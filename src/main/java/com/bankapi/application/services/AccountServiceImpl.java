package com.bankapi.application.services;


import com.bankapi.domain.models.AccountStatus;
import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private static final String IDEMPOTENCY_HEADER = "Idempotency-Key";

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO request, String idempotencyKey) {
        logger.info("Creando cuenta con clave de idempotencia: {}", idempotencyKey);

        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            Optional<Account> existingAccount = accountRepository.findByIdempotencyKey(idempotencyKey);
            if (existingAccount.isPresent()) {
                logger.info("Cuenta encontrada para clave de idempotencia: {}", idempotencyKey);
                return AccountResponseDTO.fromAccount(existingAccount.get());
            }
        }

        if (!request.isValidInitialBalanceForAccountType()) {
            throw new IllegalArgumentException(
                "El saldo inicial no es válido para el tipo de cuenta especificado");
        }

        Account account = new Account(
            request.clientId(),
            request.accountType(),
            request.initialBalance(),
            request.currency(),
            request.description()
        );

        account.setIdempotencyKey(idempotencyKey);
        Account savedAccount = accountRepository.save(account);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.getAccountId());

        return AccountResponseDTO.fromAccount(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountResponseDTO> getAccountById(UUID accountId) {
        logger.debug("Consultando cuenta con ID: {}", accountId);
        return accountRepository.findById(accountId)
            .map(AccountResponseDTO::fromAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAccountsByClientId(UUID clientId) {
        logger.debug("Consultando cuentas para cliente: {}", clientId);
        return accountRepository.findByClientId(clientId)
            .stream()
            .map(AccountResponseDTO::fromAccount)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAllAccounts() {
        logger.debug("Consultando todas las cuentas");
        return accountRepository.findAll()
            .stream()
            .map(AccountResponseDTO::fromAccount)
            .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO request) {
        logger.info("Actualizando cuenta con ID: {}", accountId);

        Account existingAccount = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (request.accountType() != null && !request.accountType().isBlank()) {
            existingAccount.setAccountType(request.accountType());
        }

        if (request.currency() != null && !request.currency().isBlank()) {
            existingAccount.setCurrency(request.currency());
        }

        if (request.description() != null) {
            existingAccount.setDescription(request.description());
        }

        existingAccount.onUpdate();
        Account updatedAccount = accountRepository.save(existingAccount);
        logger.info("Cuenta actualizada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO deposit(UUID accountId, BigDecimal amount) {
        logger.info("Depósito de {} en cuenta: {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (account.getStatus() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException(
                "No se puede depositar en una cuenta que no está activa");
        }

        account.updateBalance(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.getBalance());

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Retiro de {} de cuenta: {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (account.getStatus() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException(
                "No se puede retirar de una cuenta que no está activa");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException(
                "Saldo insuficiente para realizar el retiro");
        }

        account.updateBalance(amount.negate());
        Account updatedAccount = accountRepository.save(account);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.getBalance());

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO blockAccount(UUID accountId) {
        logger.info("Bloqueando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        account.blockAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta bloqueada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO closeAccount(UUID accountId) {
        logger.info("Cerrando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException(
                "No se puede cerrar una cuenta con saldo diferente a cero");
        }

        account.closeAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta cerrada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }

    @Override
    public AccountResponseDTO activateAccount(UUID accountId) {
        logger.info("Activando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Cuenta no encontrada con ID: " + accountId));

        account.activateAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta activada exitosamente: {}", accountId);

        return AccountResponseDTO.fromAccount(updatedAccount);
    }
}
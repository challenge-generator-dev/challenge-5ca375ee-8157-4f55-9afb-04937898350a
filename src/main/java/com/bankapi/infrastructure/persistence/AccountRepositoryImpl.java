package com.bankapi.infrastructure.persistence;


import com.bankapi.domain.models.AccountStatus;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.repositories.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account account) {
        if (account.getAccountId() == null) {
            entityManager.persist(account);
            logger.debug("Cuenta persistida con ID: {}", account.getAccountId());
            return account;
        } else {
            Account merged = entityManager.merge(account);
            logger.debug("Cuenta actualizada con ID: {}", merged.getAccountId());
            return merged;
        }
    }

    @Override
    public Optional<Account> findById(UUID accountId) {
        logger.debug("Buscando cuenta por ID: {}", accountId);
        Account account = entityManager.find(Account.class, accountId);
        return Optional.ofNullable(account);
    }

    @Override
    public List<Account> findAll() {
        logger.debug("Consultando todas las cuentas");
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }

    @Override
    public List<Account> findByClientId(UUID clientId) {
        logger.debug("Buscando cuentas para cliente: {}", clientId);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.clientId = :clientId", Account.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class);
        query.setParameter("accountNumber", accountNumber);
        List<Account> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<Account> findByStatus(Account.AccountStatus status) {
        logger.debug("Buscando cuentas con estado: {}", status);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.status = :status", Account.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<Account> findByCurrency(String currency) {
        logger.debug("Buscando cuentas en moneda: {}", currency);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.currency = :currency", Account.class);
        query.setParameter("currency", currency);
        return query.getResultList();
    }

    @Override
    public Optional<Account> findByIdempotencyKey(String idempotencyKey) {
        logger.debug("Buscando cuenta por clave de idempotencia: {}", idempotencyKey);
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.idempotencyKey = :idempotencyKey", Account.class);
        query.setParameter("idempotencyKey", idempotencyKey);
        List<Account> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public void delete(Account account) {
        logger.debug("Eliminando cuenta: {}", account.getAccountId());
        entityManager.remove(account);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(a) FROM Account a WHERE a.accountNumber = :accountNumber", Long.class);
        query.setParameter("accountNumber", accountNumber);
        return query.getSingleResult() > 0;
    }

    @Override
    public List<Account> findAccountsWithBalanceGreaterThan(BigDecimal amount) {
        logger.debug("Buscando cuentas con saldo mayor a: {}", amount);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.balance > :amount", Account.class);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    @Override
    public List<Account> findAccountsWithBalanceLessThan(BigDecimal amount) {
        logger.debug("Buscando cuentas con saldo menor a: {}", amount);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.balance < :amount", Account.class);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    @Override
    public List<Account> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Buscando cuentas creadas entre {} y {}", startDate, endDate);
        TypedQuery<Account> query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.createdAt BETWEEN :startDate AND :endDate", Account.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
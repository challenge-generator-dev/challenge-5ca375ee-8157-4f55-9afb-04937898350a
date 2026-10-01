package com.bankapi.application.services;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    AccountResponseDTO createAccount(AccountRequestDTO requestDTO, String idempotencyKey);

    Optional<AccountResponseDTO> getAccountById(UUID accountId);

    List<AccountResponseDTO> getAccountsByClientId(UUID clientId);

    List<AccountResponseDTO> getAllAccounts();

    Optional<AccountResponseDTO> updateAccount(UUID accountId, AccountRequestDTO requestDTO);

    boolean deleteAccount(UUID accountId);

    Optional<AccountResponseDTO> activateAccount(UUID accountId);

    Optional<AccountResponseDTO> blockAccount(UUID accountId);

    Optional<AccountResponseDTO> closeAccount(UUID accountId);

    boolean existsByClientIdAndAccountType(UUID clientId, String accountType);

    int countAccountsByClientId(UUID clientId);
}
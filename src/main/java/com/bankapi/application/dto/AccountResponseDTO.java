package com.bankapi.application.dto;


import com.bankapi.domain.models.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponseDTO(
    UUID accountId,
    UUID clientId,
    String accountType,
    BigDecimal balance,
    String currency,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String description
) {
    // Constructor canónico generado automáticamente por record
    // Getters, equals, hashCode y toString también generados automáticamente

    /**
     * Crea un DTO de respuesta a partir de una entidad Account.
     * @param account La entidad Account de dominio.
     * @return Un AccountResponseDTO con los datos de la cuenta.
     */
    public static AccountResponseDTO fromAccount(com.bankapi.domain.models.Account account) {
        return new AccountResponseDTO(
            account.getAccountId(),
            account.getClientId(),
            account.getAccountType(),
            account.getBalance(),
            account.getCurrency(),
            account.getStatus(),
            account.getCreatedAt(),
            account.getUpdatedAt(),
            account.getDescription()
        );
    }
}
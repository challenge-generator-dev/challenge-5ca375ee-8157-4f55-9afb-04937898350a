package com.bankapi.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id", "account_type", "currency"})
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", updatable = false, nullable = false)
    private UUID accountId;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "account_type", nullable = false, length = 20)
    private String accountType;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AccountStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "description", length = 200)
    private String description;

    public enum AccountStatus {
        ACTIVE,
        BLOCKED,
        CLOSED
    }

    // Constructor sin argumentos requerido por JPA
    protected Account() {
    }

    /**
     * Constructor para crear una nueva cuenta.
     * @param clientId ID del cliente propietario de la cuenta.
     * @param accountType Tipo de cuenta (CORRIENTE, AHORROS, INVERSION).
     * @param initialBalance Saldo inicial de la cuenta.
     * @param currency Moneda de la cuenta.
     * @param description Descripción opcional de la cuenta.
     */
    public Account(UUID clientId, String accountType, BigDecimal initialBalance, String currency, String description) {
        this.clientId = clientId;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.currency = currency;
        this.status = AccountStatus.ACTIVE;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters y setters
    public UUID getAccountId() {
        return accountId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Actualiza el saldo de la cuenta.
     * @param amount Monto a depositar (positivo) o retirar (negativo).
     * @throws IllegalArgumentException si el monto es negativo y supera el saldo actual.
     */
    public void updateBalance(BigDecimal amount) {
        BigDecimal newBalance = this.balance.add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la operación");
        }
        this.balance = newBalance;
    }

    /**
     * Bloquea la cuenta.
     */
    public void blockAccount() {
        this.status = AccountStatus.BLOCKED;
    }

    /**
     * Cierra la cuenta si el saldo es cero.
     * @throws IllegalStateException si el saldo no es cero.
     */
    public void closeAccount() {
        if (this.balance.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("No se puede cerrar una cuenta con saldo diferente de cero");
        }
        this.status = AccountStatus.CLOSED;
    }

    /**
     * Reactiva la cuenta si estaba bloqueada.
     * @throws IllegalStateException si la cuenta no está bloqueada.
     */
    public void activateAccount() {
        if (this.status != AccountStatus.BLOCKED) {
            throw new IllegalStateException("Solo se puede activar una cuenta bloqueada");
        }
        this.status = AccountStatus.ACTIVE;
    }
}
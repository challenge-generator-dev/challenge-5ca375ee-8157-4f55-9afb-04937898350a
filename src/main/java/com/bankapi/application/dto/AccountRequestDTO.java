package com.bankapi.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

public record AccountRequestDTO(
    @NotBlank(message = "El ID del cliente es obligatorio")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
             message = "El ID del cliente debe ser un UUID válido")
    String clientId,

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Size(min = 3, max = 20, message = "El tipo de cuenta debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "^(CORRIENTE|AHORROS|INVERSION)$", message = "Tipo de cuenta no válido")
    String accountType,

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.00", message = "El saldo inicial debe ser mayor o igual a 0")
    BigDecimal initialBalance,

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener exactamente 3 caracteres")
    @Pattern(regexp = "^[A-Z]{3}$", message = "La moneda debe ser un código ISO válido (ej: USD, EUR)")
    String currency,

    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres")
    String description
) {
    // Constructor canónico generado automáticamente por record
    // Getters, equals, hashCode y toString también generados automáticamente

    /**
     * Valida que el saldo inicial sea cero para cuentas de tipo INVERSION.
     * @return true si la validación pasa, false en caso contrario.
     */
    public boolean isValidInitialBalanceForAccountType() {
        if ("INVERSION".equals(accountType) && initialBalance.compareTo(BigDecimal.ZERO) != 0) {
            return false;
        }
        return true;
    }
}
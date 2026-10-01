package com.bankapi.infrastructure.controllers;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.application.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Gestión de Cuentas", description = "API para la gestión de cuentas bancarias")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cuenta", 
               description = "Crea una nueva cuenta bancaria. Soporta idempotencia mediante el header Idempotency-Key.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Conflicto - cuenta ya existe con la misma clave de idempotencia"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> createAccount(
            @Parameter(description = "Datos para crear la cuenta") @RequestBody AccountRequestDTO request,
            @Parameter(description = "Clave de idempotencia para evitar duplicados") 
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        logger.info("Solicitud de creación de cuenta recibida");
        AccountResponseDTO createdAccount = accountService.createAccount(request, idempotencyKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Obtener cuenta por ID", 
               description = "Recupera los detalles de una cuenta bancaria específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta encontrada",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> getAccountById(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de consulta de cuenta: {}", accountId);
        return accountService.getAccountById(accountId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Obtener cuentas por cliente", 
               description = "Recupera todas las cuentas asociadas a un cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas encontradas",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByClientId(
            @Parameter(description = "ID del cliente") @PathVariable UUID clientId) {
        logger.info("Solicitud de consulta de cuentas para cliente: {}", clientId);
        List<AccountResponseDTO> accounts = accountService.getAccountsByClientId(clientId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las cuentas", 
               description = "Recupera todas las cuentas bancarias del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas encontradas",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        logger.info("Solicitud de consulta de todas las cuentas");
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{accountId}")
    @Operation(summary = "Actualizar cuenta", 
               description = "Actualiza la información de una cuenta bancaria existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId,
            @Parameter(description = "Datos actualizados de la cuenta") @RequestBody AccountRequestDTO request) {
        logger.info("Solicitud de actualización de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.updateAccount(accountId, request);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/deposit")
    @Operation(summary = "Depositar fondos", 
               description = "Realiza un depósito en una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Depósito exitoso",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Monto inválido"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "409", description = "Cuenta no está activa"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> deposit(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId,
            @Parameter(description = "Monto a depositar") @RequestParam BigDecimal amount) {
        logger.info("Solicitud de depósito de {} en cuenta: {}", amount, accountId);
        AccountResponseDTO updatedAccount = accountService.deposit(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/withdraw")
    @Operation(summary = "Retirar fondos", 
               description = "Realiza un retiro de una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retiro exitoso",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Monto inválido"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "409", description = "Saldo insuficiente o cuenta no activa"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> withdraw(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId,
            @Parameter(description = "Monto a retirar") @RequestParam BigDecimal amount) {
        logger.info("Solicitud de retiro de {} de cuenta: {}", amount, accountId);
        AccountResponseDTO updatedAccount = accountService.withdraw(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/block")
    @Operation(summary = "Bloquear cuenta", 
               description = "Bloquea una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta bloqueada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> blockAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de bloqueo de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.blockAccount(accountId);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/close")
    @Operation(summary = "Cerrar cuenta", 
               description = "Cierra una cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta cerrada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "La cuenta tiene saldo pendiente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> closeAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de cierre de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.closeAccount(accountId);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/activate")
    @Operation(summary = "Activar cuenta", 
               description = "Activa una cuenta bancaria previamente bloqueada o cerrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta activada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<AccountResponseDTO> activateAccount(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID accountId) {
        logger.info("Solicitud de activación de cuenta: {}", accountId);
        AccountResponseDTO updatedAccount = accountService.activateAccount(accountId);
        return ResponseEntity.ok(updatedAccount);
    }
}
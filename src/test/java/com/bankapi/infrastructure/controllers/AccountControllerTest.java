package com.bankapi.infrastructure.controllers;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.application.services.AccountService;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.models.Account.AccountStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@DisplayName("AccountController - Pruebas de Integración")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    private UUID testClientId;
    private UUID testAccountId;
    private Account testAccount;
    private AccountRequestDTO validRequest;
    private AccountResponseDTO validResponse;

    @BeforeEach
    void setUp() {
        testClientId = UUID.randomUUID();
        testAccountId = UUID.randomUUID();
        testAccount = new Account(testClientId, "SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta de prueba");
        validRequest = new AccountRequestDTO("SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta de prueba");
        validResponse = AccountResponseDTO.fromAccount(testAccount);
    }

    @Nested
    @DisplayName("POST /api/accounts - Crear Cuenta")
    class CreateAccountTests {

        @Test
        @DisplayName("Crear cuenta retorna 201 Created con datos correctos")
        @WithMockUser(roles = "USER")
        void createAccount_Returns201Created() throws Exception {
            when(accountService.createAccount(eq(testClientId), any(AccountRequestDTO.class)))
                .thenReturn(validResponse);

            mockMvc.perform(post("/api/accounts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.accountId").value(testAccount.getAccountId().toString()))
                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
                .andExpect(jsonPath("$.balance").value(1000.00));
        }

        @Test
        @DisplayName("Crear cuenta sin autenticación retorna 401 Unauthorized")
        void createAccount_WithoutAuth_Returns401() throws Exception {
            mockMvc.perform(post("/api/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Crear cuenta con datos inválidos retorna 400 Bad Request")
        @WithMockUser(roles = "USER")
        void createAccount_WithInvalidData_Returns400() throws Exception {
            AccountRequestDTO invalidRequest = new AccountRequestDTO(
                "INVALID_TYPE",
                new BigDecimal("-100.00"),
                "USD",
                "Datos inválidos"
            );

            mockMvc.perform(post("/api/accounts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Crear cuenta conCSRF inválido retorna 403 Forbidden")
        @WithMockUser(roles = "USER")
        void createAccount_WithInvalidCsrf_Returns403() throws Exception {
            mockMvc.perform(post("/api/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("GET /api/accounts/{id} - Obtener Cuenta por ID")
    class GetAccountByIdTests {

        @Test
        @DisplayName("Obtener cuenta existente retorna 200 OK")
        @WithMockUser(roles = "USER")
        void getAccountById_WhenExists_Returns200() throws Exception {
            when(accountService.getAccountById(testAccountId))
                .thenReturn(Optional.of(testAccount));

            mockMvc.perform(get("/api/accounts/{id}", testAccountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(testAccountId.toString()))
                .andExpect(jsonPath("$.clientId").value(testClientId.toString()))
                .andExpect(jsonPath("$.accountType").value("SAVINGS"));
        }

        @Test
        @DisplayName("Obtener cuenta inexistente retorna 404 Not Found")
        @WithMockUser(roles = "USER")
        void getAccountById_WhenNotExists_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId))
                .thenReturn(Optional.empty());

            mockMvc.perform(get("/api/accounts/{id}", nonExistentId))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Obtener cuenta sin autenticación retorna 401 Unauthorized")
        void getAccountById_WithoutAuth_Returns401() throws Exception {
            mockMvc.perform(get("/api/accounts/{id}", testAccountId))
                .andExpect(status().isUnauthorized());
        }
    }

    @Nested
n    @DisplayName("GET /api/accounts/client/{clientId} - Obtener Cuentas por Cliente")
    class GetAccountsByClientTests {

        @Test
        @DisplayName("Obtener cuentas de cliente retorna 200 OK con lista")
        @WithMockUser(roles = "USER")
        void getAccountsByClientId_Returns200WithList() throws Exception {
            when(accountService.getAccountsByClientId(testClientId))
                .thenReturn(List.of(testAccount));

            mockMvc.perform(get("/api/accounts/client/{clientId}", testClientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].clientId").value(testClientId.toString()));
        }

        @Test
        @DisplayName("Obtener cuentas de cliente sin cuentas retorna 200 OK con lista vacía")
        @WithMockUser(roles = "USER")
        void getAccountsByClientId_WhenNoAccounts_ReturnsEmptyList() throws Exception {
            when(accountService.getAccountsByClientId(testClientId))
                .thenReturn(List.of());

            mockMvc.perform(get("/api/accounts/client/{clientId}", testClientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
        }
    }

    @Nested
    @DisplayName("PUT /api/accounts/{id}/block - Bloquear Cuenta")
    class BlockAccountTests {

        @Test
        @DisplayName("Bloquear cuenta retorna 200 OK")
        @WithMockUser(roles = "ADMIN")
        void blockAccount_Returns200() throws Exception {
            Account blockedAccount = new Account(testClientId, "SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta bloqueada");
            blockedAccount.blockAccount();
            when(accountService.blockAccount(testAccountId))
                .thenReturn(blockedAccount);

            mockMvc.perform(put("/api/accounts/{id}/block", testAccountId)
                    .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("BLOCKED"));
        }

        @Test
        @DisplayName("Bloquear cuenta sin rol admin retorna 403 Forbidden")
        @WithMockUser(roles = "USER")
        void blockAccount_WithoutAdminRole_Returns403() throws Exception {
            mockMvc.perform(put("/api/accounts/{id}/block", testAccountId)
                    .with(csrf()))
                .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("PUT /api/accounts/{id}/close - Cerrar Cuenta")
    class CloseAccountTests {

        @Test
        @DisplayName("Cerrar cuenta retorna 200 OK")
        @WithMockUser(roles = "ADMIN")
        void closeAccount_Returns200() throws Exception {
            Account closedAccount = new Account(testClientId, "SAVINGS", BigDecimal.ZERO, "USD", "Cuenta cerrada");
            closedAccount.closeAccount();
            when(accountService.closeAccount(testAccountId))
                .thenReturn(closedAccount);

            mockMvc.perform(put("/api/accounts/{id}/close", testAccountId)
                    .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CLOSED"));
        }
    }

    @Nested
    @DisplayName("PUT /api/accounts/{id}/activate - Activar Cuenta")
    class ActivateAccountTests {

        @Test
        @DisplayName("Activar cuenta retorna 200 OK")
        @WithMockUser(roles = "ADMIN")
        void activateAccount_Returns200() throws Exception {
            Account activeAccount = new Account(testClientId, "SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta activa");
            when(accountService.activateAccount(testAccountId))
                .thenReturn(activeAccount);

            mockMvc.perform(put("/api/accounts/{id}/activate", testAccountId)
                    .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
        }
    }

    @Nested
    @DisplayName("Manejo de Errores Global")
    class GlobalErrorHandlingTests {

        @Test
        @DisplayName("Error de validación retorna 400 Bad Request con detalles")
        @WithMockUser(roles = "USER")
        void validationError_Returns400WithDetails() throws Exception {
            AccountRequestDTO invalidRequest = new AccountRequestDTO(
                "SAVINGS",
                null,
                "USD",
                "Falta balance"
            );

            mockMvc.perform(post("/api/accounts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest))
                    .param("clientId", testClientId.toString()))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Path variable inválida retorna 400 Bad Request")
        @WithMockUser(roles = "USER")
        void invalidPathVariable_Returns400() throws Exception {
            mockMvc.perform(get("/api/accounts/invalid-uuid"))
                .andExpect(status().isBadRequest());
        }
    }
}
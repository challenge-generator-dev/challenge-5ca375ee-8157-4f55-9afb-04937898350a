package com.bankapi.application.services;

import com.bankapi.application.dto.AccountRequestDTO;
import com.bankapi.application.dto.AccountResponseDTO;
import com.bankapi.domain.models.Account;
import com.bankapi.domain.models.Account.AccountStatus;
import com.bankapi.domain.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AccountService - Pruebas Unitarias")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account testAccount;
    private AccountRequestDTO validRequest;
    private UUID testClientId;

    @BeforeEach
    void setUp() {
        testClientId = UUID.randomUUID();
        testAccount = new Account(
            testClientId,
            "SAVINGS",
            new BigDecimal("1000.00"),
            "USD",
            "Cuenta de ahorro personal"
        );
        validRequest = new AccountRequestDTO("SAVINGS", new BigDecimal("1000.00"), "USD", "Cuenta de ahorro personal");
    }

    @Nested
    @DisplayName("Creación de Cuentas")
    class CreateAccountTests {

        @Test
        @DisplayName("Crear cuenta exitosamente con datos válidos")
        void createAccount_WithValidData_ReturnsAccountResponse() {
            when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

            AccountResponseDTO result = accountService.createAccount(testClientId, validRequest);

            assertNotNull(result);
            assertEquals(testAccount.getAccountId(), result.accountId());
            assertEquals(testClientId, result.clientId());
            assertEquals("SAVINGS", result.accountType());
            assertEquals(new BigDecimal("1000.00"), result.balance());
            assertEquals("USD", result.currency());
            verify(accountRepository, times(1)).save(any(Account.class));
        }

        @Test
        @DisplayName("Crear cuenta falla con saldo inicial negativo")
        void createAccount_WithNegativeBalance_ThrowsException() {
            AccountRequestDTO invalidRequest = new AccountRequestDTO(
                "SAVINGS",
                new BigDecimal("-100.00"),
                "USD",
                "Cuenta inválida"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, invalidRequest)
            );
            verify(accountRepository, never()).save(any(Account.class));
        }

        @Test
        @DisplayName("Crear cuenta de tipo CHECKING con saldo cero es válido")
        void createAccount_CheckingWithZeroBalance_IsValid() {
            AccountRequestDTO checkingRequest = new AccountRequestDTO(
                "CHECKING",
                BigDecimal.ZERO,
                "USD",
                "Cuenta corriente"
            );
            Account checkingAccount = new Account(testClientId, "CHECKING", BigDecimal.ZERO, "USD", "Cuenta corriente");
            when(accountRepository.save(any(Account.class))).thenReturn(checkingAccount);

            AccountResponseDTO result = accountService.createAccount(testClientId, checkingRequest);

            assertNotNull(result);
            assertEquals("CHECKING", result.accountType());
            assertEquals(BigDecimal.ZERO, result.balance());
        }

        @Test
        @DisplayName("Crear cuenta falla con tipo de cuenta inválido")
        void createAccount_WithInvalidAccountType_ThrowsException() {
            AccountRequestDTO invalidTypeRequest = new AccountRequestDTO(
                "INVALID_TYPE",
                new BigDecimal("500.00"),
                "USD",
                "Tipo inválido"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, invalidTypeRequest)
            );
        }

        @Test
        @DisplayName("Crear cuenta falla si el cliente no existe")
        void createAccount_WithNonExistentClient_ThrowsException() {
            UUID nonExistentClientId = UUID.randomUUID();
            when(accountRepository.existsByClientId(nonExistentClientId)).thenReturn(false);
            when(accountRepository.save(any(Account.class))).thenThrow(new IllegalStateException("Cliente no encontrado"));

            assertThrows(IllegalStateException.class, () ->
                accountService.createAccount(nonExistentClientId, validRequest)
            );
        }
    }

    @Nested
    @DisplayName("Consulta de Cuentas")
    class GetAccountTests {

        @Test
        @DisplayName("Obtener cuenta por ID exitosamente")
        void getAccountById_WhenExists_ReturnsAccount() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

            Optional<Account> result = accountService.getAccountById(accountId);

            assertTrue(result.isPresent());
            assertEquals(accountId, result.get().getAccountId());
            verify(accountRepository, times(1)).findById(accountId);
        }

        @Test
        @DisplayName("Obtener cuenta por ID cuando no existe retorna vacío")
        void getAccountById_WhenNotExists_ReturnsEmpty() {
            UUID nonExistentId = UUID.randomUUID();
            when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            Optional<Account> result = accountService.getAccountById(nonExistentId);

            assertTrue(result.isEmpty());
            verify(accountRepository, times(1)).findById(nonExistentId);
        }

        @Test
        @DisplayName("Obtener cuentas por cliente exitosamente")
        void getAccountsByClientId_ReturnsAccountList() {
            when(accountRepository.findByClientId(testClientId)).thenReturn(java.util.List.of(testAccount));

            var result = accountService.getAccountsByClientId(testClientId);

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            assertEquals(testClientId, result.get(0).getClientId());
        }

        @Test
        @DisplayName("Obtener cuentas por cliente sin cuentas retorna lista vacía")
        void getAccountsByClientId_WhenNoAccounts_ReturnsEmptyList() {
            when(accountRepository.findByClientId(testClientId)).thenReturn(java.util.List.of());

            var result = accountService.getAccountsByClientId(testClientId);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Actualización de Cuentas")
    class UpdateAccountTests {

        @Test
        @DisplayName("Actualizar balance de cuenta exitosamente")
        void updateAccountBalance_WithValidAmount_UpdatesBalance() {
            UUID accountId = testAccount.getAccountId();
            BigDecimal newBalance = new BigDecimal("500.00");
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.updateAccountBalance(accountId, newBalance);

            assertNotNull(result);
            assertEquals(newBalance, result.getBalance());
            verify(accountRepository, times(1)).save(testAccount);
        }

        @Test
        @DisplayName("Actualizar cuenta que no existe lanza excepción")
        void updateAccountBalance_WhenNotExists_ThrowsException() {
            UUID nonExistentId = UUID.randomUUID();
            when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () ->
                accountService.updateAccountBalance(nonExistentId, new BigDecimal("100.00"))
            );
        }

        @Test
        @DisplayName("Bloquear cuenta exitosamente")
        void blockAccount_WhenActive_ChangesStatusToBlocked() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.blockAccount(accountId);

            assertEquals(AccountStatus.BLOCKED, result.getStatus());
            verify(accountRepository, times(1)).save(testAccount);
        }

        @Test
        @DisplayName("Cerrar cuenta exitosamente")
        void closeAccount_WhenActive_ChangesStatusToClosed() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.closeAccount(accountId);

            assertEquals(AccountStatus.CLOSED, result.getStatus());
        }

        @Test
        @DisplayName("Activar cuenta bloqueada exitosamente")
        void activateAccount_WhenBlocked_ChangesStatusToActive() {
            testAccount.blockAccount();
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Account result = accountService.activateAccount(accountId);

            assertEquals(AccountStatus.ACTIVE, result.getStatus());
        }
    }

    @Nested
    @DisplayName("Excepciones y Casos Edge")
    class ExceptionHandlingTests {

        @Test
        @DisplayName("Crear cuenta con currency inválido lanza excepción")
        void createAccount_WithInvalidCurrency_ThrowsException() {
            AccountRequestDTO invalidCurrencyRequest = new AccountRequestDTO(
                "SAVINGS",
                new BigDecimal("100.00"),
                "INVALID",
                "Currency inválida"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, invalidCurrencyRequest)
            );
        }

        @Test
        @DisplayName("Crear cuenta con saldo muy grande lanza excepción")
        void createAccount_WithExcessiveBalance_ThrowsException() {
            AccountRequestDTO excessiveRequest = new AccountRequestDTO(
                "SAVINGS",
                new BigDecimal("100000000.00"),
                "USD",
                "Monto excesivo"
            );

            assertThrows(IllegalArgumentException.class, () ->
                accountService.createAccount(testClientId, excessiveRequest)
            );
        }

        @Test
        @DisplayName("Actualizar balance con monto negativo lanza excepción")
        void updateAccountBalance_WithNegativeAmount_ThrowsException() {
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

            assertThrows(IllegalArgumentException.class, () ->
                accountService.updateAccountBalance(accountId, new BigDecimal("-50.00"))
            );
        }

        @Test
        @DisplayName("Cerrar cuenta que ya está cerrada lanza excepción")
        void closeAccount_WhenAlreadyClosed_ThrowsException() {
            testAccount.closeAccount();
            UUID accountId = testAccount.getAccountId();
            when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

            assertThrows(IllegalStateException.class, () ->
                accountService.closeAccount(accountId)
            );
        }
    }
}
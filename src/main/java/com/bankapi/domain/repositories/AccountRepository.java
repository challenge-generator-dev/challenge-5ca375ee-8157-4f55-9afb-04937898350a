package com.bankapi.domain.repositories;


import com.bankapi.domain.models.AccountStatus;
import com.bankapi.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByClientId(UUID clientId);

    List<Account> findByAccountType(String accountType);

    List<Account> findByStatus(Account.AccountStatus status);

    @Query("SELECT a FROM Account a WHERE a.clientId = :clientId AND a.status = :status")
    List<Account> findByClientIdAndStatus(@Param("clientId") UUID clientId, @Param("status") Account.AccountStatus status);

    @Query("SELECT a FROM Account a WHERE a.accountType = :accountType AND a.status = :status")
    List<Account> findByAccountTypeAndStatus(@Param("accountType") String accountType, @Param("status") Account.AccountStatus status);

    @Query("SELECT SUM(a.balance) FROM Account a WHERE a.clientId = :clientId")
    BigDecimal getTotalBalanceByClientId(@Param("clientId") UUID clientId);

    @Query("SELECT a FROM Account a WHERE a.createdAt BETWEEN :startDate AND :endDate")
    List<Account> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    boolean existsByClientIdAndAccountType(UUID clientId, String accountType);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.clientId = :clientId")
    int countByClientId(@Param("clientId") UUID clientId);
}
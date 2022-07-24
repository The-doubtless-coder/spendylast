package com.spenndify.application.spendylast.spendyuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface SpendyRepository extends JpaRepository<SpendyUser, Long> {
    @Query("select user FROM SpendyUser user WHERE user.email=?1 OR user.phone=?1")
    SpendyUser findByEmailOrPhone(String emailOrPhone);
    Optional<SpendyUser> findByIdNumber(String idNumber); //for the if-else method I wanted to implement

    @Transactional
    @Modifying
    @Query("UPDATE SpendyUser s " +
            "SET s.enabled = TRUE WHERE s.phone = ?1")
    void enableSpendyUser(String phone);
}

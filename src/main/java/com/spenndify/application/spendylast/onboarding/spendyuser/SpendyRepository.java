package com.spenndify.application.spendylast.onboarding.spendyuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SpendyRepository extends JpaRepository<SpendyUser, Long> {
    @Query("select user FROM SpendyUser user WHERE user.email=?1 OR user.phone=?1")
    SpendyUser findByEmailOrPhone(String emailOrPhone);
    SpendyUser findByIdNumber(String idNumber); //for the if-else method I wanted to implement
    SpendyUser findByPhone(String phone);


    @Transactional
    @Modifying
    @Query("UPDATE SpendyUser s " +
            "SET s.enabled = TRUE WHERE s.phone = ?1")
    void enableSpendyUser(String phone);

    @Transactional
    @Modifying
    @Query("UPDATE SpendyUser s " +
            "SET s.password = ?1 WHERE s.idNumber = ?2")
    void changeUserPassword(String password, String idNumber);
}

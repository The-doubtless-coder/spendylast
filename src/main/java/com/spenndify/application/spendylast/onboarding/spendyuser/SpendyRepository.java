package com.spenndify.application.spendylast.onboarding.spendyuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface SpendyRepository extends JpaRepository<SpendUser, Long> {
    @Query("SELECT user FROM SpendUser u WHERE u.email=?1 OR u.phone=?1")
    SpendUser findByEmailOrPhone(String emailOrPhone);
    SpendUser findByIdNumber(String idNumber); //for the if-else method I wanted to implement
    SpendUser findByPhone(String phone);


    @Transactional
    @Modifying
    @Query("UPDATE SpendUser s " +
            "SET s.enabled = TRUE WHERE s.phone = ?1")
    void enableSpendyUser(String phone);

    @Transactional
    @Modifying
    @Query("UPDATE SpendUser s " +
            "SET s.password = ?1 WHERE s.idNumber = ?2")
    void changeUserPassword(String password, String idNumber);
}

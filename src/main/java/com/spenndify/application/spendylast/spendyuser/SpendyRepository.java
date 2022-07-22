package com.spenndify.application.spendylast.spendyuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SpendyRepository extends JpaRepository<SpendyUser, Long> {
    @Query("select user FROM SpendyUser user WHERE user.email=?1 OR user.phone=?1")
    SpendyUser findByEmailOrPhone(String emailOrPhone);
//    Optional<SpendyUser> findByEmailOrPhone(String emailOrPhone);
    Optional<SpendyUser> findByIdNumber(String idNumber); //for the if-else method I wanted to implement
}

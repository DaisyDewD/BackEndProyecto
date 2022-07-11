package com.dh.clinica.repository.impl;

import com.dh.clinica.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<AppUser, Long> {
=======
public interface UserRepository extends JpaRepository<AppUser, Integer> {
>>>>>>> 8943bfb32cf38b3fbcc201f1776325fbec48297b

    Optional<AppUser> findByEmail(String email);
}

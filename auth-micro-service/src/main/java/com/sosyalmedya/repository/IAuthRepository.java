package com.sosyalmedya.repository;

import com.sosyalmedya.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface IAuthRepository extends JpaRepository<Auth,Long> {
   Optional<Auth> findOptionalByUsername(String username);
   Optional<Auth> findOptionalByUsernameAndPassword(String username,String password);

}

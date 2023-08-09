package com.sosyalmedya.repository;

import com.sosyalmedya.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User,String> {

   Optional<User> findByAuthId(Long authId);
}

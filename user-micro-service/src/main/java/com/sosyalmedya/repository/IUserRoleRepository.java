package com.sosyalmedya.repository;

import com.sosyalmedya.repository.entity.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IUserRoleRepository extends MongoRepository<UserRole,String> {
List<UserRole> findOptionalByAuthId(Long authId);
}

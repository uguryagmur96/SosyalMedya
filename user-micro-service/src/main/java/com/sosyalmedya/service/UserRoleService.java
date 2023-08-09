package com.sosyalmedya.service;

import com.sosyalmedya.repository.IUserRoleRepository;
import com.sosyalmedya.repository.entity.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRoleService {
    private final IUserRoleRepository userRoleRepository;


    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
    public List<UserRole> findAllByAuthId(Long authId) {
      return userRoleRepository.findOptionalByAuthId(authId);
    }
}

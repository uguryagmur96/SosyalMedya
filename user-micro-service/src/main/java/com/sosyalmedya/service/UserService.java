package com.sosyalmedya.service;

import com.sosyalmedya.dto.request.UserSaveRequestDto;
import com.sosyalmedya.exceptions.ErrorType;
import com.sosyalmedya.exceptions.UserException;
import com.sosyalmedya.repository.IUserRepository;
import com.sosyalmedya.repository.entity.User;
import com.sosyalmedya.util.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final JwtTokenManager  jwtTokenManager;
    public void save(UserSaveRequestDto dto){
        userRepository.save(User.builder()
                        .authId(dto.getAuthid())
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                .build());
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public List<User> findAll(String token){
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(token);
        if (userId.isEmpty()) throw new UserException(ErrorType.INVALID_TOKEN);
        return userRepository.findAll();
    }

}

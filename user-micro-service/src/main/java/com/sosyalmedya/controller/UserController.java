package com.sosyalmedya.controller;

import com.sosyalmedya.dto.request.UserSaveRequestDto;
import com.sosyalmedya.dto.response.UserSaveResponseDto;
import com.sosyalmedya.repository.entity.User;
import com.sosyalmedya.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sosyalmedya.constants.RestApiList.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER','SUPER_USER')")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("User role ile girilmesi için test methodu");
    }


    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test");
    }

@PostMapping(SAVE)
    public ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto dto){
    userService.save(dto);
        return ResponseEntity.ok(UserSaveResponseDto.builder()
                        .status(200)
                        .result("Başarılı bir şekilde kayıt edildi.")
                .build());
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<User>> findAll(String token){
        return ResponseEntity.ok(userService.findAll(token));
    }
}

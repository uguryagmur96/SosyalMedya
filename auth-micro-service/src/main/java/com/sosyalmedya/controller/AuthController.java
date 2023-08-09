package com.sosyalmedya.controller;

import com.sosyalmedya.dto.request.doLoginRequestDto;
import com.sosyalmedya.dto.request.doRegisterRequestDto;
import com.sosyalmedya.dto.response.doLoginResponseDto;
import com.sosyalmedya.dto.response.doRegisterResponseDto;
import com.sosyalmedya.rabbitmq.model.CreateProfile;
import com.sosyalmedya.rabbitmq.producer.CreateProfileProducer;
import com.sosyalmedya.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sosyalmedya.constants.RestApiList.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final CreateProfileProducer createProfileProducer;
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test");
    }


    @GetMapping("/rabbitmq")
    public ResponseEntity<Void> testRabbitSendMessage(String username,String email,Long authid){
        createProfileProducer.sendCreateProfileMessage(CreateProfile.builder()
                        .authid(authid)
                        .email(email)
                        .username(username)
                .build());
        return ResponseEntity.ok().build();
    }

    @PostMapping(LOGIN)
  public ResponseEntity<doLoginResponseDto> doLogin(@RequestBody @Valid doLoginRequestDto dto){
      String token= service.login(dto);
          return ResponseEntity.ok(doLoginResponseDto.builder()
                  .status(200)
                  .result("Giriş işlemi başarılı")
                  .token(token)
                  .build());

    }
    @PostMapping(REGISTER)
    public ResponseEntity<doRegisterResponseDto> doRegister(@RequestBody @Valid doRegisterRequestDto dto){
        Boolean isRegister=service.register(dto);
        if (isRegister)
            return ResponseEntity.ok(doRegisterResponseDto.builder()
                .status(200)
                .result("Giriş işlemi başarılı")
                .build());
        return ResponseEntity.badRequest().body(
                doRegisterResponseDto.builder()
                        .status(400)
                        .result("Giriş işlemi başarısız. Lütfen bilgilerinizi gözden geçirin")
                        .build());
    }
}

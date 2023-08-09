package com.sosyalmedya.service;

import com.sosyalmedya.dto.request.UserSaveRequestDto;
import com.sosyalmedya.dto.request.doLoginRequestDto;
import com.sosyalmedya.dto.request.doRegisterRequestDto;
import com.sosyalmedya.exceptions.AuthException;
import com.sosyalmedya.manager.IUserManager;
import com.sosyalmedya.mapper.IAuthMapper;
import com.sosyalmedya.rabbitmq.model.CreateProfile;
import com.sosyalmedya.rabbitmq.producer.CreateProfileProducer;
import com.sosyalmedya.repository.IAuthRepository;
import com.sosyalmedya.repository.entity.Auth;
import com.sosyalmedya.util.JwtTokenManager;
import com.sosyalmedya.util.ServiceManager;
import org.springframework.stereotype.Service;
import static com.sosyalmedya.exceptions.ErrorType.*;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository repository;
    private final IUserManager userManager;
    private final CreateProfileProducer createProfileProducer;
    private final JwtTokenManager jwtTokenManager;
    public AuthService(IAuthRepository  repository, IUserManager userManager,CreateProfileProducer createProfileProducer,JwtTokenManager jwtTokenManager){
        super(repository);
        this.repository=repository;
        this.userManager=userManager;
        this.createProfileProducer=createProfileProducer;
        this.jwtTokenManager=jwtTokenManager;
    }

    /**
     * Kayıt olurken eğer aynı kullanıcı adı var ise hata döndürülecek
     * Kayıt başarılı ise olumlu sonuç dönülecek
     * Giriş yapınca kullanıcıya bir JWT token döndürelecek
     * Girişte sorun olursa sorun bilgisi Dto olarak dönülecek
     */

    public Boolean register(doRegisterRequestDto dto){
        repository.findOptionalByUsername(dto.getUsername()).ifPresent(auth -> {
            throw new AuthException(REGISTER_KULLANICIADI_KAYITLI);
        });
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            throw new AuthException(REGISTER_PASSWORD_NOT_MATCH);
       /*Auth auth=Auth.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
*/
     Auth auth= IAuthMapper.INSTANCE.authFromDto(dto);
        repository.save(auth);
        /**
         * Auth servis kullanıcıyı kayıt ettikten sonra user microservisine kullanıcı profili oluşturmak üzere bilgi gönderir
         * DİKKAT!!!
         *
         */
     /*   userManager.save(UserSaveRequestDto.builder()
                        .authid(auth.getId())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                .build());
                */
      createProfileProducer.sendCreateProfileMessage(CreateProfile.builder()
                      .authid(auth.getId())
                      .username(dto.getUsername())
                      .email(dto.getEmail())
              .build());
        return true;
    }
    public String login(doLoginRequestDto dto){
        Optional<Auth> auth=repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        /**
         * 1- Auth bilgisini sorgulayarak kullanıcı yok ise false dönebiliriz
         * return auth.isPresent();
         * 2- Auth bilgisini sorgulayarak kullanıcı yok ise ya da bilgileri yanlış ise exception fırlatabiliriz
         */
        if (auth.isEmpty())throw new AuthException(DOLOGIN_INVALID_USERNAME_PASSWORD);
        Optional<String> token=jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty())throw new AuthException(BAD_REQUEST_ERROR);

        return token.get();

    }
    public Optional<Auth> loginAlternatif(doLoginRequestDto dto){
         return repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());

    }
}

package com.sosyalmedya.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class doRegisterRequestDto {
    @NotEmpty(message = "Kullanıcı adı boş geçilemez")
    @Size(min = 3,max = 64,message = "Kullanıcı adınız 3 ila 64 karakter içerisinde olmalıdır")
    String username;
    @NotEmpty(message = "Şifre adı boş geçilemez")
    @Size(min = 8,max = 64,message = "Şifreniz 3 ila 64 karakter içerisinde olmalıdır")
    @Pattern(
            message = "Şifre en az 8 karakter olmalı, içinde en az bir büyük bir küçük harf sayı ve rakam olmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String password;
    @NotEmpty(message = "Şifre adı boş geçilemez")
    @Size(min = 8,max = 64,message = "Şifreniz 3 ila 64 karakter içerisinde olmalıdır")
    @Pattern(
            message = "Şifre en az 8 karakter olmalı, içinde en az bir büyük bir küçük harf sayı ve rakam olmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String confirmPassword;
    @Email(message = "Lütfen geçerli bir mail adresi giriniz")
    String email;
}

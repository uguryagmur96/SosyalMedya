/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.sosyalmedya.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Ugur
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(1000,"Sunucuda Bilinmeyen bir hata oluştu", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"İstek formatı hatalı",HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(1001,"Kullanıcı adı kayıtlı",HttpStatus.BAD_REQUEST),
    REGISTER_PASSWORD_NOT_MATCH(1002,"Girmiş olduğunuz şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    DOLOGIN_INVALID_USERNAME_PASSWORD(1003,"Kullanıcı adı veya şifre hatalı",HttpStatus.BAD_REQUEST),
    NAME_IS_NULL(1001,"Name alanı boş geçilemez",HttpStatus.BAD_REQUEST),
    ID_NOT_FOUND(1001,"Aradığınız id ye ait kayıt bulunamadı",HttpStatus.BAD_REQUEST),
    BRANS_ZATEN_KAYITLI(2001,"Girdiğiniz branş tipi sistemde zaten kayıtlıdır",HttpStatus.BAD_REQUEST),
    DOKTOR_BULUNAMADI(3001,"Doktor bulunamadı",HttpStatus.BAD_REQUEST),
    HASTA_BULUNAMADI(4001,"Hasta bulunamadı",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1002,"Geçersiz token",HttpStatus.BAD_REQUEST);
    
    
    
    
    int code;
    String message;
    HttpStatus httpStatus;
}

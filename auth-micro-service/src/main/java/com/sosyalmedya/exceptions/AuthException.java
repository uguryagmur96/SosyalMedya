/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sosyalmedya.exceptions;

import lombok.Getter;


/**
 *
 * @author Ugur
 * Bu kısımda uygulamanız icinde olusabilecek tüm hataları kapsayan bir kapsayıcıya ihtiyacınız var, yani hata tiplerinin listesini iceren
 * bir enum sınıfı olusturabilirsiniz. 
 * 
 */
@Getter
public class AuthException extends RuntimeException{
    private final ErrorType errorType;
    
    public AuthException(ErrorType errorType){
    super(errorType.getMessage());
    this.errorType=errorType;
    }
     public AuthException(ErrorType errorType, String message){
    super(errorType.getMessage());
    this.errorType=errorType;
    }
}

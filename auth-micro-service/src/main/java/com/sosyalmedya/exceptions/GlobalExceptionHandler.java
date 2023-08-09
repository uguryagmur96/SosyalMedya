/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sosyalmedya.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import static com.sosyalmedya.exceptions.ErrorType.*;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author Ugur
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
@ExceptionHandler(Exception.class)
@ResponseBody
    public ResponseEntity<String> iAmTheExceptionCatcher(Exception ex){
        String olusanHata=ex.toString();
        return ResponseEntity.ok("Beklenmedik bir hata olustu" + olusanHata);
    }
    
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> SpringStartExceptionHandler(AuthException ex){
        return new ResponseEntity<>(createErrorMessage(ex, ex.getErrorType()),ex.getErrorType().getHttpStatus());
    }
     @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handlePSQLException(
            DataIntegrityViolationException exception) {
        ErrorType errorType = REGISTER_KULLANICIADI_KAYITLI;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createErrorMessage(exception,errorType);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }
    /**
     * Tum hatalar belli bir metot uzerinden gectigi icin ek kodlamalar yapmadan tek bir yerden olusan 
     * hataların loglanması ve veritabanına kayıt edilmesini kolaylastirir.
     * 
     */
    private ErrorMessage createErrorMessage(Exception ex,ErrorType type){
        System.out.println("Hata oluştu...." + System.currentTimeMillis()+ "   " + ex.toString());
        log.error("Hata oluştu:   " + System.currentTimeMillis() + "  " + ex.toString());
     
        return ErrorMessage.builder()
                .message(type.getMessage())
                .code(type.getCode())
                .build();
    }
}

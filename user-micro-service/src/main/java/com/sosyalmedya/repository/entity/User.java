package com.sosyalmedya.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class User {
    @Id
    String id;
    /**
     * Bu kullanıcının hangi oturum hesabı ile ilişkilendirildiğinin bilinmesi gereklidir.
     * Auth kısmında oluşturulan kaydın id bilgisi burada tutulmalıdır
     */
    Long authId;
    String username;
    String name;
    String surname;
    String email;
    String profilephoto;
    Long memberSince;
    Long createat;
    Long updateat;
    int state;

}

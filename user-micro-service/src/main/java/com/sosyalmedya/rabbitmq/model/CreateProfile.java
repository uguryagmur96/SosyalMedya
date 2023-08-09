package com.sosyalmedya.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Çok ÖNEMLİ!!!
 * rabbitmq gibi yapılar sınıfları iletirken base 64 e çevirir. Bu nedenle bu sınıfları tanımlarken serileştirme
 * işlemleri yapmamız gerekir. Ayrıca dikkat edilmesi gereken diğer husus serileştirilen sınıfların iletilen
 * tarafta deserilize edilebilmesi için aynı paket içinde tanımlanmış olması gereklidir. Aksi halde hata alınır.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateProfile implements Serializable {
    Long authid;
    String username;
    String email;
}

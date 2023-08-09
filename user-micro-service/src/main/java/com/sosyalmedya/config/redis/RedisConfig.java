package com.sosyalmedya.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching // redis üzerinden cacheleme yapabilmek için spring cache i aktif ediyoruz.
@EnableRedisRepositories // redis repositorylerini kullanabilmek için gerekli olan anatasyon
public class RedisConfig {
    //@Value("${myapplication.redis.host}")
    //private String redisHost;
    //@Value("${myapplication.redis.port}")
    //private int redisPort;
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }
}

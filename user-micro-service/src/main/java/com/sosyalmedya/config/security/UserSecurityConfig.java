package com.sosyalmedya.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class UserSecurityConfig {
    @Bean
    JwtFilter getJwtFilter(){
        return new JwtFilter();
    }

    /**
     * Spring Application Context, uygulamanız başlatılırken gerekli olan tüm sınıflardan nesneler yaratarak context
     * içine atar ve gerekli olduğunda buradan kullanır.
     * Spring Security de SecurityFilterChain nesnesini context içine atar. Bu nesne ie filtreleme işlemleri yapar.
     * Biz bu sınıf için de bir SecurityFilterChain nesnesi oluşturarak yani bir Bean yaratarak bu nesnenin
     * özelliklerini değiştireceğiz.
     */
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    /**
     * Spring 6.0 ve üzeri için Security kullanımı
     * permitAll() : Herkese açık
     * hasRole("Admin") : Parantez içinde belirtilen rolde olmalı
     * DİKKAT !!! kişi kullanıcı adı şifresi ya da jwt ile giriş yapsa bile eğer belirtilen rolü yoksa giriş yapamaz
     */
        http.authorizeHttpRequests(req->
                req.requestMatchers("/api/v1/user/hello","swagger-ui/**","/v3/api-docs/**","/userrole/**")//
                        .permitAll()
                        .requestMatchers("/api/v1/user/**")// http://localhost:9092/api/v1/user/[herhangi bir istek]
                        .hasAuthority("USER") // user rolîne sahip olanların için
                       // .requestMatchers("/api/v1/**").hasAnyRole("Admin","SuperUser","Anyone")
                        //.anyRequest() // diğer bütün istekler
                        //.authenticated() // oturum açmış olmayı zorla
                );
    /**
     * DİKKAT !!! Restapi kullanırken form login işlemleri kullanılmaz. Aynı zamanda form işlemlerinde gelen isteklerini
     * kendi gönderdiğimiz form üzerinden gelmesini sağlamak için CSRF token kullanılır. Bu rest api için mümkün değildir.
     * Bu nedenle kapatmamız gerekir. Aksi taktirde 403 hata kodu alırsınız.
     */
    http.csrf(AbstractHttpConfigurer::disable); // csrf token kullanımını kapatır.
    /**
     * Filtreleme işleminden önce devreye girerek filtreleme işlemini manipüle ederek değiştiriyoruz.
     * Yani filtreleme yöntemini devralıyoruz.
     */
    http.addFilterBefore(getJwtFilter(), UsernamePasswordAuthenticationFilter.class);

    /**
     * SPring 6.0 öncesi için SPring Security kullanım örnekleri
     http
                .authorizeRequests()
                .anyRequest()// Herhangi bir istekte bulunursa
                .authenticated(); // oturum açmış olmayı zorla
        http.formLogin(Customizer.withDefaults()); // varsayılan form login sayfasını kullan
    */
        return http.build();
    }

}



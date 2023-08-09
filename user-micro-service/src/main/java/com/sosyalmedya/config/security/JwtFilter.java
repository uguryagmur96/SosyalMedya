package com.sosyalmedya.config.security;

import com.sosyalmedya.exceptions.ErrorType;
import com.sosyalmedya.exceptions.UserException;
import com.sosyalmedya.util.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUser jwtUser;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /**
         * RestAPI ye gelen istek şu şekilde ulaşır.
         * fetch('http://localhost:8080/api/test', {
         *     method: 'POST',
         *     headers: {
         *     'Content-Type': 'application/json',
         *     'Authorization': 'Bearer ' + token
         *     },
         *     body: JSON.stringify({
         *         userid: '2351231352',
         *         ip: '122.14.52.362'
         *         })
         *         })
         *         Burada dikkat ederseniz Header içinde token bilgisi Authorization Key ile gelir bu nedenle header
         *         içinden bu KEY in VALUE bilgisini almamız ve içinden token bilgisini ayırmamız gerekmektedir.
         */

        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){ // Header dolu mu ve Bearer ile başlıyor mu?
            String token=authorizationHeader.substring(7); // token bilgisini çek.
            Optional<Long> authId=jwtTokenManager.getByIdFromToken(token);
            if (authId.isEmpty()) throw new UserException(ErrorType.INVALID_TOKEN); // token geçersiz hatası fırlat
            UserDetails userDetails=jwtUser.getUserByAuthId(authId.get());
            UsernamePasswordAuthenticationToken userSpringToken=new UsernamePasswordAuthenticationToken(null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(userSpringToken);
        }
        filterChain.doFilter(request,response);
    }
}

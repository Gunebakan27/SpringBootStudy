package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passEncode(){ // bu method password encode ettigi icin password olan class'tan cagirilmali

        return new BCryptPasswordEncoder(10);// crypto güvenli seviyesi genelde 10 veya 8 kullanilir
    }
}

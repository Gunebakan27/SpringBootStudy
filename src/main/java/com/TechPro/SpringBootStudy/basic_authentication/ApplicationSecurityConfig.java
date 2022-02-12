package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration //class'i config olarak tanimlar
@EnableWebSecurity
// Tanimli oldugu class'ta form based security yerine(basic authentication) configure(ayarlama) eder.
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordConfig passEncode; // final obje bir deger almali. Bu degeri alacagi Autowired cons create edilmeli
   @Autowired
    public ApplicationSecurityConfig(PasswordConfig passEncode){
        this.passEncode=passEncode;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //ey springboot put delete patch calistir sorumluluk benim... Bu methodlarin block'unu kaldir
                .authorizeRequests()// Rewuestler icin yetki sorgula (get put patch delete post
                .antMatchers("/","index","/css/*","/js/*") // girilen url'lere izin veriyor
//                .not()  yazilan sayfalara girmesine izin vermez
                .permitAll()
//                .denyAll()

                .anyRequest() // her request icin
                .authenticated() // kullanici sorgula
                .and() // neye göre
                .httpBasic(); //httpBasic'e göre
        //her request'te App username ve password kontrol edilmeli logout yapmaya gerek kalmayacak
        // izin verilen kisiler Aptmana girdikten sonra herkes yetki sahibi olsun
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

     UserDetails student= User.builder().username("ali").password(passEncode.passEncode().encode("1111")).roles("AGA","BABA").build();
//     UserDetails student= User.builder().username("ali").password("1111").roles("AGA","BABA").build();
     UserDetails student1= User.builder().username("ayse").password(passEncode.passEncode().encode("1111")).roles("Hanim AGA","ANA").build();
//     UserDetails student1= User.builder().username("ayse").password("1111").roles("Hanim AGA","ANA").build();
     UserDetails admin= User.builder().username("admin").password(passEncode.passEncode().encode("1111")).roles("ADMIN").build();// convension tanim
//     UserDetails admin= User.builder().username("admin").password("1111").roles("ADMIN").build();// convension tanim
        return new InMemoryUserDetailsManager(student,student1,admin);
    }
}

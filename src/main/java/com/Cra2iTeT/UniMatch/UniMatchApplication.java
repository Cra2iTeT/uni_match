package com.Cra2iTeT.UniMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UniMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniMatchApplication.class, args);
    }

}

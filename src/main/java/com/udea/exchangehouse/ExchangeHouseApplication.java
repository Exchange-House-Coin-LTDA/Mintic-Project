package com.udea.exchangehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ExchangeHouseApplication {

    @GetMapping("/inicio")
    public String inicio(){
        return "Inicio";
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeHouseApplication.class, args);
    }

}
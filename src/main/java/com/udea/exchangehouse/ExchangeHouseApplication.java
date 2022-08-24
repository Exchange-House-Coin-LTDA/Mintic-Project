package com.udea.exchangehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ExchangeHouseApplication {

    @GetMapping("/empresa")
    public String empresa(){
        public Empresa empresa1 = new Empresa("Exchange House Coin", "Bogota", "3600566", "9002858585");
        String informacion = "Nombre de la empresa: " + empresa1.getNombre + "\nDireccion: " + empresa1.getDireccion + "\nTelefono: " + empresa1.getTelefono + "\nNIT: " + empresa1.getNIT;
        return informacion;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeHouseApplication.class, args);
    }

}

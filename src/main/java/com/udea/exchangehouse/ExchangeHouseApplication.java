package com.udea.exchangehouse;

import com.udea.exchangehouse.models.MovimientoDinero;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ExchangeHouseApplication {

    @GetMapping("/MovimientoDinero")
    public String movimientodinero(){
        //MovimientoDinero movimientodinero = new MovimientoDinero();
        return "Eeste es el movimiento Dinero";
    }

    public static void main(String[] args) {

        SpringApplication.run(ExchangeHouseApplication.class, args);
    }

}

package com.udea.exchangehouse;

import com.udea.exchangehouse.models.Empresa;
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
        Empresa empresa1 = new Empresa("Exchange House Coin", "Bogota", "3600566", "9002858585");
        String informacion = "<h1>Informacion de la empresa</h1>" + "<br><b>Nombre de la empresa: </b>" + empresa1.getNombre() + "<br><b>Direccion: </b>" + empresa1.getDireccion() + "<br><b>Telefono: </b>" + empresa1.getTelefono() + "<br><b>NIT: </b>" + empresa1.getNit();
        return informacion;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeHouseApplication.class, args);
    }

}

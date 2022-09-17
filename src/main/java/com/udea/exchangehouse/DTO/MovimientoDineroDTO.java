package com.udea.exchangehouse.DTO;

import com.udea.exchangehouse.models.Empleado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovimientoDineroDTO {

    private int id;
    private long monto;
    private String concepto;
    private Empleado usuario;
}

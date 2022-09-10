package com.udea.exchangehouse.DTO;

import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.models.MovimientoDinero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpleadoDTO {

    private int id;
    private String nombre;
    private String correo;

    private Empresa empresa;
    private String rol;
    private List<MovimientoDinero> movimientos;
}

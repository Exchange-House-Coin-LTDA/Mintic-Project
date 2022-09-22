package com.udea.exchangehouse.DTO;

import com.udea.exchangehouse.models.Empleado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaDTO {

    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String nit;
    private List<Empleado> empleados;
}

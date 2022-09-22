package com.udea.exchangehouse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String correo;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    private String rol;
    @OneToMany
    private List<MovimientoDinero> movimientos;

}
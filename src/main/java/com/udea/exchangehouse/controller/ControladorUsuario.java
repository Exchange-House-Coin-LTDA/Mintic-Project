package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.services.EmpleadoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorUsuario {

    @Autowired
    EmpleadoServ empleadoServ;

    @GetMapping("/users")
    public List<Empleado> verEmpleados(){
        return this.empleadoServ.todosLosEmpleados();
    }

    @PostMapping("/users")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        return this.empleadoServ.guardarActualizarEmpleado(empleado);
    }

    @GetMapping("/user/{id}")
    public Optional<Empleado> empleadoPorId(@PathVariable Integer id){
        return this.empleadoServ.empleadoPorId(id);
    }

    @PatchMapping("/user/{id}")
    public Empleado actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado){
        Empleado emp = this.empleadoServ.empleadoPorId(id).get();
        emp.setNombre(empleado.getNombre());
        emp.setCorreo(empleado.getCorreo());
        emp.setEmpresa(empleado.getEmpresa());
        emp.setRol(empleado.getRol());
        return this.empleadoServ.guardarActualizarEmpleado(emp);
    }

    @DeleteMapping("/user/{id}")
    public String eliminarEmpleado(@PathVariable Integer id){
        boolean respuesta = this.empleadoServ.borrarEmpleado(id);
        if(respuesta){
            return "Se ha eliminado el usuario con id: " + id;
        }
        return "No se ha podido eliminar el usuario con id: " + id;
    }
}

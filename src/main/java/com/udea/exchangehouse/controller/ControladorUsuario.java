package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.DTO.EmpleadoDTO;
import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.services.EmpleadoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorUsuario {

    private final EmpleadoServ empleadoServ;

    public ControladorUsuario(EmpleadoServ empleadoServ) {
        this.empleadoServ = empleadoServ;
    }

    @GetMapping("/users")
    public List<Empleado> verEmpleados(){
        return this.empleadoServ.todosLosEmpleados();
    }

    @PostMapping("/users")
    public Empleado agregarEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
        return this.empleadoServ.saveEmpleado(empleadoDTO);
    }

    @GetMapping("/user/{id}")
    public Optional<Empleado> empleadoPorId(@PathVariable Integer id){
        return this.empleadoServ.empleadoPorId(id);
    }

    @PatchMapping("/user/{id}")
    public Empleado actualizarEmpleado(@PathVariable Integer id, @RequestBody EmpleadoDTO empleadoDTO){
        if(this.empleadoServ.empleadoPorId(id).isPresent()){
            empleadoDTO.setId(id);
            return this.empleadoServ.actualizarEmpleado(empleadoDTO);
        }
        return null;
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

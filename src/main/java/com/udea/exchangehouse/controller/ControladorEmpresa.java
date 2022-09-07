package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.services.EmpresaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorEmpresa {
    @Autowired
    EmpresaServ empresaServ;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaServ.todasLasEmpresas();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaServ.guardarActualizarEmpresa(emp);
    }

    @GetMapping("/enterprises/{id}")
    public Optional<Empresa> empresaPorID(@PathVariable("id") Integer id){
        return this.empresaServ.empresaPorId(id);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa){
        Empresa emp= empresaServ.empresaPorId(id).get();
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNit(empresa.getNit());
        return empresaServ.guardarActualizarEmpresa(emp);
    }

    @DeleteMapping ("/enterprises/{id}")
    public String deleteEmpresa(@PathVariable("id") Integer id){
        boolean respuesta= empresaServ.borrarEmpresa(id);
        if (respuesta){
            return "Se elimino la empresa con id" +id;
        }
        else{
            return "No se pudo eliminar la empresa con id"+id;
        }
    }
}

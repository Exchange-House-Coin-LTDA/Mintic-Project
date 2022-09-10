package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.DTO.EmpresaDTO;
import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.services.EmpresaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorEmpresa {
    private final EmpresaServ empresaServ;

    public ControladorEmpresa(EmpresaServ empresaServ) {
        this.empresaServ = empresaServ;
    }

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaServ.todasLasEmpresas();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody EmpresaDTO empresaDTO){
        return this.empresaServ.saveEmpresa(empresaDTO);
    }

    @GetMapping("/enterprises/{id}")
    public Optional<Empresa> empresaPorID(@PathVariable("id") Integer id){
        return this.empresaServ.empresaPorId(id);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody EmpresaDTO empresaDTO){
        if(this.empresaServ.empresaPorId(id).isPresent()){
            empresaDTO.setId(id);
            return empresaServ.updateEmpresa(empresaDTO);
        }
        return null;
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

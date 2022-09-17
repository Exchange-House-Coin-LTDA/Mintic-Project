package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.DTO.EmpresaDTO;
import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.services.EmpresaServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ControladorEmpresa {
    private final EmpresaServ empresaServ;

    public ControladorEmpresa(EmpresaServ empresaServ) {
        this.empresaServ = empresaServ;
    }

    //Consultar empresas
    @GetMapping("/enterprises")
    public String verEmpresas(Model model){
        List<Empresa> empresas = this.empresaServ.todasLasEmpresas();
        List<EmpresaDTO> empresasDTO = new ArrayList<>();
        empresas.forEach(empresa -> empresasDTO.add(new EmpresaDTO(empresa.getId(),
                empresa.getNombre(),empresa.getDireccion(),empresa.getTelefono(),empresa.getNit(),
                empresa.getEmpleados())));
        model.addAttribute("empresas", empresasDTO);
        return "verEmpresas";
    }

    //Agregar empresa
    @GetMapping("/adEnterprise")
    public String nuevaEmpresa(Model model){
        EmpresaDTO empresa = new EmpresaDTO();
        model.addAttribute("empresa", empresa);
        return "agregarEmpresa";
    }

    //Guardar empresa
    @PostMapping("/enterprises")
    public String guardarEmpresa(EmpresaDTO empresaDTO){
        System.out.println(empresaDTO);
        this.empresaServ.saveEmpresa(empresaDTO);
        return "redirect:/enterprises";
    }

    //Editar empresa por ID
    @GetMapping("/enterprises/{id}")
    public String empresaPorID(@PathVariable("id") Integer id, Model model){
        Empresa empresa = this.empresaServ.empresaPorId(id).get();
        EmpresaDTO empresaDTO = new EmpresaDTO(empresa.getId(), empresa.getNombre(), empresa.getDireccion(),
                empresa.getTelefono(), empresa.getNit(), empresa.getEmpleados());
        model.addAttribute("empresa", empresaDTO);
        return "editarEmpresa";
    }

    @PostMapping("/updateEnterprise")
    public String actualizarEmpresa(EmpresaDTO empresaDTO){
        this.empresaServ.updateEmpresa(empresaDTO);
        return "redirect:/enterprises";
    }

    @GetMapping ("/deleteEnterprise/{id}")
    public String deleteEmpresa(@PathVariable("id") Integer id){
        this.empresaServ.borrarEmpresa(id);
        return "redirect:/enterprises";

    }
}

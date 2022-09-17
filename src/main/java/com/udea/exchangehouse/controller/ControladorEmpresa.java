package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.DTO.EmpleadoDTO;
import com.udea.exchangehouse.DTO.EmpresaDTO;
import com.udea.exchangehouse.DTO.MovimientoDineroDTO;
import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.services.EmpleadoServ;
import com.udea.exchangehouse.services.EmpresaServ;
import com.udea.exchangehouse.services.MovimientoDineroServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorEmpresa {
    private final EmpresaServ empresaServ;
    private final EmpleadoServ empleadoServ;
    private final MovimientoDineroServ movimientoDineroServ;

    public ControladorEmpresa(EmpresaServ empresaServ, EmpleadoServ empleadoServ, MovimientoDineroServ movimientoDineroServ) {
        this.empresaServ = empresaServ;
        this.empleadoServ = empleadoServ;
        this.movimientoDineroServ = movimientoDineroServ;
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

    @GetMapping("/enterprise/{id}/movements")
    public String movimientosPorEmpresa(@PathVariable Integer id, Model model){
        List<MovimientoDinero> movimientos= this.movimientoDineroServ.movimientosPorEmpresa(id);
        List<MovimientoDineroDTO> movimientoDineroDTOS = new ArrayList<>();
        movimientos.forEach(movimiento -> movimientoDineroDTOS.add(new MovimientoDineroDTO(movimiento.getId(), movimiento.getMonto(),
                movimiento.getConcepto(), movimiento.getUsuario())));
        Long total = this.movimientoDineroServ.sumarMovimientos();
        model.addAttribute("movimientos", movimientoDineroDTOS);
        model.addAttribute("total", total);
        return "verMovimientos";
    }

    @GetMapping("/enterprises/{id}/users")
    public String verEmpleadosPorEmpresa(@PathVariable Integer id, Model model){
        List<Empleado> empleados = this.empleadoServ.obtenerPorEmpresa(id);
        List<EmpleadoDTO> empleadoDTOS = new ArrayList<>();
        empleados.forEach(empleado -> empleadoDTOS.add(new EmpleadoDTO(empleado.getId(),
                empleado.getNombre(), empleado.getCorreo(), empleado.getPassword(), empleado.getEmpresa(),
                empleado.getRol(), empleado.getMovimientos())));
        model.addAttribute("empleados", empleadoDTOS);
        return "verEmpleados";
    }
}

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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ControladorUsuario {

    private final EmpleadoServ empleadoServ;
    private final EmpresaServ empresaServ;
    private final MovimientoDineroServ movimientoDineroServ;

    public ControladorUsuario(EmpleadoServ empleadoServ, EmpresaServ empresaServ, MovimientoDineroServ movimientoDineroServ) {
        this.empleadoServ = empleadoServ;
        this.empresaServ = empresaServ;
        this.movimientoDineroServ = movimientoDineroServ;
    }

    //consultar lista de empleados
    @GetMapping("/users")
    public String verEmpleados(Model model){
        List<Empleado> empleados = this.empleadoServ.todosLosEmpleados();
        List<EmpleadoDTO> empleadoDTOS = new ArrayList<>();
        empleados.forEach(empleado -> empleadoDTOS.add(new EmpleadoDTO(empleado.getId(),
                empleado.getNombre(), empleado.getCorreo(), empleado.getEmpresa(),
                empleado.getRol(), empleado.getMovimientos())));
        model.addAttribute("empleados", empleadoDTOS);
        return "verEmpleados";
    }

    //Agregar Empleado
    @GetMapping("/adUser")
    public String nuevaEmpleado(Model model){
        EmpleadoDTO empleado = new EmpleadoDTO();
        List<Empresa> empresas = this.empresaServ.todasLasEmpresas();
        List<EmpresaDTO> empresasDTO = new ArrayList<>();
        empresas.forEach(empresa -> empresasDTO.add(new EmpresaDTO(empresa.getId(),
                empresa.getNombre(),empresa.getDireccion(),empresa.getTelefono(),empresa.getNit(),
                empresa.getEmpleados())));
        model.addAttribute("empresas", empresasDTO);
        model.addAttribute("empleado", empleado);
        return "agregarEmpleado";
    }

    //Guardar empleado
    @PostMapping("/users")
    public String agregarEmpleado(EmpleadoDTO empleadoDTO){
        this.empleadoServ.saveEmpleado(empleadoDTO);
        return "redirect:/users";
    }

    //Editar empleado
    @GetMapping("/user/{id}")
    public String empleadoPorId(@PathVariable Integer id, Model model){
        Empleado empleado = this.empleadoServ.empleadoPorId(id).get();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(empleado.getId(), empleado.getNombre(), empleado.getCorreo(),
                empleado.getEmpresa(),empleado.getRol(),empleado.getMovimientos());
        List<Empresa> empresas = this.empresaServ.todasLasEmpresas();
        List<EmpresaDTO> empresasDTO = new ArrayList<>();
        empresas.forEach(empresa -> empresasDTO.add(new EmpresaDTO(empresa.getId(),
                empresa.getNombre(),empresa.getDireccion(),empresa.getTelefono(),empresa.getNit(),
                empresa.getEmpleados())));
        model.addAttribute("empresas", empresasDTO);
        model.addAttribute("empleado", empleadoDTO);
        return "editarEmpleado";
    }

    //Actualizar empleado
    @PostMapping("/updateUser")
    public String actualizarEmpleado(EmpleadoDTO empleadoDTO){
        this.empleadoServ.actualizarEmpleado(empleadoDTO);
        return "redirect:/users";
    }

    //Eliminar empleado
    @GetMapping("/deleteUser/{id}")
    public String eliminarEmpleado(@PathVariable Integer id){
        this.empleadoServ.borrarEmpleado(id);
        return "redirect:/users";
    }

    @GetMapping("/user/{id}/movements")
    public String movimientosPorEmpleado(@PathVariable Integer id, Model model){
        List<MovimientoDinero> movimientos= this.movimientoDineroServ.movimientosPorEmpleado(id);
        List<MovimientoDineroDTO> movimientoDineroDTOS = new ArrayList<>();
        movimientos.forEach(movimiento -> movimientoDineroDTOS.add(new MovimientoDineroDTO(movimiento.getId(), movimiento.getMonto(),
                movimiento.getConcepto(), movimiento.getUsuario())));
        Long total = this.movimientoDineroServ.sumarMovimientos();
        model.addAttribute("movimientos", movimientoDineroDTOS);
        model.addAttribute("total", total);
        return "verMovimientos";
    }
}

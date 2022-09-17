package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.DTO.EmpleadoDTO;
import com.udea.exchangehouse.DTO.MovimientoDineroDTO;
import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.services.EmpleadoServ;
import com.udea.exchangehouse.services.MovimientoDineroServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ControladorMovimiento {

    private final MovimientoDineroServ movimientoDineroServ;
    private final EmpleadoServ empleadoServ;

    public ControladorMovimiento(MovimientoDineroServ movimientoDineroServ, EmpleadoServ empleadoServ) {
        this.movimientoDineroServ = movimientoDineroServ;
        this.empleadoServ = empleadoServ;
    }

    //Consultar movimientos
    @GetMapping("/movements")
    public String verMovimientos(Model model){
        List<MovimientoDinero> movimientos= this.movimientoDineroServ.getAllMovimientos();
        List<MovimientoDineroDTO> movimientoDineroDTOS = new ArrayList<>();
        movimientos.forEach(movimiento -> movimientoDineroDTOS.add(new MovimientoDineroDTO(movimiento.getId(), movimiento.getMonto(),
                movimiento.getConcepto(), movimiento.getUsuario())));
        Long total = this.movimientoDineroServ.sumarMovimientos();
        model.addAttribute("movimientos", movimientoDineroDTOS);
        model.addAttribute("total", total);
        return "verMovimientos";
    }

    //Agregar Movimiento
    @GetMapping("/adMovement")
    public String agregarMovimiento(Model model){
        MovimientoDineroDTO movimientoDineroDTO = new MovimientoDineroDTO();
        List<Empleado> empleados = this.empleadoServ.todosLosEmpleados();
        List<EmpleadoDTO> empleadoDTOS = new ArrayList<>();
        empleados.forEach(empleado -> empleadoDTOS.add(new EmpleadoDTO(empleado.getId(),
                empleado.getNombre(), empleado.getCorreo(), empleado.getPassword(), empleado.getEmpresa(),
                empleado.getRol(), empleado.getMovimientos())));
        model.addAttribute("movimiento", movimientoDineroDTO);
        model.addAttribute("usuarios", empleadoDTOS);
        return "agregarMovimiento";
    }

    //Guardar Movimiento
    @PostMapping("/movements")
    public String agregarMovimiento(MovimientoDineroDTO movimientoDineroDTO){
        this.movimientoDineroServ.saveMovimiento(movimientoDineroDTO);
        return "redirect:/movements";
    }

    //Editar movimiento
    @GetMapping("/movement/{id}")
    public String movimientoPorId(@PathVariable Integer id, Model model){
        MovimientoDinero movimientoDinero = this.movimientoDineroServ.getMovimientoById(id).get();
        MovimientoDineroDTO movimientoDineroDTO = new MovimientoDineroDTO(movimientoDinero.getId(),
                movimientoDinero.getMonto(), movimientoDinero.getConcepto(), movimientoDinero.getUsuario());
        List<Empleado> usuarios = this.empleadoServ.todosLosEmpleados();
        List<EmpleadoDTO> usuariosDTO = new ArrayList<>();
        usuarios.forEach(usuario -> usuariosDTO.add(new EmpleadoDTO(usuario.getId(), usuario.getNombre(),
                usuario.getCorreo(), usuario.getPassword(), usuario.getEmpresa(), usuario.getRol(),
                usuario.getMovimientos())));
        model.addAttribute("movimiento", movimientoDineroDTO);
        model.addAttribute("usuarios", usuariosDTO);
        return "editarMovimiento";
    }

    //Actualizar movimiento
    @PostMapping("/updateMovement")
    public String actualizarMovimiento(MovimientoDineroDTO movimientoDineroDTO){
        this.movimientoDineroServ.updateMovimiento(movimientoDineroDTO);
        return "redirect:/movements";
    }

    //Eliminar movimiento
    @GetMapping("/deleteMovement/{id}")
    public String eliminarMovimiento(@PathVariable Integer id){
        this.movimientoDineroServ.deleteMovimiento(id);
        return "redirect:/movements";
    }




}

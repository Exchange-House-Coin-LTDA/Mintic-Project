package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.DTO.MovimientoDineroDTO;
import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.services.MovimientoDineroServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorMovimiento {

    private final MovimientoDineroServ movimientoDineroServ;

    public ControladorMovimiento(MovimientoDineroServ movimientoDineroServ) {
        this.movimientoDineroServ = movimientoDineroServ;
    }

    @GetMapping("/movements")
    public List<MovimientoDinero> verMovimientos(){
        return this.movimientoDineroServ.getAllMovimientos();
    }

    @PostMapping("/movements")
    public MovimientoDinero agregarMovimiento(@RequestBody MovimientoDineroDTO movimientoDineroDTO){
        return this.movimientoDineroServ.saveMovimiento(movimientoDineroDTO);
    }

    @GetMapping("/movement/{id}")
    public Optional<MovimientoDinero> movimientoPorId(@PathVariable Integer id){
        return this.movimientoDineroServ.getMovimientoById(id);
    }

    @PatchMapping("/movement/{id}")
    public MovimientoDinero actualizarMovimiento(@PathVariable Integer id, @RequestBody MovimientoDineroDTO movimientoDineroDTO){
        if(this.movimientoDineroServ.getMovimientoById(id).isPresent()){
            movimientoDineroDTO.setId(id);
            return this.movimientoDineroServ.updateMovimiento(movimientoDineroDTO);
        }
        return null;
    }

    @DeleteMapping("/movement/{id}")
    public String eliminarMovimiento(@PathVariable Integer id){
        boolean respuesta = this.movimientoDineroServ.deleteMovimiento(id);
        if(respuesta){
            return "Se ha eliminado el movimiento con id: " + id;
        }
        return "No se ha podido eliminar el movimiento con id: " + id;
    }

    @GetMapping("/enterprise/{id}/movements")
    public List<MovimientoDinero> movimientosPorEmpresa(@PathVariable Integer id){
        return this.movimientoDineroServ.movimientosPorEmpresa(id);
    }

    @GetMapping("/user/{id}/movements")
    public List<MovimientoDinero> movimientosPorEmpleado(@PathVariable Integer id){
        return this.movimientoDineroServ.movimientosPorEmpleado(id);
    }
}

package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.services.MovimientoDineroServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorMovimiento {

    @Autowired
    MovimientoDineroServ movimientoDineroServ;

    @GetMapping("/movements")
    public List<MovimientoDinero> verMovimientos(){
        return this.movimientoDineroServ.getAllMovimientos();
    }

    @PostMapping("/movements")
    public MovimientoDinero agregarMovimiento(@RequestBody MovimientoDinero movimientoDinero){
        return this.movimientoDineroServ.saveOrUpdateMovimiento(movimientoDinero);
    }

    @GetMapping("/movement/{id}")
    public Optional<MovimientoDinero> movimientoPorId(@PathVariable Integer id){
        return this.movimientoDineroServ.getMovimientoById(id);
    }

    @PatchMapping("/movement/{id}")
    public MovimientoDinero actualizarMovimiento(@PathVariable Integer id, @RequestBody MovimientoDinero movimientoDinero){
        MovimientoDinero mov = this.movimientoDineroServ.getMovimientoById(id).get();
        mov.setMonto(movimientoDinero.getMonto());
        mov.setConcepto(movimientoDinero.getConcepto());
        mov.setUsuario(movimientoDinero.getUsuario());
        return this.movimientoDineroServ.saveOrUpdateMovimiento(mov);
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

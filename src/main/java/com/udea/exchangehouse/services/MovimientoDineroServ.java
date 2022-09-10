package com.udea.exchangehouse.services;

import com.udea.exchangehouse.DTO.MovimientoDineroDTO;
import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.repository.MovimientoDineroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoDineroServ {
    @Autowired
    MovimientoDineroRepo movimientoDineroRepo;

    public List<MovimientoDinero> getAllMovimientos(){
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientoDineroRepo.findAll().forEach(movimiento -> movimientosList.add(movimiento));
        return movimientosList;
    }

    public Optional<MovimientoDinero> getMovimientoById(Integer id){
        return movimientoDineroRepo.findById(id);
    }

    public MovimientoDinero saveMovimiento(MovimientoDineroDTO movimientoDineroDTO){
        MovimientoDinero movimientoDinero = new MovimientoDinero();
        movimientoDinero.setMonto(movimientoDineroDTO.getMonto());
        movimientoDinero.setConcepto(movimientoDineroDTO.getConcepto());
        movimientoDinero.setUsuario(movimientoDineroDTO.getUsuario());
        return this.movimientoDineroRepo.save(movimientoDinero);
    }

    public MovimientoDinero updateMovimiento(MovimientoDineroDTO movimientoDineroDTO){
        MovimientoDinero movimientoDinero = new MovimientoDinero();
        movimientoDinero.setId(movimientoDineroDTO.getId());
        movimientoDinero.setMonto(movimientoDineroDTO.getMonto());
        movimientoDinero.setConcepto(movimientoDineroDTO.getConcepto());
        movimientoDinero.setUsuario(movimientoDineroDTO.getUsuario());
        return this.movimientoDineroRepo.save(movimientoDinero);
    }

    public boolean deleteMovimiento (Integer id){
        movimientoDineroRepo.deleteById(id);
        if(this.movimientoDineroRepo.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    public ArrayList<MovimientoDinero> movimientosPorEmpleado (Integer id) {
        return movimientoDineroRepo.findByEmpleado(id);
    }

    public ArrayList<MovimientoDinero> movimientosPorEmpresa (Integer id) {
        return movimientoDineroRepo.findByEmpresa(id);
    }
}

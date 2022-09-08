package com.udea.exchangehouse.services;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.repository.EmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServ {
    @Autowired
    EmpleadoRepo empleadoRepo;

    public List<Empleado> todosLosEmpleados(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepo.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    public Optional<Empleado> empleadoPorId(Integer id){
        return empleadoRepo.findById(id);
    }

    public Empleado guardarActualizarEmpleado(Empleado empleado){
        Empleado empl=empleadoRepo.save(empleado);
        return empl;
    }

    public boolean borrarEmpleado(Integer id){
        empleadoRepo.deleteById(id);

        if (empleadoRepo.findById(id)!=null){
            return true;
        }
        return false;
    }

}

package com.udea.exchangehouse.services;

import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServ {
    @Autowired
    EmpresaRepo empresaRepo;

    public List<Empresa> todasLasEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepo.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Optional<Empresa> empresaPorId(Integer id){
        return empresaRepo.findById(id);
    }

    public Empresa guardarActualizarEmpresa(Empresa empresa){
        Empresa emp=empresaRepo.save(empresa);
        return emp;
    }

    public boolean borrarEmpresa(Integer id){
        empresaRepo.deleteById(id);

        if (empresaRepo.findById(id)!=null){
            return true;
        }
        return false;
    }

}

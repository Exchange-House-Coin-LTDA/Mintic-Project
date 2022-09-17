package com.udea.exchangehouse.services;

import com.udea.exchangehouse.DTO.EmpresaDTO;
import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServ {

    private final EmpresaRepo empresaRepo;

    public EmpresaServ(EmpresaRepo empresaRepo) {
        this.empresaRepo = empresaRepo;
    }

    public List<Empresa> todasLasEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        this.empresaRepo.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Optional<Empresa> empresaPorId(Integer id){
        return this.empresaRepo.findById(id);
    }

    public Empresa saveEmpresa(EmpresaDTO empresaDTO){
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setDireccion(empresaDTO.getDireccion());
        empresa.setTelefono(empresaDTO.getTelefono());
        empresa.setNit(empresaDTO.getNit());
        return this.empresaRepo.save(empresa);
    }

    public Empresa updateEmpresa(EmpresaDTO empresaDTO){
        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.getId());
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setDireccion(empresaDTO.getDireccion());
        empresa.setTelefono(empresaDTO.getTelefono());
        empresa.setNit(empresaDTO.getNit());
        return this.empresaRepo.save(empresa);
    }

    public boolean borrarEmpresa(Integer id){
        this.empresaRepo.deleteById(id);

        if (this.empresaRepo.findById(id)!=null){
            return true;
        }
        return false;
    }

}

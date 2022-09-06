package com.udea.exchangehouse.repository;

import com.udea.exchangehouse.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepo extends JpaRepository <Empresa, Integer> {
}

package com.udea.exchangehouse.repository;

import com.udea.exchangehouse.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepo extends JpaRepository <Empleado, Integer> {
}

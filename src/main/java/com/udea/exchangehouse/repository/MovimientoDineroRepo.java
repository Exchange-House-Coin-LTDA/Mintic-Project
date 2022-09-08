package com.udea.exchangehouse.repository;

import com.udea.exchangehouse.models.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoDineroRepo extends JpaRepository<MovimientoDinero, Integer> {
    @Query(value = "SELECT * FROM movimiento where empleado_id=?1", nativeQuery=true)
    ArrayList<MovimientoDinero> findByEmpleado(Integer id);

    @Query(value = "select * from movimiento where empleado_id in (select id from empleado where empresa_id=?1)", nativeQuery = true)
    ArrayList<MovimientoDinero> findByEmpresa(Integer id);
}

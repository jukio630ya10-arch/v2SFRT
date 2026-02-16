package com.cibertec.EFSRTII_proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.EFSRTII_proyecto.model.MovimientoInventario;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long>{
	   List<MovimientoInventario> findByTipo(String tipo);
}

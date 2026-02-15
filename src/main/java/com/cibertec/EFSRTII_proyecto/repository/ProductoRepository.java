package com.cibertec.EFSRTII_proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.EFSRTII_proyecto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

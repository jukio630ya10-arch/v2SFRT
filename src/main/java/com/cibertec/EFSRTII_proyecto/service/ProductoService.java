package com.cibertec.EFSRTII_proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.EFSRTII_proyecto.model.Producto;
import com.cibertec.EFSRTII_proyecto.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto guardar(Producto p) {
        return repository.save(p);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    
}

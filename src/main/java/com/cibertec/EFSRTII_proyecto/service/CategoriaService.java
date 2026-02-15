package com.cibertec.EFSRTII_proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.EFSRTII_proyecto.model.Categoria;
import com.cibertec.EFSRTII_proyecto.repository.CategoriaRepository;


@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public Categoria guardar(Categoria categoria) {
        return repository.save(categoria);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

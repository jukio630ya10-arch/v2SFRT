package com.cibertec.EFSRTII_proyecto.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.EFSRTII_proyecto.model.MovimientoInventario;
import com.cibertec.EFSRTII_proyecto.model.Producto;
import com.cibertec.EFSRTII_proyecto.repository.MovimientoInventarioRepository;
import com.cibertec.EFSRTII_proyecto.repository.ProductoRepository;

@Service
@Transactional
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<MovimientoInventario> listar() {
        return movimientoRepository.findAll();
    }

    public MovimientoInventario guardar(MovimientoInventario movimiento) {

        Producto producto = productoRepository.findById(
                movimiento.getProducto().getId()
        ).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (movimiento.getTipo().equals("ENTRADA")) {
            producto.setStock(producto.getStock() + movimiento.getCantidad());
        } else if (movimiento.getTipo().equals("SALIDA")) {

            if (producto.getStock() < movimiento.getCantidad()) {
                throw new RuntimeException("Stock insuficiente");
            }

            producto.setStock(producto.getStock() - movimiento.getCantidad());
        }

        productoRepository.save(producto);
        movimiento.setFecha(LocalDate.now());

        return movimientoRepository.save(movimiento);
    }
    public List<MovimientoInventario> listarSalidas() {
        return movimientoRepository.findByTipo("SALIDA");
    }

    public List<MovimientoInventario> listarEntradas() {
        return movimientoRepository.findByTipo("ENTRADA");
    }

    public void eliminar(Long id) {
        movimientoRepository.deleteById(id);
    }
}

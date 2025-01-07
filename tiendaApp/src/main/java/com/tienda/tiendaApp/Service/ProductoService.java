package com.tienda.tiendaApp.Service;

import com.tienda.tiendaApp.Model.Producto;
import com.tienda.tiendaApp.Repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository) {

        this.productoRepository = productoRepository;
    }

    public String crearProducto(Producto producto) {
        productoRepository.save(producto);
        return "Producto creado";
    }


    public String actualizarProducto(Long codigodebarras, @RequestBody Producto producto) {
        Producto productoActualizado = productoRepository.findById(codigodebarras).get();
        productoActualizado.setNombre(producto.getNombre());
        productoActualizado.setDescripcion(producto.getDescripcion());
        productoActualizado.setPrecio(producto.getPrecio());
        productoActualizado.setStock(producto.getStock());
        productoRepository.save(productoActualizado);
        return "Producto actualizado";
    }
    public String borrarProducto(Long codigodebarras) {
        productoRepository.deleteById(codigodebarras);
        return "Producto eliminado";
    }
    public Producto obtenerProducto( Long codigodebarras) {
        Producto producto = productoRepository.findById(codigodebarras).get();
        return producto;

    }
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }
}

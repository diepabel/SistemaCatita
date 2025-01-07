package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.Model.Producto;
import com.tienda.tiendaApp.Repository.ProductoRepository;
import com.tienda.tiendaApp.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductoController {

      @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/Producto/{nombre}/{codigodebarras}/{descripcion}/{precio}/{stock}")
    public String crearProducto(@RequestBody Producto producto) {
        productoRepository.save(producto);
        return productoService.crearProducto(producto);
            }
    @PutMapping("/Producto/{codigodebarras}")
     public String actualizarProducto(@PathVariable Long codigodebarras, @RequestBody Producto producto) {
        return productoService.actualizarProducto(codigodebarras, producto);
    }
    @DeleteMapping("/Producto/{codigodebarras}")
    public String borrarProducto(@PathVariable Long codigodebarras) {
        return productoService.borrarProducto(codigodebarras);
    }
    @GetMapping("/Producto/{codigodebarras}")
    public Producto obtenerProducto(@PathVariable Long codigodebarras) {
        return productoService.obtenerProducto(codigodebarras);
    }
    @GetMapping("/Productos")
    public List<Producto> obtenerProductos() {

        return productoService.obtenerProductos();
    }


}

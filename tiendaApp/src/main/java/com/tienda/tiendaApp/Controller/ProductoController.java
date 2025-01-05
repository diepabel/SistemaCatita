package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.Model.Producto;
import com.tienda.tiendaApp.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    @PostMapping("/Producto/{nombre}/{codigodebarras}/{descripcion}/{precio}/{stock}")
    public String crearProducto(@PathVariable String nombre,@PathVariable String codigodebarras, @PathVariable String descripcion, @PathVariable Double precio , @PathVariable int stock ) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCodigodebarras(codigodebarras);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        productoRepository.save(producto);
        return "Producto creado";
    }
    @PutMapping ("/Producto/{id}/{nombre}/{codigodebarras}/{descripcion}/{precio}/{stock}")
    public String actualizarProducto(@PathVariable Long id, @PathVariable String nombre,@PathVariable String codigodebarras, @PathVariable String descripcion, @PathVariable Double precio, @PathVariable int stock) {
        Producto producto = productoRepository.findProductoByCodigodebarras(codigodebarras);
        producto.setNombre(nombre);
        producto.setCodigodebarras(codigodebarras);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        productoRepository.save(producto);
        return "Producto actualizado";
    }
    @DeleteMapping("/Producto/{codigodebarras}")
    public String borrarProducto(@PathVariable String codigodebarras) {
        Producto producto = productoRepository.deleteProductoByCodigodebarras(codigodebarras);
        return "Producto eliminado";
    }
    @GetMapping("/Producto/{codigodebarras}")
    public Producto obtenerProducto(@PathVariable String codigodebarras) {
        Producto producto  = productoRepository.findProductoByCodigodebarras(codigodebarras);
        return producto;
    }
//    @GetMapping("/Producto")
//    public List<Map<String, Object>> obtenerProductos() {
//        List<Map<String, Object>> productosSinDescripcion = new ArrayList<>();
//        for (Producto producto : productoRepository.findAll()) {
//            Map<String, Object> productoMap = new HashMap<>();
//            productoMap.put("id", producto.getId());
//            productoMap.put("nombre", producto.getNombre());
//            productoMap.put("precio", producto.getPrecio());
//            productoMap.put("stock", producto.getStock());
//            productosSinDescripcion.add(productoMap);
//        }
//        return productosSinDescripcion;
//    }

}

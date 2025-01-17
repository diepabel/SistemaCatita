package com.tienda.tiendaApp.Controller;


import com.tienda.tiendaApp.Model.Proveedor;
import com.tienda.tiendaApp.Repository.ProveedorRepository;
import com.tienda.tiendaApp.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/Proveedor/crear")
    public String crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.crearProveedor(proveedor);
    }
    @PutMapping("/Proveedor/{id}")
    public String actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.actualizarProveedor(id, proveedor);
    }
    @DeleteMapping("/Proveedor/{id}")
    public String borrarProveedor(@PathVariable Long id) {
        return proveedorService.borrarProveedor(id);
    }

    @GetMapping("/Proveedor/{id}")
    public Proveedor obtenerProveedor(@PathVariable Long id) {
        return proveedorService.obtenerProveedor(id);
    }

    @GetMapping("/Proveedores")
    public List<Proveedor> obtenerProveedores() {
        return proveedorService.obtenerProveedores();
    }

}

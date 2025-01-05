package com.tienda.tiendaApp.Controller;


import com.tienda.tiendaApp.Model.Proveedor;
import com.tienda.tiendaApp.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProveedorController {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @PostMapping("/Proveedor/{nombre}/{direccion}/{telefono}/{email}")
    public String crearProveedor(@PathVariable String nombre, @PathVariable String direccion, @PathVariable String telefono, @PathVariable String email) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);
        return "Proveedor creado";
    }
    @PutMapping("/Proveedor/{id}/{nombre}/{direccion}/{telefono}/{email}")
    public String actualizarProveedor(@PathVariable Long id, @PathVariable String nombre, @PathVariable String direccion, @PathVariable String telefono, @PathVariable String email) {
        Proveedor proveedor = proveedorRepository.findById(id).get();
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);
        proveedorRepository.save(proveedor);
        return "Proveedor actualizado";
    }

    @DeleteMapping("/Proveedor/{id}")
    public String borrarProveedor(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
        return "Proveedor eliminado";
    }

    @GetMapping("/Proveedor/{id}")
    public String obtenerProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).get();
        return "Proveedores registrados";

    }
}

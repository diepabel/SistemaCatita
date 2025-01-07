package com.tienda.tiendaApp.Service;

import com.tienda.tiendaApp.Model.Proveedor;
import com.tienda.tiendaApp.Repository.ProveedorRepository;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProveedorService {

    private ProveedorRepository proveedorRepository;
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public String crearProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return "Proveedor creado";
    }
    public String actualizarProveedor(Long id, Proveedor proveedor) {
        Proveedor proveedorActualizado= proveedorRepository.findById(id).get();
        proveedorActualizado.setNombre(proveedor.getNombre());
        proveedorActualizado.setDireccion(proveedor.getDireccion());
        proveedorActualizado.setTelefono(proveedor.getTelefono());
        proveedorActualizado.setEmail(proveedor.getEmail());
        proveedorRepository.save(proveedor);
        return "Proveedor actualizado";
    }
    public String borrarProveedor(Long id) {
        proveedorRepository.deleteById(id);
        return "Proveedor eliminado";
    }
    public Proveedor obtenerProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).get();
        return proveedor;
    }
    public List<Proveedor> obtenerProveedores() {
        return proveedorRepository.findAll();
    }


}

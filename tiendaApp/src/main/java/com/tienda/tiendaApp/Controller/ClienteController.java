package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.Model.Cliente;
import com.tienda.tiendaApp.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @PostMapping("/Cliente/{nombre}/{direccion}/{telefono}/{email}")
    public String crearCliente(@PathVariable String nombre, @PathVariable String direccion, @PathVariable String telefono, @PathVariable String email) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        clienteRepository.save(cliente);
        return "Cliente creado";
    }
    @PutMapping("/Cliente/{id}/{nombre}/{direccion}/{telefono}/{email}")
    public String actualizarCliente(@PathVariable Long id, @PathVariable String nombre, @PathVariable String direccion, @PathVariable String telefono, @PathVariable String email) {
        Cliente cliente = clienteRepository.findById(id).get();
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        clienteRepository.save(cliente);
        return "Cliente actualizado";
    }

    @DeleteMapping("/Cliente/{id}")
    public String borrarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "cliente eliminado";
    }

    @GetMapping("/Cliente/{id}")
    public String obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).get();


        return "Clientes registrados";
    }
}

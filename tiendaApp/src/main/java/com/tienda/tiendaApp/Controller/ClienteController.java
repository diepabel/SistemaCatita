package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.Model.Cliente;
import com.tienda.tiendaApp.Repository.ClienteRepository;
import com.tienda.tiendaApp.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/Cliente/crear")
    public String crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @PutMapping("/Cliente/{id}")
    public String actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.actualizarCliente( id, cliente);
    }

    @DeleteMapping("/Cliente/{id}")
    public String borrarCliente(@PathVariable Long id) {
        return clienteService.borrarCliente(id);
    }

    @GetMapping("/Cliente/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerCliente(id);
    }

    @GetMapping("/Clientes")
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerClientes();
    }
}
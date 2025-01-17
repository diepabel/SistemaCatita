package com.tienda.tiendaApp.Service;

import com.tienda.tiendaApp.Model.Cliente;
import com.tienda.tiendaApp.Repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


        public String crearCliente(Cliente cliente) {
        clienteRepository.save(cliente);
             return "Cliente creado";
    }

    public String actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteActualizado = clienteRepository.findById(id).get();
        clienteActualizado.setNombre(cliente.getNombre());
        clienteActualizado.setDireccion(cliente.getDireccion());
        clienteActualizado.setTelefono(cliente.getTelefono());
        clienteActualizado.setEmail(cliente.getEmail());
        clienteRepository.save(clienteActualizado);
           return "Cliente actualizado";
    }


    public String borrarCliente(Long id) {
        clienteRepository.deleteById(id);
        return "Cliente eliminado";
    }

    public Cliente obtenerCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).get();
        return cliente;
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }
}

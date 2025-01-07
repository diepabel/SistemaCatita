package com.tienda.tiendaApp.Repository;

import com.tienda.tiendaApp.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findClienteById(Integer id);

    Cliente deleteClienteById(Integer id);
}

package com.tienda.tiendaApp.Repository;

import com.tienda.tiendaApp.Model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Proveedor findProveedorByEmail(String email);
}

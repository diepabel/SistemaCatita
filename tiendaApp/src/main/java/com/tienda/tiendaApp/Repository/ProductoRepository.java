package com.tienda.tiendaApp.Repository;

import com.tienda.tiendaApp.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByCodigodebarras(String codigodebarras);
    void deleteByCodigodebarras(String codigodebarras);

}

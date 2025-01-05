package com.tienda.tiendaApp.Repository;

import com.tienda.tiendaApp.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findProductoById(Integer Id);
    Producto findProductoByCodigodebarras(String codigodebarras);

    Producto deleteProductoByCodigodebarras(String codigodebarras);
}

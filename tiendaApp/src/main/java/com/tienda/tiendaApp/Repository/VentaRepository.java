package com.tienda.tiendaApp.Repository;
import com.tienda.tiendaApp.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    Venta findById(Long id);
}

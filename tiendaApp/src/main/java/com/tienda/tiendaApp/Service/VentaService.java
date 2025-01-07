package com.tienda.tiendaApp.Service;

import com.tienda.tiendaApp.Model.DetalleVenta;
import com.tienda.tiendaApp.Model.Producto;
import com.tienda.tiendaApp.Model.Venta;
import com.tienda.tiendaApp.Repository.ProductoRepository;
import com.tienda.tiendaApp.Repository.VentaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Venta registrarVenta(Venta venta) {
        Double totalVenta = 0.0;

        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = productoRepository.findById( detalle.getProducto().getId().longValue() )
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            detalle.setPrecioUnitario(producto.getPrecio());
            Double subtotal = producto.getPrecio() * detalle.getCantidad();
            Double descuento = detalle.getDescuento() != null ? detalle.getDescuento() : 0.0;
            Double impuesto = detalle.getImpuesto() != null ? detalle.getImpuesto() : 0.0;

            Double totalDetalle = subtotal - descuento + impuesto;
            detalle.setPrecioTotal(totalDetalle);
            totalVenta += totalDetalle;

            detalle.setVenta(venta); // Relación bidireccional
        }

        venta.setTotal(totalVenta);
        venta.setFecha(LocalDate.now());
        return ventaRepository.save(venta);
    }

    public Venta actualizarVenta(@Valid Venta venta) {
        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Long id) {
    }

    public Venta obtenerVenta(Long id) {
        return ventaRepository.findById(id);
    }
}

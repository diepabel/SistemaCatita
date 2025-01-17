package com.tienda.tiendaApp.Service;

import com.tienda.tiendaApp.Model.DetalleVenta;
import com.tienda.tiendaApp.Model.Producto;
import com.tienda.tiendaApp.Model.Venta;
import com.tienda.tiendaApp.Repository.ProductoRepository;
import com.tienda.tiendaApp.Repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    public String realizarVenta(Venta venta) {
        double total = 0.0;
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId().longValue()).orElse(null);
            if (producto != null) {
                detalle.setPrecioUnitario(producto.getPrecio());
                double precioTotal = detalle.getPrecioUnitario() * detalle.getCantidad();
                detalle.setPrecioTotal(precioTotal);
                detalle.setImpuesto(precioTotal * 0.18); // Ejemplo de cálculo de impuestos
                detalle.setDescuento(0.0); // Ejemplo de descuentos
                total += precioTotal;
                detalle.setVenta(venta);
            }
        }
        venta.setTotal(total);
        venta.setFecha(LocalDate.now());
        ventaRepository.save(venta);
        return "Venta realizada";
    }
}
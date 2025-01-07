package com.tienda.tiendaApp.Service;

import com.tienda.tiendaApp.DTO.DetalleVentaDTO;
import com.tienda.tiendaApp.Model.DetalleVenta;
import com.tienda.tiendaApp.Repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public String crearDetalleVenta(DetalleVenta detalleVenta) {
        detalleVenta.setPrecioTotal(calcularPrecioTotal(detalleVenta));
        detalleVentaRepository.save(detalleVenta);
        return "Detalle de venta creado";
    }

    public String actualizarDetalleVenta(Integer id, DetalleVenta detalleVenta) {
        DetalleVenta detalleVentaActualizado = detalleVentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
        detalleVentaActualizado.setVenta(detalleVenta.getVenta());
        detalleVentaActualizado.setProducto(detalleVenta.getProducto());
        detalleVentaActualizado.setCantidad(detalleVenta.getCantidad());
        detalleVentaActualizado.setPrecioUnitario(detalleVenta.getPrecioUnitario());
        detalleVentaActualizado.setImpuesto(detalleVenta.getImpuesto());
        detalleVentaActualizado.setDescuento(detalleVenta.getDescuento());
        detalleVentaActualizado.setPrecioTotal(calcularPrecioTotal(detalleVenta));
        detalleVentaActualizado.setFecha(detalleVenta.getFecha());
        detalleVentaActualizado.setUsuario(detalleVenta.getUsuario());
        detalleVentaRepository.save(detalleVentaActualizado);
        return "Detalle de venta actualizado";
    }

    public String borrarDetalleVenta(Integer id) {
        detalleVentaRepository.deleteById(id);
        return "Detalle de venta eliminado";
    }

    public DetalleVenta obtenerDetalleVenta(Integer id) {
        return detalleVentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
    }

    public List<DetalleVentaDTO> obtenerDetallesVenta() {
        return detalleVentaRepository.findAll().stream().map(detalleVenta -> {
            DetalleVentaDTO dto = new DetalleVentaDTO();
            dto.setId(detalleVenta.getId());
            dto.setVentaId(detalleVenta.getVenta().getId());
            dto.setProductoId(detalleVenta.getProducto().getId());
            dto.setCantidad(detalleVenta.getCantidad());
            dto.setPrecioUnitario(detalleVenta.getPrecioUnitario());
            dto.setImpuesto(detalleVenta.getImpuesto());
            dto.setDescuento(detalleVenta.getDescuento());
            dto.setPrecioTotal(detalleVenta.getPrecioTotal());
            dto.setFecha(detalleVenta.getFecha());
            dto.setUsuarioId(detalleVenta.getUsuario().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    private Double calcularPrecioTotal(DetalleVenta detalleVenta) {
        Double precioBruto = detalleVenta.getCantidad() * detalleVenta.getPrecioUnitario();
        Double precioConDescuento = precioBruto - detalleVenta.getDescuento();
        Double precioConImpuesto = precioConDescuento + detalleVenta.getImpuesto();
        return precioConImpuesto;
    }
}
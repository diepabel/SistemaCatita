package com.tienda.tiendaApp.Controller;


import com.tienda.tiendaApp.DTO.DetalleVentaDTO;
import com.tienda.tiendaApp.Model.DetalleVenta;
import com.tienda.tiendaApp.Service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleVenta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    public String crearDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.crearDetalleVenta(detalleVenta);
    }

    @PutMapping("/{id}")
    public String actualizarDetalleVenta(@PathVariable Integer id, @RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.actualizarDetalleVenta(id, detalleVenta);
    }

    @DeleteMapping("/{id}")
    public String borrarDetalleVenta(@PathVariable Integer id) {
        return detalleVentaService.borrarDetalleVenta(id);
    }

    @GetMapping("/{id}")
    public DetalleVenta obtenerDetalleVenta(@PathVariable Integer id) {
        return detalleVentaService.obtenerDetalleVenta(id);
    }

    @GetMapping
    public List<DetalleVentaDTO> obtenerDetallesVenta() {
        return detalleVentaService.obtenerDetallesVenta();
    }
}
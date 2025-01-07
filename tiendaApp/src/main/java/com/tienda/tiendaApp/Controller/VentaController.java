package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.Model.Venta;
import com.tienda.tiendaApp.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.registrarVenta(venta);
        return ResponseEntity.ok(nuevaVenta);
    }
}

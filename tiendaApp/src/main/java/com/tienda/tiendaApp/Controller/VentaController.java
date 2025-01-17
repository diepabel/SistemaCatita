package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.Model.Venta;
import com.tienda.tiendaApp.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public String realizarVenta(@RequestBody Venta venta) {
        return ventaService.realizarVenta(venta);
    }
}
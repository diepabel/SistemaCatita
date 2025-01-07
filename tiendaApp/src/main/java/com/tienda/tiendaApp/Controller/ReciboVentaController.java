package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.DTO.ReciboVentaDTO;
import com.tienda.tiendaApp.Model.ReciboVenta;
import com.tienda.tiendaApp.Repository.ReciboVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recibos")
public class ReciboVentaController {

    @Autowired
    private ReciboVentaRepository reciboVentaRepository;

    @GetMapping
    public List<ReciboVentaDTO> getAllRecibos() {
        return reciboVentaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReciboVentaDTO getReciboById(@PathVariable Integer id) {
        ReciboVenta reciboVenta = reciboVentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Recibo no encontrado"));
        return convertToDTO(reciboVenta);
    }

    @PostMapping
    public ReciboVentaDTO createRecibo(@RequestBody ReciboVentaDTO reciboVentaDTO) {
        ReciboVenta reciboVenta = convertToEntity(reciboVentaDTO);
        return convertToDTO(reciboVentaRepository.save(reciboVenta));
    }

    @PutMapping("/{id}")
    public ReciboVentaDTO updateRecibo(@PathVariable Integer id, @RequestBody ReciboVentaDTO reciboVentaDTO) {
        ReciboVenta reciboVenta = reciboVentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Recibo no encontrado"));
        reciboVenta.setFecha(reciboVentaDTO.getFecha());
        reciboVenta.setMonto(reciboVentaDTO.getMonto());
        reciboVenta.setCliente(reciboVentaDTO.getCliente());
        return convertToDTO(reciboVentaRepository.save(reciboVenta));
    }

    @DeleteMapping("/{id}")
    public void deleteRecibo(@PathVariable Integer id) {
        reciboVentaRepository.deleteById(id);
    }

    private ReciboVentaDTO convertToDTO(ReciboVenta reciboVenta) {
        ReciboVentaDTO reciboVentaDTO = new ReciboVentaDTO();
        reciboVentaDTO.setId(reciboVenta.getId());
        reciboVentaDTO.setVentaId(reciboVenta.getVenta().getId());
        reciboVentaDTO.setFecha(reciboVenta.getFecha());
        reciboVentaDTO.setMonto(reciboVenta.getMonto());
        reciboVentaDTO.setClienteId(reciboVenta.getCliente().getId());
        return reciboVentaDTO;
    }

    private ReciboVenta convertToEntity(ReciboVentaDTO reciboVentaDTO) {
        ReciboVenta reciboVenta = new ReciboVenta();
        reciboVenta.setId(reciboVentaDTO.getId());
        reciboVenta.setFecha(reciboVentaDTO.getFecha());
        reciboVenta.setMonto(reciboVentaDTO.getMonto());
        // Set Venta and Cliente entities based on IDs
        return reciboVenta;
    }
}
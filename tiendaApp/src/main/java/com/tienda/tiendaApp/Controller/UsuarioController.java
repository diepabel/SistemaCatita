package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.DTO.UsuarioDTO;
import com.tienda.tiendaApp.Model.Usuario;
import com.tienda.tiendaApp.Repository.UsuarioRepository;
import com.tienda.tiendaApp.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/Usuario")
    public String crearUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return usuarioService.crearUsuario(usuario);
    }
    @PutMapping("/Usuario/{id}")
    public String actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    @DeleteMapping("/Usuario/{id}")
    public String borrarUsuario(@PathVariable Long id) {
        return usuarioService.borrarUsuario(id);
    }
    @GetMapping("/Usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuario(id);
    }
    @GetMapping("/Usuario")
    public List<Map<String, Object>> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }


    @GetMapping("/Login/{email}/{password}")
    public Optional<UsuarioDTO> login(@PathVariable String email, @PathVariable String password) {
        return usuarioService.login(email, password);
    }





}

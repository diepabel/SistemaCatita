package com.tienda.tiendaApp.Controller;

import com.tienda.tiendaApp.Model.Usuario;
import com.tienda.tiendaApp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/Login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password) {

        if (usuarioRepository.findUsuarioByEmailAndPassword(email, password) != null) {
            return "Bienvenido";
        } else {
            return "Usuario o contraseña incorrectos";
        }

    }


    @PostMapping("/Usuario/{nombre}/{email}/{password}/{rol}")
    public String crearUsuario(@PathVariable String nombre,  @PathVariable String email,@PathVariable String password, @PathVariable String rol) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        return "Usuario creado";
    }

    @PutMapping("/Usuario/{id}/{nombre}/{email}/{password}/{rol}")
    public String actualizarUsuario(@PathVariable Long id, @PathVariable String nombre, @PathVariable String email, @PathVariable String password, @PathVariable String rol) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        return "Usuario actualizado";
    }

    @DeleteMapping("/Usuario/{id}")
    public String borrarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "Usuario eliminado";
    }

    @GetMapping("/Usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
//poner DTO
        return usuario;
    }
//    @GetMapping("/Usuario")
//    public ArrayList<Usuario> obtenerUsuarios() {
//        return new ArrayList<>(usuarioRepository.findAll());
//    }
@GetMapping("/Usuario")
public List<Map<String, Object>> obtenerUsuarios() {
    List<Map<String, Object>> usuariosSinPassword = new ArrayList<>();
    for (Usuario usuario : usuarioRepository.findAll()) {
        Map<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("id", usuario.getId());
        usuarioMap.put("nombre", usuario.getNombre());
        usuarioMap.put("email", usuario.getEmail());
        usuarioMap.put("rol", usuario.getRol());
        usuariosSinPassword.add(usuarioMap);
    }
    return usuariosSinPassword;
}
}

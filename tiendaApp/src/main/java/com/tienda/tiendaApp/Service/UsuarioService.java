package com.tienda.tiendaApp.Service;

import com.tienda.tiendaApp.DTO.UsuarioDTO;
import com.tienda.tiendaApp.Model.Usuario;
import com.tienda.tiendaApp.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public Optional<UsuarioDTO> login(@PathVariable String email, @PathVariable String password) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = usuarioRepository.findUsuarioByEmailAndPassword(email, password);
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRol(usuario.getRol());

        if (usuarioRepository.findUsuarioByEmailAndPassword(email, password) != null) {
            if (usuarioRepository.findUsuarioByEmailAndPassword(email, password).getRol().equals("admin")) {
                return Optional.of(usuarioDTO);
            }
            if (usuarioRepository.findUsuarioByEmailAndPassword(email, password).getRol().equals("vendedor")) {
                return Optional.of(usuarioDTO);
            }

        }
        return Optional.empty();


    }
    public String crearUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario creado";
    }
    public String actualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioActualizado = usuarioRepository.findById(id).get();
        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setEmail(usuario.getEmail());
        usuarioActualizado.setPassword(usuario.getPassword());
        usuarioActualizado.setRol(usuario.getRol());
        usuarioRepository.save(usuarioActualizado);
        return "Usuario actualizado";
    }
    public String borrarUsuario(Long id) {
        usuarioRepository.deleteById(id);
        return "Usuario eliminado";
    }
    public Usuario obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuario;
    }
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

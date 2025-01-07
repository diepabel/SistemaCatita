package com.tienda.tiendaApp.Repository;

import com.tienda.tiendaApp.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByEmailAndPassword(String email, String password);

    Optional<Object> findById(Integer id);
}

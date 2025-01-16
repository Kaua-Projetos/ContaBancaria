package com.example.repository;

import com.example.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNome(String nome);

}

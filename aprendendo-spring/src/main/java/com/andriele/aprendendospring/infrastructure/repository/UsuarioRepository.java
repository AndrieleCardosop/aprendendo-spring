package com.andriele.aprendendospring.infrastructure.repository;

import com.andriele.aprendendospring.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import jdk.jfr.TransitionFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);//optional serve para evitar o retorno de informações nulas

    @Transactional
    void deleteByEmail(String email);
}


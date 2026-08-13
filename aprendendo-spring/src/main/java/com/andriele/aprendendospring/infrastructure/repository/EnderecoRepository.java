package com.andriele.aprendendospring.infrastructure.repository;

import com.andriele.aprendendospring.infrastructure.entity.Endereco;
import com.andriele.aprendendospring.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

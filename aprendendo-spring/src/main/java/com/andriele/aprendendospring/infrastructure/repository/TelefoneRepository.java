package com.andriele.aprendendospring.infrastructure.repository;

import com.andriele.aprendendospring.infrastructure.entity.Telefone;
import com.andriele.aprendendospring.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}

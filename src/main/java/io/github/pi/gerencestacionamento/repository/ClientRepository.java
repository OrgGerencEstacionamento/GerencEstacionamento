package io.github.pi.gerencestacionamento.repository;


import io.github.pi.gerencestacionamento.domain.client.dto.ClientRequestDTO;
import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(int id);
}

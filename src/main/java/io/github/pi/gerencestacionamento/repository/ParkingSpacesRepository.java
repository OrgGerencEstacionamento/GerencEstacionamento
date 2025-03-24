package io.github.pi.gerencestacionamento.repository;

import io.github.pi.gerencestacionamento.domain.vaga.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSpacesRepository  extends JpaRepository<Vaga, Integer> {
    Optional<Vaga> findById(int id);
}

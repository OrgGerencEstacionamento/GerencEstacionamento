package io.github.pi.gerencestacionamento.repository;

import io.github.pi.gerencestacionamento.domain.movimentoVagas.entity.MovimentoVaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovesParkingSpacesRepository  extends JpaRepository<MovimentoVaga, Integer> {
    List<MovimentoVaga> findAllByFkUsuario(int id);
}

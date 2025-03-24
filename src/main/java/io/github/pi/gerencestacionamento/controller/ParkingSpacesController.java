package io.github.pi.gerencestacionamento.controller;

import io.github.pi.gerencestacionamento.Exception.DesativadoException;
import io.github.pi.gerencestacionamento.domain.client.dto.ClientRequestDTO;
import io.github.pi.gerencestacionamento.domain.client.dto.ClientResponseDTO;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.Enum.EstadoMovimentacaoEnum;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.dto.MovimentoVagaDTOConverter;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.dto.MovimentoVagasRequestDTO;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.dto.MovimentoVagasResponseDTO;
import io.github.pi.gerencestacionamento.domain.vaga.dto.VagaResponseDTO;
import io.github.pi.gerencestacionamento.domain.vaga.entity.Vaga;
import io.github.pi.gerencestacionamento.repository.MovesParkingSpacesRepository;
import io.github.pi.gerencestacionamento.service.ParkingSpacesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spaces/")
public class ParkingSpacesController {
    @Autowired
    private ParkingSpacesService parkingSpacesService;
    @Autowired
    private MovesParkingSpacesRepository movesParkingSpacesRepository;

    @GetMapping("/moves")
    public ResponseEntity<List<MovimentoVagasResponseDTO>> listAllMoves() {
        return ResponseEntity.ok().body(parkingSpacesService.findAllMoves());
    }

    @GetMapping
    public ResponseEntity<List<VagaResponseDTO>> listAll() {
        return ResponseEntity.ok().body(parkingSpacesService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MovimentoVagasRequestDTO movimentoVagasRequestDTO) {
        boolean wasCreated = parkingSpacesService.create(MovimentoVagasRequestDTO.fromMovimentoVagas(movimentoVagasRequestDTO));
        return wasCreated ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> findOne(@PathVariable("id") int id) {
        Optional<VagaResponseDTO> responseDTO = parkingSpacesService.findOne(id);
        return responseDTO.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(responseDTO.get());
    };

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<VagaResponseDTO>> findAllByUser(@PathVariable("idUsuario") int id) {
        List<VagaResponseDTO> responseDTO = parkingSpacesService.findAllByUser(id);
        return responseDTO.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(responseDTO);
    };

    @PutMapping
    public ResponseEntity<Void> updateOne(@Valid @RequestBody MovimentoVagasRequestDTO movimentoVagasRequestDTO) {
        boolean responseDTO = parkingSpacesService.updateOne(MovimentoVagasRequestDTO.fromMovimentoVagas(movimentoVagasRequestDTO));
        return responseDTO ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteOne(@PathVariable("id") int id) {
        boolean resp = false;
        if(id > 0)
            resp = movesParkingSpacesRepository.existsById(id);

        if(resp)
            movesParkingSpacesRepository.deleteById(id);

        return resp ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };

    @PutMapping("/payment")
    public ResponseEntity<Void> calcPayment(@Valid @RequestBody MovimentoVagaDTOConverter movimentoVagaDTOConverter) {
        boolean resp = parkingSpacesService.calcPayment(MovimentoVagaDTOConverter.toEntity(movimentoVagaDTOConverter));
        return resp ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

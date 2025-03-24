package io.github.pi.gerencestacionamento.controller;

import io.github.pi.gerencestacionamento.domain.client.dto.ClientRequestDTO;
import io.github.pi.gerencestacionamento.domain.client.dto.ClientResponseDTO;
import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import io.github.pi.gerencestacionamento.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client/")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<ClientResponseDTO> login(@Valid @RequestBody ClientRequestDTO usuario) {
        Optional<ClientResponseDTO> responseDTO = clientService.login(usuario.getEmail(), usuario.getSenha());
        return responseDTO.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(responseDTO.get());
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listAll() {
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ClientRequestDTO ClientRequestDTO) {
        boolean wasCreated = clientService.create(ClientRequestDTO.fromUsuario(ClientRequestDTO));
        return wasCreated ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findOne(@PathVariable("id") int id) {
        Optional<ClientResponseDTO> responseDTO = clientService.findOne(id);
        return responseDTO.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(responseDTO.get());
    };

    @PutMapping
    public ResponseEntity<Void> updateOne(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        boolean responseDTO = clientService.updateOne(ClientRequestDTO.fromUsuario(clientRequestDTO));
        return responseDTO ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteOne(@PathVariable("id") int id) {
        boolean responseDTO = clientService.DeleteOne(id);
        return responseDTO ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };
}

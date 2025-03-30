package io.github.pi.gerencestacionamento.service;

import ch.qos.logback.core.net.server.Client;
import io.github.pi.gerencestacionamento.domain.client.dto.ClientRequestDTO;
import io.github.pi.gerencestacionamento.domain.client.dto.ClientResponseDTO;
import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import io.github.pi.gerencestacionamento.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientResponseDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientResponseDTO::fromUsuario)
                .collect(Collectors.toList());
    }

    public Usuario create(Usuario usuario) {
        if(usuario == null) return null;

        return clientRepository.save(usuario);
    }

    public Optional<ClientResponseDTO> findOne(int id) {
        if(id <= 0) return Optional.empty();

        Optional<Usuario> respDTO = clientRepository.findById(id);

        if(respDTO.isEmpty()) return Optional.empty();
        return Optional.of(ClientResponseDTO.fromUsuario(respDTO.get()));
    }

    public boolean updateOne(Usuario usuario) {
        if(usuario.getIdUsuario() <= 0 || !clientRepository.existsById(usuario.getIdUsuario())) return false;
        clientRepository.save(usuario);
        return true;
    }

    public boolean DeleteOne(int id) {
        if(id <= 0 || !clientRepository.existsById(id)) return false;
         clientRepository.deleteById(id);
        return true;
    }
}

package io.github.pi.gerencestacionamento.validations;

import java.util.Optional;

import org.springframework.stereotype.Component;

import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import io.github.pi.gerencestacionamento.repository.ClientRepository;

@Component
public class ClientValidation {
    
    ClientRepository clientRepository;

    public ClientValidation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Usuario> login(String email, String password) {
        Optional<Usuario> usuario = clientRepository.findByEmail(email);
        
        if(usuario.isPresent() && usuario.get().getSenha().equals(password))    
            return usuario;
    
        throw new IllegalArgumentException("E-mail ou senha inválidos.");
    }
}

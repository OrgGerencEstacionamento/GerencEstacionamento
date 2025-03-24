package io.github.pi.gerencestacionamento.service;

import io.github.pi.gerencestacionamento.Exception.ParametroInvalidoException;
import io.github.pi.gerencestacionamento.Exception.DesativadoException;
import io.github.pi.gerencestacionamento.Exception.CredenciaisInvalidasException;
import io.github.pi.gerencestacionamento.Exception.NaoEncontradoException;
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

    public Optional<ClientResponseDTO> login(String email, String senha) {
        if(email.isEmpty() || senha.isEmpty()) throw new ParametroInvalidoException("Lembre de preecher os campos EMAIL e SENHA!");

        Optional<Usuario> respDTO = clientRepository.findByEmail(email);
        if(respDTO.isEmpty()) throw new CredenciaisInvalidasException("Usuario desconhecido, por favor verifique suas credenciais!");

        Usuario usuario = respDTO.get();

        if(!usuario.isEstadoUsuario()) throw new DesativadoException("Esse usuario foi desativado!");
        if(!usuario.getSenha().equals(senha)) throw new CredenciaisInvalidasException("Usuario desconhecido, por favor verifique suas credenciais!");

        return Optional.of(ClientResponseDTO.fromUsuario(usuario));
    }

    public List<ClientResponseDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientResponseDTO::fromUsuario)
                .collect(Collectors.toList());
    }

    public boolean create(Usuario usuario) {
        if(usuario == null) throw new ParametroInvalidoException("Verifique se todos os campos foram preechidos!");

        clientRepository.save(usuario);
        return true;
    }

    public Optional<ClientResponseDTO> findOne(int id) {
        if(id <= 0) throw new ParametroInvalidoException("Verifique o campo id do cliente!");

        Optional<Usuario> respDTO = clientRepository.findById(id);
        if(respDTO.isEmpty()) throw new NaoEncontradoException("Ops, esse usuario não foi encontrado!");
        if(respDTO.get().isEstadoUsuario()) throw new DesativadoException("Esse usuario foi desativado!");

        return Optional.of(ClientResponseDTO.fromUsuario(respDTO.get()));
    }

    public boolean updateOne(Usuario usuario) {
        if(usuario.getIdUsuario() <= 0) throw new ParametroInvalidoException("Verifique o campo id do cliente!");
        if(!clientRepository.existsById(usuario.getIdUsuario())) throw new NaoEncontradoException("Ops, esse usuario não foi encontrado!");

        clientRepository.save(usuario);
        return true;
    }

    public boolean DeleteOne(int id) {
        if(id <= 0) throw new ParametroInvalidoException("Verifique o campo id do cliente!");
        if(!clientRepository.existsById(id)) throw new NaoEncontradoException("Ops, esse usuario não foi encontrado!");

        clientRepository.deleteById(id);
        return true;
    }
}

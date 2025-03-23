package io.github.pi.gerencestacionamento.domain.client.dto;

import io.github.pi.gerencestacionamento.domain.client.Enum.TipoPlanoEnum;
import io.github.pi.gerencestacionamento.domain.client.Enum.TipoUsuarioEnum;
import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDTO {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private TipoPlanoEnum tipoPlanoEnum;
    private String placa;
    private TipoUsuarioEnum tipoUsuarioEnum;
    private boolean estadoUsuario;

    public static ClientResponseDTO fromUsuario(Usuario usuario) {
        return ClientResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .tipoPlanoEnum(usuario.getTipoPlanoEnum())
                .placa(usuario.getPlaca())
                .tipoUsuarioEnum(usuario.getTipoUsuarioEnum())
                .estadoUsuario(usuario.isEstadoUsuario())
                .build();
    }
}

package io.github.pi.gerencestacionamento.domain.client.dto;

import io.github.pi.gerencestacionamento.domain.client.Enum.TipoPlanoEnum;
import io.github.pi.gerencestacionamento.domain.client.Enum.TipoUsuarioEnum;
import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequestDTO {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private TipoPlanoEnum tipoPlanoEnum;
    private String placa;
    private TipoUsuarioEnum tipoUsuarioEnum;
    private boolean estadoUsuario;

    public static Usuario fromUsuario(ClientRequestDTO usuario) {
        return  Usuario.builder()
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

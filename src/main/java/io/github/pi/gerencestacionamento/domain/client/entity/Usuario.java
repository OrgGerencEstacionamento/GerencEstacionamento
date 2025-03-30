package io.github.pi.gerencestacionamento.domain.client.entity;

import io.github.pi.gerencestacionamento.domain.client.Enum.TipoUsuarioEnum;
import io.github.pi.gerencestacionamento.domain.client.Enum.TipoPlanoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "TB_USUARIOS")
@Entity(name = "TB_USUARIOS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @NotNull(message = "O nome deve ser preechido")
    private String nome;
    @NotNull(message = "O email deve ser preechido")
    private String email;
    @NotNull(message = "A senha deve ser preechida")
    private String senha;
    @NotNull @Enumerated(EnumType.ORDINAL)
    private TipoPlanoEnum tipoPlanoEnum;
    @NotNull(message = "A placa deve ser preechida")
    private String placa;
    @NotNull @Enumerated(EnumType.ORDINAL)
    private TipoUsuarioEnum tipoUsuarioEnum;
    @NotNull
    private boolean estadoUsuario;
}

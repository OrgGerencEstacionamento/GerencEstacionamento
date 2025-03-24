package io.github.pi.gerencestacionamento.domain.movimentoVagas.entity;

import io.github.pi.gerencestacionamento.domain.movimentoVagas.Enum.EstadoMovimentacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TB_MOVIMENTOVAGAS")
@Entity(name = "TB_MOVIMENTOVAGAS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class MovimentoVaga {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "FK Usuario deve ser preechida")
    private int fkUsuario;
    @NotNull(message = "FK Vaga deve ser preechida")
    private int fkVaga;
    private LocalDate dataInclusao = LocalDate.now();
    private LocalDate dataAlteracao = LocalDate.now();
    private EstadoMovimentacaoEnum estadoMovimentacao = EstadoMovimentacaoEnum.ATIVO;
    private float valorVaga = 0;
}
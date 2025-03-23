package io.github.pi.gerencestacionamento.domain.movimentoVagas.dto;

import io.github.pi.gerencestacionamento.domain.movimentoVagas.Enum.EstadoMovimentacaoEnum;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.entity.MovimentoVaga;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovimentoVagasResponseDTO {
    private long id;
    private int fkUsuario;
    private int fkVaga;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;
    private EstadoMovimentacaoEnum estadoMovimentacao;
    private float valorVaga;

    public static MovimentoVagasResponseDTO fromMovimentoVaga(MovimentoVaga movimentoVaga) {
        return MovimentoVagasResponseDTO.builder()
                .id(movimentoVaga.getId())
                .fkUsuario(movimentoVaga.getFkUsuario())
                .fkVaga(movimentoVaga.getFkVaga())
                .dataInclusao(movimentoVaga.getDataInclusao())
                .dataAlteracao(movimentoVaga.getDataAlteracao())
                .estadoMovimentacao(movimentoVaga.getEstadoMovimentacao())
                .build();
    }
}

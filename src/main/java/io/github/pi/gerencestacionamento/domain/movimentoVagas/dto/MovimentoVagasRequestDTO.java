package io.github.pi.gerencestacionamento.domain.movimentoVagas.dto;

import io.github.pi.gerencestacionamento.domain.movimentoVagas.Enum.EstadoMovimentacaoEnum;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.entity.MovimentoVaga;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovimentoVagasRequestDTO {
    private int id;
    private int fkUsuario;
    private int fkVaga;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;
    private EstadoMovimentacaoEnum estadoMovimentacao;
    private float valorVaga;

    public static MovimentoVaga fromMovimentoVagas(MovimentoVagasRequestDTO movimentoVagasRequestDTO) {
        return MovimentoVaga.builder()
                .id(movimentoVagasRequestDTO.getId())
                .fkUsuario(movimentoVagasRequestDTO.getFkUsuario())
                .fkVaga(movimentoVagasRequestDTO.getFkVaga())
                .dataInclusao(movimentoVagasRequestDTO.getDataInclusao())
                .dataAlteracao(movimentoVagasRequestDTO.getDataAlteracao())
                .estadoMovimentacao(movimentoVagasRequestDTO.getEstadoMovimentacao())
                .valorVaga(movimentoVagasRequestDTO.getValorVaga())
                .build();
    }
}

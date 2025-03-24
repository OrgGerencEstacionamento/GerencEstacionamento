package io.github.pi.gerencestacionamento.domain.movimentoVagas.dto;

import io.github.pi.gerencestacionamento.domain.movimentoVagas.Enum.EstadoMovimentacaoEnum;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.entity.MovimentoVaga;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovimentoVagasResponseDTO {
    private int id;
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
                .valorVaga(movimentoVaga.getValorVaga())
                .build();
    }

    public static MovimentoVaga fromMovimentoVaga(MovimentoVagasResponseDTO movimentoVagasResponseDTO) {
        return MovimentoVaga.builder()
                .id(movimentoVagasResponseDTO.getId())
                .fkUsuario(movimentoVagasResponseDTO.getFkUsuario())
                .fkVaga(movimentoVagasResponseDTO.getFkVaga())
                .dataInclusao(movimentoVagasResponseDTO.getDataInclusao())
                .dataAlteracao(movimentoVagasResponseDTO.getDataAlteracao())
                .estadoMovimentacao(movimentoVagasResponseDTO.getEstadoMovimentacao())
                .valorVaga(movimentoVagasResponseDTO.getValorVaga())
                .build();
    }
}

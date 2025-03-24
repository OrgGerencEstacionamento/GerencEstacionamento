package io.github.pi.gerencestacionamento.domain.movimentoVagas.dto;

import io.github.pi.gerencestacionamento.domain.movimentoVagas.Enum.EstadoMovimentacaoEnum;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.entity.MovimentoVaga;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovimentoVagaDTOConverter {
    private int id;
    private int fkUsuario;
    private int fkVaga;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;
    private EstadoMovimentacaoEnum estadoMovimentacao;
    private float valorVaga;

    public static MovimentoVaga toEntity(MovimentoVagaDTOConverter dto) {
        return MovimentoVaga.builder()
                .id(dto.getId())
                .fkUsuario(dto.getFkUsuario())
                .fkVaga(dto.getFkVaga())
                .dataInclusao(dto.getDataInclusao())
                .dataAlteracao(dto.getDataAlteracao())
                .estadoMovimentacao(dto.getEstadoMovimentacao())
                .valorVaga(dto.getValorVaga())
                .build();
    }

    public static MovimentoVagaDTOConverter fromMovimentoVagas(MovimentoVaga movimentoVaga) {
        return MovimentoVagaDTOConverter.builder()
                .id(movimentoVaga.getId())
                .fkUsuario(movimentoVaga.getFkUsuario())
                .fkVaga(movimentoVaga.getFkVaga())
                .dataInclusao(movimentoVaga.getDataInclusao())
                .dataAlteracao(movimentoVaga.getDataAlteracao())
                .estadoMovimentacao(movimentoVaga.getEstadoMovimentacao())
                .valorVaga(movimentoVaga.getValorVaga())
                .build();
    }

    public static MovimentoVagasResponseDTO fromMovimentoVagaDTOConverter(MovimentoVagaDTOConverter movimentoVagaDTOConverter) {
        return MovimentoVagasResponseDTO.builder()
                .id(movimentoVagaDTOConverter.getId())
                .fkUsuario(movimentoVagaDTOConverter.getFkUsuario())
                .fkVaga(movimentoVagaDTOConverter.getFkVaga())
                .dataInclusao(movimentoVagaDTOConverter.getDataInclusao())
                .dataAlteracao(movimentoVagaDTOConverter.getDataAlteracao())
                .estadoMovimentacao(movimentoVagaDTOConverter.getEstadoMovimentacao())
                .valorVaga(movimentoVagaDTOConverter.getValorVaga())
                .build();
    }
}
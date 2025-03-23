package io.github.pi.gerencestacionamento.domain.vaga.dto;

import io.github.pi.gerencestacionamento.domain.vaga.entity.Vaga;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VagaResponseDTO {
    private int idVaga;
    private boolean estadoVaga;

    public static VagaResponseDTO fromVaga(Vaga vaga) {
        return VagaResponseDTO.builder()
                .idVaga(vaga.getIdVaga())
                .estadoVaga(vaga.isEstadoVaga())
                .build();
    }
}

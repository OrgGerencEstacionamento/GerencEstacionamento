package io.github.pi.gerencestacionamento.domain.vaga.dto;

import io.github.pi.gerencestacionamento.domain.vaga.entity.Vaga;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

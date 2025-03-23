package io.github.pi.gerencestacionamento.domain.vaga.dto;

import io.github.pi.gerencestacionamento.domain.vaga.entity.Vaga;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VagaRequestDTO {
    private int idVaga;
    private boolean estadoVaga;

    public static VagaRequestDTO fromVaga(Vaga vaga) {
        return VagaRequestDTO.builder()
                .idVaga(vaga.getIdVaga())
                .estadoVaga(vaga.isEstadoVaga())
                .build();
    }
}

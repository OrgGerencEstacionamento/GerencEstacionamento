package io.github.pi.gerencestacionamento.domain.vaga.dto;

import io.github.pi.gerencestacionamento.domain.vaga.entity.Vaga;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VagaRequestDTO {
    private int idVaga;
    private boolean estadoVaga;

    public static Vaga newVaga(VagaRequestDTO vagaRequestDTO) {
        return Vaga.builder()
                .idVaga(vagaRequestDTO.getIdVaga())
                .estadoVaga(vagaRequestDTO.isEstadoVaga())
                .build();
    }
}

package io.github.pi.gerencestacionamento.domain.vaga.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "TB_VAGAS")
@Entity(name = "TB_VAGAS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Vaga {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVaga;
    @NotNull
    private boolean estadoVaga;
}

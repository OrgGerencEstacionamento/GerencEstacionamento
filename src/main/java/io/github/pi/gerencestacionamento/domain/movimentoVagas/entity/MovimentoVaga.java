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
    private long id;
    @NotNull(message = "FK Usuario deve ser preechida")
    private int fkUsuario;
    @NotNull(message = "FK Vaga deve ser preechida")
    private int fkVaga;
    @NotNull(message = "A data de inclusão deve ser valída")
    private LocalDate dataInclusao;
    @NotNull(message = "A data de alteração deve ser valída")
    private LocalDate dataAlteracao;
    @NotNull @Enumerated(EnumType.ORDINAL)
    private EstadoMovimentacaoEnum estadoMovimentacao;
    @NotNull(message = "O valor deve ser preechido")
    private float valorVaga;
}

/*
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Fk_Usuario INT,
    Fk_Vaga INT,
    Data_Inclusao DATETIME,
    Data_Alteracao DATETIME,
    Estado_Movimento TINYINT,
    ValorFinal DECIMAL(10,2),
    FOREIGN KEY (Fk_Usuario) REFERENCES TB_USUARIOS(Id),
    FOREIGN KEY (Fk_Vaga) REFERENCES TB_VAGAS(Id)
*/
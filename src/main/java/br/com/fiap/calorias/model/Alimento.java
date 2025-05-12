package br.com.fiap.calorias.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_alimentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Alimento {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ALIMENTOS"
    )
    @SequenceGenerator(
            name = "SEQ_ALIMENTOS",
            sequenceName = "SEQ_ALIMENTOS",
            allocationSize = 1
    )
    private Long alimentoId;

    private String nome;
    private String porcao;

    @Column(name = "QTDE_PROTEINA")
    private Double quantidadeProteina;

    @Column(name = "QTDE_CARBOIDRATO")
    private Double quantidadeCarboidrato;

    @Column(name = "QTDE_GORDURAS")
    private Double quantidadeGorduras;

    @Column(name = "TOTAL_CALORIAS")
    private Double totalCalorias;
}
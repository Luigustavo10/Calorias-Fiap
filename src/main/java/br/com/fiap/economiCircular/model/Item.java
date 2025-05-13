package br.com.fiap.economiCircular.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "tbl_itens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITENS")
    @SequenceGenerator(name = "SEQ_ITENS", sequenceName = "SEQ_ITENS", allocationSize = 1)
    private Long itemId;

    @NotBlank(message = "O nome do item é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição do item é obrigatória")
    private String descricao;

    private String estado; // "novo", "usado"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "DISPONIVEL", nullable = false)
    private Boolean disponivel = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
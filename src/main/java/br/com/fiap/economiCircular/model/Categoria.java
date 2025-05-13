package br.com.fiap.economiCircular.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORIAS")
    @SequenceGenerator(name = "SEQ_CATEGORIAS", sequenceName = "SEQ_CATEGORIAS", allocationSize = 1)
    private Long categoriaId;

    @Enumerated(EnumType.STRING) // Define que usará o nome da constante no banco de dados
    @Column(nullable = false, unique = true)
    private TipoCategoria tipo; // Alterado de String para TipoCategoria
}
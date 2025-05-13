package br.com.fiap.economiCircular.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemAtualizacaoDTO(
        @NotBlank(message = "O nome do item é obrigatório")
        String nome,

        @NotBlank(message = "A descrição do item é obrigatória")
        String descricao,

        Boolean disponivel,

        Long categoriaId
) {}
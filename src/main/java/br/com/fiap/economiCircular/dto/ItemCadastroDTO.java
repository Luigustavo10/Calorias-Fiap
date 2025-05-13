package br.com.fiap.economiCircular.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemCadastroDTO(
        @NotBlank String nome,
        @NotBlank String descricao,
        Long categoriaId
) {}
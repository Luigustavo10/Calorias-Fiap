package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.NotBlank;

public record AlimentoCadastroDTO(
        Long alimentoId,
        @NotBlank(message = "Nome do alimento é obrigatório")
        String nome,
        String porcao,
        Double quantidadeProteina,
        Double quantidadeCarboidrato,
        Double quantidadeGorduras
) {
}
package br.com.fiap.economiCircular.dto;

import br.com.fiap.economiCircular.model.Item;

public record ItemExibicaoDTO(
        Long itemId,
        String nome,
        String descricao,
        Boolean disponivel,
        String categoria
) {
    public ItemExibicaoDTO(Item item) {
        this(
                item.getItemId(),
                item.getNome(),
                item.getDescricao(),
                item.getDisponivel(),
                item.getCategoria().getTipo().name() // Pega o nome do enum como string
        );
    }
}
package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuario;

public record UsuarioExibicaoDTO(
        Long id,
        String nome,
        String email) {

    public UsuarioExibicaoDTO(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail());
    }

}

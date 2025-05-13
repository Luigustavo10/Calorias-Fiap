package br.com.fiap.economiCircular.dto;

import br.com.fiap.economiCircular.model.Usuario;

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

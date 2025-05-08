package br.com.fiap.calorias.dto;

public record UsuarioCadastroDTO(
        Long id,
        String nome,
        String email,
        String senha
) {

}

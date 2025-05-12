package br.com.fiap.calorias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_usuarios") // Nome da tabela no banco
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "seq_usuarios", allocationSize = 1)
    @Column(name = "USUARIO_ID") // O nome correto da coluna no banco
    private Long usuarioId;

    @Column(name = "NOME", nullable = false, length = 100) // Garantir tamanho e não nulo
    private String nome;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "SENHA", nullable = false, length = 20)
    private String senha;

    // Getters e Setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
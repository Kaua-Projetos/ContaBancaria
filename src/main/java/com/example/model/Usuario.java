package com.example.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "email", length = 80, nullable = false)
    private String email;
    @Column(name = "senha", length = 30, nullable = false)
    private String senha;
    private Double saldo;

    public Usuario(){

    }

    public Usuario(Double saldo) {
        this.saldo = saldo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

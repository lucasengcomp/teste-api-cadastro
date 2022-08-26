package com.apicadastro.domain.cliente.entity.dto;

import com.apicadastro.domain.cliente.entity.Cliente;

import java.io.Serializable;
import java.time.LocalDate;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private LocalDate dataNascimento;

    private String endereco;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String cpf, String email, String telefone, LocalDate dataNascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public ClienteDTO(Cliente entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.cpf = entidade.getCpf();
        this.email = entidade.getEmail();
        this.telefone = entidade.getTelefone();
        this.dataNascimento = entidade.getDataNascimento();
        this.endereco = entidade.getEndereco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

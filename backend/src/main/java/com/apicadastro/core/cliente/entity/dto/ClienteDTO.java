package com.apicadastro.core.cliente.entity.dto;

import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.endereco.entity.Endereco;
import com.apicadastro.domain.endereco.entity.dto.EnderecoDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nomeCompleto;

    private String cpf;

    private String email;

    private String telefone;

    private LocalDate dataNascimento;

    private Set<EnderecoDTO> enderecos = new HashSet<>();

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nomeCompleto, String cpf, String email, String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public ClienteDTO(Cliente entidade) {
        this.id = entidade.getId();
        this.nomeCompleto = entidade.getNomeCompleto();
        this.cpf = entidade.getCpf();
        this.email = entidade.getEmail();
        this.telefone = entidade.getTelefone();
        this.dataNascimento = entidade.getDataNascimento();
    }

    public ClienteDTO(Cliente entidade, Set<Endereco> enderecos) {
        this(entidade);
        enderecos.forEach(endereco -> this.enderecos.add(new EnderecoDTO(endereco)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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

    public Set<EnderecoDTO> getEnderecos() {
        return enderecos;
    }
}

package com.apicadastro.domain.cliente.entity;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "tb_cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", length = 100)
    @NotEmpty(message = "Campo nome é de preenchimento obrigatório")
    @Length(min = 2, max = 100, message = "O tamanho deve ter no mínimo 2 e no máximo 100 caracteres")
    private String nome;

    @CPF
    @NotEmpty(message = "Campo cpf é de preenchimento obrigatório")
    private String cpf;

    @Column(unique = true)
    @NotEmpty(message = "Campo email é de preenchimento obrigatório")
    @Email(message = "O email informado é inválido")
    private String email;

    @NotEmpty(message = "Campo telefone é de preenchimento obrigatório")
    private String telefone;

    @NotEmpty(message = "Campo data de nascimento é de preenchimento obrigatório")
    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Lob
    @NotEmpty(message = "Campo endereço é de preenchimento obrigatório")
    private String endereco;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, String email, String telefone, String dataNascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
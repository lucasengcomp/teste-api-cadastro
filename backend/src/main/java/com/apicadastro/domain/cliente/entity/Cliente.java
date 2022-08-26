package com.apicadastro.domain.cliente.entity;


import com.apicadastro.domain.pedido.entity.Pedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @NotEmpty(message = "Campo email é de preenchimento obrigatório")
    @Email(message = "O email informado é inválido")
    private String email;

    @NotEmpty(message = "Campo telefone é de preenchimento obrigatório")
    private String telefone;

    @NotEmpty(message = "Campo dataNascimento é de preenchimento obrigatório")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Lob
    @NotEmpty(message = "Campo endereço é de preenchimento obrigatório")
    private String endereco;

    @OneToMany(mappedBy = "cliente")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonBackReference
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, String email, String telefone, LocalDate dataNascimento, String endereco) {
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
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
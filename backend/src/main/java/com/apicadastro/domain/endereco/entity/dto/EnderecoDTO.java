package com.apicadastro.domain.endereco.entity.dto;

import com.apicadastro.domain.endereco.entity.Endereco;

import java.io.Serializable;

public class EnderecoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String cep;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String complemento;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long id, String cep, String rua, String numero, String bairro, String cidade, String estado, String complemento) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }

    public EnderecoDTO(Endereco entidade) {
        this.id = entidade.getId();
        this.cep = entidade.getCep();
        this.rua = entidade.getRua();
        this.numero = entidade.getNumero();
        this.bairro = entidade.getBairro();
        this.cidade = entidade.getCidade();
        this.estado = entidade.getEstado();
        this.complemento = entidade.getComplemento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}

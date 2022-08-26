package com.apicadastro.domain.pedido.entity.dto;

import com.apicadastro.domain.pedido.entity.ItemPedido;

import java.io.Serializable;

public class ItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String sku;

    private String nome;

    private Integer valorUnitario;

    private Integer quantidade;

    private Integer valorTotal;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String sku, String nome, Integer valorUnitario, Integer quantidade) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public ItemDTO(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.sku = itemPedido.getSku();
        this.nome = itemPedido.getNome();
        this.valorUnitario = itemPedido.getValorUnitario();
        this.quantidade = itemPedido.getQuantidade();
        this.valorTotal = itemPedido.getValorTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }
}

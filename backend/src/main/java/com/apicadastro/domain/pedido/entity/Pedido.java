package com.apicadastro.domain.pedido.entity;

import com.apicadastro.domain.cliente.entity.Cliente;

import javax.persistence.*;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String sku;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double valorUnitario;

    @Column(nullable = false)
    private Long quantidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_pedido_cliente"), nullable = false)
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(Long id, String sku, String nome, Double valorUnitario, Long quantidade, Cliente cliente) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.cliente = cliente;
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

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
package com.apicadastro.domain.pedido.entity;

import com.apicadastro.domain.pedido.entity.dto.ItemDTO;

import javax.persistence.*;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String sku;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false, name = "valor_unitario")
    private Integer valorUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, name = "valor_total")
    private Integer valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    public ItemPedido() {
    }

    public ItemPedido(Long id, String sku, String nome, Integer valorUnitario, Integer quantidade, Integer valorTotal) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public ItemPedido(ItemDTO itemDTO, Pedido pedido) {
        this.id = itemDTO.getId();
        this.sku = itemDTO.getSku();
        this.nome = itemDTO.getNome();
        this.valorUnitario = itemDTO.getValorUnitario();
        this.quantidade = itemDTO.getQuantidade();
        this.valorTotal = itemDTO.getValorTotal();
        this.pedido = pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}

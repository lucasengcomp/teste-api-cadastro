package com.apicadastro.domain.pedido.entity.dto;

import com.apicadastro.domain.cliente.entity.dto.ClienteDTO;
import com.apicadastro.domain.pedido.entity.Pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String endereco;

    private Long idCliente;

    private Integer valorTotal;

    private List<ItemDTO> itemsPedido = new ArrayList<>();

    private ClienteDTO clienteDTO;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, String endereco, Long idCliente, Integer valorTotal, ClienteDTO cliente) {
        this.id = id;
        this.endereco = endereco;
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
        this.clienteDTO = cliente;
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.endereco = pedido.getEndereco();
        this.idCliente = pedido.getCliente().getId();
        this.valorTotal = pedido.getValorTotal();
        this.itemsPedido = pedido.getItems().stream().map(ItemDTO::new).collect(Collectors.toList());
    }

    public PedidoDTO(Pedido pedido, ClienteDTO clienteDTO) {
        this.id = pedido.getId();
        this.endereco = pedido.getEndereco();
        this.valorTotal = pedido.getValorTotal();
        this.itemsPedido = pedido.getItems().stream().map(ItemDTO::new).collect(Collectors.toList());
        this.clienteDTO = new ClienteDTO(pedido.getCliente());
        this.idCliente = clienteDTO.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemDTO> getItemsPedido() {
        return itemsPedido;
    }

    public void setItemsPedido(List<ItemDTO> itemsPedido) {
        this.itemsPedido = itemsPedido;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }
}

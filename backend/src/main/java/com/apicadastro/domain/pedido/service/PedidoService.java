package com.apicadastro.domain.pedido.service;

import com.apicadastro.core.exception.service.DatabaseException;
import com.apicadastro.core.exception.service.ResourceNotFoundException;
import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.cliente.entity.dto.ClienteDTO;
import com.apicadastro.domain.cliente.repository.ClienteRepository;
import com.apicadastro.domain.pedido.entity.ItemPedido;
import com.apicadastro.domain.pedido.entity.Pedido;
import com.apicadastro.domain.pedido.entity.dto.PedidoDTO;
import com.apicadastro.domain.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.apicadastro.core.consts.Consts.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public PedidoDTO inserePedidoComIdCliente(PedidoDTO pedidoDTO) {
        var idCliente = clienteRepository.findById(pedidoDTO.getIdCliente()).orElseThrow(() -> new ResourceNotFoundException(RECURSO_NAO_ENCONTRADO));
        return new PedidoDTO(repository.save(objetosPedido(pedidoDTO, idCliente)));
    }

    @Transactional
    public PedidoDTO inserePedidoECliente(PedidoDTO pedidoDTO) {
        var cliente = objetosCliente(pedidoDTO.getClienteDTO());
        cliente = clienteRepository.save(cliente);
        pedidoDTO.setIdCliente(cliente.getId());
        var pedido = objetosPedido(pedidoDTO, cliente);
        repository.save(pedido);
        return new PedidoDTO(pedido, pedidoDTO.getClienteDTO());
    }

    public void deletaPorId(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ID_NAO_ENCONTRADO);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(INTEGRIDADE_DE_DADOS_VIOLADA);
        }
    }

    public Integer calculaValorTotalPedido(List<ItemPedido> itemsPedido) {
        return itemsPedido.stream()
                .mapToInt(ItemPedido::getValorTotal)
                .sum();
    }

    private Pedido objetosPedido(PedidoDTO dto, Cliente cliente) {
        var pedido = new Pedido();
        pedido.setId(dto.getId());
        pedido.setCliente(cliente);
        pedido.setValorTotal(dto.getValorTotal());
        pedido.setEndereco(dto.getEndereco());
        pedido.setItems(preencheListaItemPedido(dto, pedido));
        return pedido;
    }

    private Cliente objetosCliente(ClienteDTO dto) {
        var cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setNome(dto.getNome());
        cliente.setEndereco(dto.getEndereco());
        cliente.setDataNascimento(dto.getDataNascimento());

        return cliente;
    }

    private static List<ItemPedido> preencheListaItemPedido(PedidoDTO dto, Pedido pedido) {
        var items = dto.getItemsPedido().stream()
                .map(item -> new ItemPedido(item, pedido))
                .collect(Collectors.toList());
        return items;
    }
}

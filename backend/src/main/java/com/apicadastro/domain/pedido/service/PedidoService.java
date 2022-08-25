package com.apicadastro.domain.pedido.service;

import com.apicadastro.core.exception.service.DatabaseException;
import com.apicadastro.core.exception.service.NegativeNumberException;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.apicadastro.core.consts.Consts.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Optional<Pedido> pedidoPorId = repository.findById(id);
        Pedido entity = pedidoPorId.orElseThrow(() -> new ResourceNotFoundException(RECURSO_NAO_ENCONTRADO));
        return new PedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        var cliente = clienteRepository.findById(dto.getIdCliente()).orElseThrow(() -> new ResourceNotFoundException(RECURSO_NAO_ENCONTRADO));

        final var pedido = new Pedido();
        pedido.setId(dto.getId());
        pedido.setCliente(cliente);
        pedido.setValorTotal(dto.getValorTotal());
        pedido.setEndereco(dto.getEndereco());

        var items = dto.getItemsPedido().stream()
                .map(item -> new ItemPedido(item, pedido))
                .collect(Collectors.toList());
        pedido.setItems(items);

        repository.save(pedido);

        return new PedidoDTO(pedido);
    }


    public void deleteById(Long id) {
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

    public Integer calcularSoma(List<ItemPedido> itemsPedido) {
        Integer valorTotal = 0;
        for (ItemPedido itemDoPedido : itemsPedido) {
            valorTotal = itemDoPedido.getValorTotal() + valorTotal;
        }
        return valorTotal;
    }
}

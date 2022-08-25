package com.apicadastro.domain.cliente.service;

import com.apicadastro.core.exception.service.DatabaseException;
import com.apicadastro.core.exception.service.ResourceNotFoundException;
import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.cliente.entity.dto.ClienteDTO;
import com.apicadastro.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.apicadastro.core.consts.Consts.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Optional<Cliente> clientePorId = repository.findById(id);
        Cliente entity = clientePorId.orElseThrow(() -> new ResourceNotFoundException(RECURSO_NÃO_ENCONTRADO));
        return new ClienteDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAllPaged(PageRequest pageRequest) {
        Page<Cliente> clientes = repository.findAll(pageRequest);
        return clientes.map(x -> new ClienteDTO(x));
    }

    @javax.transaction.Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        Cliente entity = new Cliente();
        entity = objetoCliente(dto, entity);
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    private Cliente objetoCliente(ClienteDTO dto, Cliente entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
        return entity;
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
}

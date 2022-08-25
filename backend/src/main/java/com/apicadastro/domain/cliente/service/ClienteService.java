package com.apicadastro.domain.cliente.service;

import com.apicadastro.core.exception.service.DatabaseException;
import com.apicadastro.core.exception.service.ResourceNotFoundException;
import com.apicadastro.core.exception.service.UnprocessableEntity;
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

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

import static com.apicadastro.core.consts.Consts.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public ClienteDTO buscaClientePorId(Long id) {
        Optional<Cliente> clientePorId = repository.findById(id);
        Cliente entity = clientePorId.orElseThrow(() -> new ResourceNotFoundException(RECURSO_NAO_ENCONTRADO));
        return new ClienteDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> buscaClientesPaginado(PageRequest pageRequest) {
        Page<Cliente> clientes = repository.findAll(pageRequest);
        return clientes.map(x -> new ClienteDTO(x));
    }

    @Transactional
    public ClienteDTO insereNovoCliente(ClienteDTO dto) {
        Cliente entity = new Cliente();
        entity = objetoCliente(dto, entity);
        validaCliente(entity);
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    public void deletaClientePorId(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ID_NAO_ENCONTRADO);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(INTEGRIDADE_DE_DADOS_VIOLADA);
        }
    }

    @Transactional
    public ClienteDTO atualizaCliente(Long id, ClienteDTO dto) {
        try {
            Cliente entity = repository.getOne(id);
            objetoCliente(dto, entity);
            validaCliente(entity);
            return new ClienteDTO(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(ID_NAO_ENCONTRADO);
        }
    }

    private Cliente objetoCliente(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
        return entity;
    }

    private void validaCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().equals("")) {
            throw new UnprocessableEntity(NOME_CLIENTE_OBRIGATORIO);
        }
        if (cliente.getCpf() == null || cliente.getCpf().equals("")) {
            throw new UnprocessableEntity(CPF_CLIENTE_OBRIGATORIO);
        }
        if (cliente.getEmail() == null || cliente.getEmail().equals("")) {
            throw new UnprocessableEntity(EMAIL_CLIENTE_OBRIGATORIO);
        }
        if (repository.findClienteByEmail(cliente.getEmail()) != null &&
                !Objects.equals(repository.findClienteByEmail(cliente.getEmail()).getId(), cliente.getId())) {
            throw new UnprocessableEntity(EMAIL_DUPLICADO);
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().equals("")) {
            throw new UnprocessableEntity(TELEFONE_CLIENTE_OBRIGATORIO);
        }
        if (cliente.getDataNascimento() == null) {
            throw new UnprocessableEntity(DATA_NASCIMENTO_CLIENTE_OBRIGATORIO);
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().equals("")) {
            throw new UnprocessableEntity(ENDERECO_CLIENTE_OBRIGATORIO);
        }
    }
}

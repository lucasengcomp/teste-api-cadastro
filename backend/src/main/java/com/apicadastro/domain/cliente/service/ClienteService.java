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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    public Page<ClienteDTO> buscaClientesPaginado(Pageable pageable) {
        Page<Cliente> clientes = repository.findAll(pageable);
        return clientes.map(x -> new ClienteDTO(x));
    }

    @Transactional
    public Cliente insereCliente(Cliente cliente) {
        verificaSeEmailExiste(cliente.getEmail());
        cliente.setId(null);
        return repository.save(cliente);
    }

    @Transactional
    public ClienteDTO atualizaCliente(Long id, ClienteDTO dto) {
        try {
            Cliente entity = repository.getOne(id);
            verificaSeEmailExiste(entity.getEmail());
            objetoCliente(dto, entity);
            return new ClienteDTO(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(ID_NAO_ENCONTRADO);
        }
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

    private Cliente objetoCliente(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
        return entity;
    }

    public Cliente fromDTO(ClienteDTO novoDTO) {
        return new Cliente(null, novoDTO.getNome(), novoDTO.getCpf(), novoDTO.getEmail(), novoDTO.getTelefone(), novoDTO.getDataNascimento(), novoDTO.getEndereco());
    }

    public boolean verificaSeEmailExiste(String email) {
        Optional<Cliente> emailExiste = Optional.ofNullable(repository.findByEmail(email));
        if (emailExiste.isPresent()) {
            throw new UnprocessableEntity("Email já existe na base de dados");
        }
        return false;
    }
}

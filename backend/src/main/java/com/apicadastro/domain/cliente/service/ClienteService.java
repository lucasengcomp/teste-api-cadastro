package com.apicadastro.domain.cliente.service;

import com.apicadastro.core.exception.service.ResourceNotFoundException;
import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.cliente.entity.dto.ClienteDTO;
import com.apicadastro.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.apicadastro.core.consts.Consts.RECURSO_NÃO_ENCONTRADO;

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

    /*
    @Transactional(readOnly = true)
    public Cliente insert(Cliente objetoCliente) {
        return repository.save(objetoCliente);
    }


    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(
                objDto.getId(),
                objDto.getNome(),
                objDto.getCpf(),
                objDto.getEmail(),
                objDto.getTelefone(),
                objDto.getDataNascimento(),
                objDto.getEndereco());
    }


    public Cliente fromDTO(ClienteNovoDTO objDto) {
        return new Cliente(
                objDto.getId(),
                objDto.getNome(),
                objDto.getCpf(),
                objDto.getEmail(),
                objDto.getTelefone(),
                objDto.getDataNascimento(),
                objDto.getEndereco());
    }
*/
}

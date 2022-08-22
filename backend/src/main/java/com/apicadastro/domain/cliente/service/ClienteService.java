package com.apicadastro.domain.cliente.service;

import com.apicadastro.core.cliente.entity.dto.ClienteDTO;
import com.apicadastro.core.exception.service.ResourceNotFoundException;
import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.apicadastro.core.consts.Consts.RESOURCE_NOT_FOUND;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Optional<Cliente> clientePorId = repository.findById(id);
        Cliente entity = clientePorId.orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        return new ClienteDTO(entity, entity.getEnderecos());
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAllPaged(PageRequest pageRequest) {
        Page<Cliente> clientes = repository.findAll(pageRequest);
        return clientes.map(x -> new ClienteDTO(x, x.getEnderecos()));
    }
}

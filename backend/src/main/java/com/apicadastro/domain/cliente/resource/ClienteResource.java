package com.apicadastro.domain.cliente.resource;

import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.cliente.entity.dto.ClienteDTO;
import com.apicadastro.domain.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> buscaClientePorId(@PathVariable Long id) {
        ClienteDTO dto = service.buscaClientePorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> buscaClientesPaginado(Pageable pageable) {
        var dtos = service.buscaClientesPaginado(pageable);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Cliente> insereCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = service.fromDTO(dto);
        cliente = service.insereCliente(cliente);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> atualizaCliente(@Valid @PathVariable Long id, @RequestBody ClienteDTO dto) {
        dto = service.atualizaCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaClientePorId(@PathVariable Long id) {
        service.deletaClientePorId(id);
        return ResponseEntity.noContent().build();
    }
}

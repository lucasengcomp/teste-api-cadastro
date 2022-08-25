package com.apicadastro.domain.cliente.resource;

import com.apicadastro.domain.cliente.entity.dto.ClienteDTO;
import com.apicadastro.domain.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<Page<ClienteDTO>> buscaClientesPaginado(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "cfop") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClienteDTO> dtos = service.buscaClientesPaginado(pageRequest);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insereNovoCliente(@RequestBody ClienteDTO dto) {
        dto = service.insereNovoCliente(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> atualizaCliente(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        dto = service.atualizaCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaClientePorId(@PathVariable Long id) {
        service.deletaClientePorId(id);
        return ResponseEntity.noContent().build();
    }
}

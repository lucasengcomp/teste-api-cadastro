package com.apicadastro.domain.pedido.resource;

import com.apicadastro.domain.pedido.entity.dto.PedidoDTO;
import com.apicadastro.domain.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @PostMapping(value = "/pedido")
    public ResponseEntity<PedidoDTO> inserePedido(@Valid @RequestBody PedidoDTO dto) {
        dto = service.inserePedidoComIdCliente(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping(value = "/pedido-cliente")
    public ResponseEntity<PedidoDTO> inserePedidoECliente(@Valid @RequestBody PedidoDTO dto) {
        dto = service.inserePedidoECliente(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable Long id) {
        service.deletaPorId(id);
        return ResponseEntity.noContent().build();
    }
}

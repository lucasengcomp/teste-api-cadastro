package com.apicadastro.domain.pedido.service;

import com.apicadastro.domain.pedido.entity.ItemPedido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testaSoma() {
        List<ItemPedido> items = listaDeItemPedidoComValores();

        var res1 = pedidoService.calculaValorTotalPedido(items);

        Assertions.assertThat(res1).isEqualTo(18500);
    }

    private List<ItemPedido> listaDeItemPedido() {
        ItemPedido item1 = new ItemPedido();
        item1.setValorTotal(1000);
        item1.setPedido(item1.getPedido());
        item1.setValorTotal(1000);
        item1.setSku("SKU999");
        item1.setQuantidade(5);
        item1.setValorUnitario(200);
        return Arrays.asList(item1);
    }

    private List<ItemPedido> listaDeItemPedidoComValores() {
        ItemPedido item1 = new ItemPedido();
        item1.setValorTotal(1000);

        ItemPedido item2 = new ItemPedido();
        item2.setValorTotal(500);

        ItemPedido item3 = new ItemPedido();
        item3.setValorTotal(3000);

        ItemPedido item4 = new ItemPedido();
        item4.setValorTotal(14000);

        return Arrays.asList(item1, item2, item3, item4);
    }
}

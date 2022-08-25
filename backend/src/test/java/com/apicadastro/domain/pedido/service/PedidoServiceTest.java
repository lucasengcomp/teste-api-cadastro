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
    public void testSoma() {
        List<ItemPedido> items = getLista();

        var res1 = pedidoService.calculaValorTotalPedido(items);
        var res2 = pedidoService.calcularSoma(items);

        Assertions.assertThat(res1).isEqualTo(res2);
    }

    private List<ItemPedido> getLista() {
        ItemPedido i1 = new ItemPedido();
        i1.setValorTotal(1000);

        ItemPedido i2 = new ItemPedido();
        i2.setValorTotal(1000);

        ItemPedido i3 = new ItemPedido();
        i3.setValorTotal(1000);

        ItemPedido i4 = new ItemPedido();
        i4.setValorTotal(1000);

        return Arrays.asList(i1, i2, i3, i4);
    }
}

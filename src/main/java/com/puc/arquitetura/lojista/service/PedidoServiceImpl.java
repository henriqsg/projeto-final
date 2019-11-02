package com.puc.arquitetura.lojista.service;

import com.puc.arquitetura.lojista.domain.Pedido;
import com.puc.arquitetura.lojista.service.exception.PedidoNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public String getStatus(Long id) throws PedidoNotFound {
        Optional<Pedido> pedido = pedidos.stream().filter(item -> item.getId().equals(id)).findFirst();
        return pedido.map(Pedido::getStatus).orElseThrow(PedidoNotFound::new);
    }

    @Override
    public Pedido createPedido(Integer codigoProduto, Integer quantidade, String observacoes) {
        Pedido pedido = new Pedido(codigoProduto, quantidade, observacoes);
        pedidos.add(pedido);
        // Comunicar com servico Atacadista
        return pedido;
    }

    @Override
    public List<Pedido> all() {
        return pedidos;
    }


}

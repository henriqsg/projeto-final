package com.puc.arquitetura.lojista.rest;

import com.puc.arquitetura.lojista.domain.Orcamento;
import com.puc.arquitetura.lojista.domain.Pedido;
import com.puc.arquitetura.lojista.service.PedidoService;
import com.puc.arquitetura.lojista.service.exception.PedidoNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/lojista")
public class LojistaController {

    private final PedidoService pedidoService;

    public LojistaController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping(value = "/pedido/{id}")
    public ResponseEntity<String> statusPedido(Long id) throws PedidoNotFound {
        return ResponseEntity.ok(pedidoService.getStatus(id));
    }

    @PostMapping(value = "/pedido")
    public ResponseEntity<Pedido> createPedido(Integer codigoProduto, Integer quantidade, String observacoes) {
        return ResponseEntity.ok(pedidoService.createPedido(codigoProduto, quantidade, observacoes));
    }

    @GetMapping(value = "/pedido")
    public ResponseEntity<List<Pedido>> all() {
        return ResponseEntity.ok(pedidoService.all());
    }

    @PostMapping(value = "/orcamento/{id}")
    public ResponseEntity<Orcamento> authorizePedido() {
        return ResponseEntity.ok(pedidoService.authorize());
    }

}

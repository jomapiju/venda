package com.javastore.venda.facade;

import com.javastore.venda.model.Venda;
import com.javastore.venda.sigleton.NumeroVendaSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendaFacade {
    @Autowired
    ProdutoFacade produtoFacade;

    @Autowired
    EstoqueFacade estoqueFacade;

    @Autowired
    PagamentoFacade pagamentoFacade;

    @Autowired
    NotaFacade notaFacade;

    @Autowired
    private NumeroVendaSingleton numeroVenda;

    public boolean ExecutarVenda(Venda venda){
        System.out.printf("Venda numero: %d%n", numeroVenda.getNumeroVenda());
        if(!estoqueFacade.ReservaEstoque(venda.quantidade, venda.produtoId)){
            return false;
        }
        if (!pagamentoFacade.executaPagamento(venda.cpf)){
            estoqueFacade.DesfazReserva(venda.quantidade, venda.produtoId);
            return false;
        }
        notaFacade.GerarNota(produtoFacade.BuscaProduto(venda.produtoId));
        return true;
    }
}

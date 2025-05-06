package com.javastore.venda.facade;

import org.springframework.stereotype.Component;

@Component
public class PagamentoFacade {

    PagamentoFacade(){}

    public boolean executaPagamento(String cpf){

        if (cpf.equals("12124463721")){
            System.out.printf("Pagamento não aprovado%n");
            return false;
        } else {
            System.out.printf("Pagamento aprovado%n");
            return true;
        }
    }
}

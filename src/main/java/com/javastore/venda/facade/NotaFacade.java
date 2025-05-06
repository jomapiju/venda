package com.javastore.venda.facade;

import com.javastore.venda.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class NotaFacade {
    NotaFacade(){}

    public void GerarNota(Produto produto){
        System.out.printf("Nota gerada%n");
    }
}

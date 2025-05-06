package com.javastore.venda.service;

import com.javastore.venda.facade.VendaFacade;
import com.javastore.venda.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    VendaFacade vendaFacade;

    public boolean vender(Venda venda){
        return vendaFacade.ExecutarVenda(venda);
    }
}

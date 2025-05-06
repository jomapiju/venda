package com.javastore.venda.sigleton;

public class NumeroVendaSingleton {
    private int numeroVenda = 0;

    public int getNumeroVenda() {
        numeroVenda = numeroVenda + 1;
        return numeroVenda;
    }
}

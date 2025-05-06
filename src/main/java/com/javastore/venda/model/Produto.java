package com.javastore.venda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Produto {
    public Long id;
    public String nome;
    public Double preco;
}

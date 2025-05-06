package com.javastore.venda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Venda {

    public String cpf;
    public Long produtoId;
    public Integer quantidade;
}

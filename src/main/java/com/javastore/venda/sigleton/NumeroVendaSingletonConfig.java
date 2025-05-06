package com.javastore.venda.sigleton;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class NumeroVendaSingletonConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public NumeroVendaSingleton numeroVenda() {
        return new NumeroVendaSingleton();
    }
}

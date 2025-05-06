package com.javastore.venda.facade;

import com.javastore.venda.config.AuthConfig;
import com.javastore.venda.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProdutoFacade {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private AuthConfig authConfig;

    ProdutoFacade(){
    }

    public Produto BuscaProduto(Long id){
         try{
             HttpHeaders headers = new HttpHeaders();
             headers.setBearerAuth(authConfig.getToken());

             HttpEntity<String> entity = new HttpEntity<>(null, headers);
            ResponseEntity<Produto> response = restTemplate.exchange(
                    String.format("%s/produtoController/%d", environment.getProperty("url_produto"), id),
                    HttpMethod.GET,
                    entity,
                    Produto.class
            );
            System.out.printf("Produto encontrado: %s%n", response.getBody());
            return response.getBody();
        } catch (Exception e) {
             System.out.printf("Erro ao obter produto: %s%n", e.getMessage());
            return null;
        }
    }
}

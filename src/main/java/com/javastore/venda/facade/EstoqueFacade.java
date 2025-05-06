package com.javastore.venda.facade;

import com.javastore.venda.config.AuthConfig;
import com.javastore.venda.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class EstoqueFacade {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private AuthConfig authConfig;

    EstoqueFacade(){}

    public boolean ReservaEstoque(Integer quantidade, Long produtoId){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(authConfig.getToken());

            HttpEntity<String> entity = new HttpEntity<>(null, headers);
            ResponseEntity<Void> response = restTemplate.exchange(
                    String.format("%s/estoqueController/%d/vender/%d", environment.getProperty("url_estoque"), produtoId, quantidade),
                    HttpMethod.PUT,
                    entity,
                    Void.class
            );
            System.out.printf("Estoque reservado: %s%n", response.getStatusCode() == HttpStatus.NO_CONTENT);
            return response.getStatusCode() == HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            System.out.printf("Erro ao reservar estoque: %s%n", e.getMessage());
            return false;
        }
    }

    public boolean DesfazReserva(Integer quantidade, Long produtoId){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(authConfig.getToken());

            HttpEntity<String> entity = new HttpEntity<>(null, headers);
            ResponseEntity<Void> response = restTemplate.exchange(
                    String.format("%s/estoqueController/%d/repor/%d", environment.getProperty("url_estoque"),produtoId, quantidade),
                    HttpMethod.PUT,
                    entity,
                    Void.class
            );
            System.out.printf("Estoque retornado: %s%n", response.getStatusCode() == HttpStatus.NO_CONTENT);
            return response.getStatusCode() == HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            System.out.printf("Erro ao retornar estoque: %s%n", e.getMessage());
            return false;
        }
    }

}

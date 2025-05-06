package com.javastore.venda.api;

import com.javastore.venda.model.Venda;
import com.javastore.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "vendaController")
public class VendaController {

    @Autowired
    VendaService vendaService;

    @PreAuthorize("hasRole('vendedor')")
    @PostMapping("")
    public ResponseEntity<Void> vender(@RequestBody Venda venda) {
        if(vendaService.vender(venda)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}

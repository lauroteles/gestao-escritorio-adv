package com.gestaoescritorio.gestao.controller;

import com.gestaoescritorio.gestao.dto.AndamentosDTO;
import com.gestaoescritorio.gestao.service.AndamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/andamentos")
public class AndamentosController {

    @Autowired
    private AndamentoService andamentoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criandoAndamento(@RequestBody AndamentosDTO dto) {
        try {
            andamentoService.criarAndamento(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Andamento Criado");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar andamento" + exception);
        }
    }
}

package com.gestaoescritorio.gestao.controller;

import com.gestaoescritorio.gestao.dto.AndamentosDTO;
import com.gestaoescritorio.gestao.service.AndamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/tabela-andamentos")
    public ResponseEntity<List<AndamentosDTO>> tabelaAndamentosPorProcesso(@RequestParam String numeroProcesso){
        try {
            List<AndamentosDTO> tabelaAndamentos = andamentoService.listarAndamentosPorProcesso(numeroProcesso);
            return ResponseEntity.ok(tabelaAndamentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

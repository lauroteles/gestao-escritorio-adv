package com.gestaoescritorio.gestao.controller;

import com.gestaoescritorio.gestao.dto.processoDTO;
import com.gestaoescritorio.gestao.service.ProcessosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processos")
public class ProcessosController {

    @Autowired
    private ProcessosService processosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrasProcesso(@RequestBody processoDTO dto) {
        try {
            processosService.criarProcesso(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Processo criado com sucesso");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na criação do processo, favor verificar as informações" + exception);
        }

    }

    @GetMapping("/tabela-processos")
    public ResponseEntity<?> tabelaProcessos(@RequestParam(required = false) String processoNumero,
                                             @RequestParam(required = false) String nome,
                                             @RequestParam(required = false) String tribunal,
                                             @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        try {
            Page<processoDTO> processos = processosService.listarProcessos(
                    processoNumero,
                    nome,
                    tribunal,
                    pageable);
            return ResponseEntity.ok(processos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERRO NA REQUISIÇÃO DA TABELA: " + e.getMessage());
        }
    }



}

package com.gestaoescritorio.gestao.controller;

import com.gestaoescritorio.gestao.dto.processoDTO;
import com.gestaoescritorio.gestao.service.ProcessosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}

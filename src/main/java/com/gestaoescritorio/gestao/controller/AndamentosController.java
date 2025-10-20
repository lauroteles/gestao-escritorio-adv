package com.gestaoescritorio.gestao.controller;

import com.gestaoescritorio.gestao.dto.AndamentosDTO;
import com.gestaoescritorio.gestao.service.AndamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            List<AndamentosDTO> tabelaAndamentos = andamentoService.listarAndamentosPorProcesso(numeroProcesso).stream()
                    .sorted(Comparator.comparing(AndamentosDTO::id))
                    .toList();
            return ResponseEntity.ok(tabelaAndamentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("vencimentos-1-dia")
    public ResponseEntity<?> vencimentoUmDia() {
        try {
            Long total = andamentoService.getTotalItensnoDia();
            return ResponseEntity.status(HttpStatus.OK).body(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e + "Erro na requisição");
        }
    }
    @GetMapping("andamentos-1-dia")
    public ResponseEntity<?> andamentosvVencimentoUmDia() {
        try {
            List<AndamentosDTO> total = andamentoService.getTotalAndamentosHoje();
            return ResponseEntity.status(HttpStatus.OK).body(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e + "Erro na requisição");
        }
    }

    @GetMapping("vencimentos-1-semana")
    public ResponseEntity<?> vencimentoUmaSemana() {
        try {
            Long total = andamentoService.getTotalItensProximaSemana();
            return ResponseEntity.status(HttpStatus.OK).body(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e + "Erro na requisição");
        }
    }

    @GetMapping("andamentos-1-semana")
    public ResponseEntity<?> andamentosvVencimentoUmaSemana() {
        try {
            List<AndamentosDTO> total = andamentoService.getTotalAndamentosProximaSemana();
            return ResponseEntity.status(HttpStatus.OK).body(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e + "Erro na requisição");
        }
    }
    @GetMapping("vencimentos-1-mes")
    public ResponseEntity<?> vencimentoUmMes() {
        try {
            Long total = andamentoService.getTotalItensProximaMes();
            return ResponseEntity.status(HttpStatus.OK).body(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e + "Erro na requisição de total 1 mes");
        }
    }
    @GetMapping("vencimentos-15-dias")
    public ResponseEntity<?> vencimentoUmQuinzeDias() {
        try {
            Long total = andamentoService.getTotalItensProximos15Dias();
            return ResponseEntity.status(HttpStatus.OK).body(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e + "Erro na requisição de total 1 mes");
        }
    }

    @PutMapping("/observacao")
    public ResponseEntity<?> updateObservacao(
            @RequestParam String observacao,
            @RequestParam Long id) {
        Optional<AndamentosDTO> linhasAfetadas = andamentoService.updateObservacao(observacao, id);
        return ResponseEntity.status(HttpStatus.OK).body(linhasAfetadas);
    }

}

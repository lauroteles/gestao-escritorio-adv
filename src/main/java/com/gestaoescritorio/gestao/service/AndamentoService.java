package com.gestaoescritorio.gestao.service;

import com.gestaoescritorio.gestao.dto.AndamentosDTO;
import com.gestaoescritorio.gestao.entity.AndamentosEntity;
import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import com.gestaoescritorio.gestao.repository.AndamentoRepository;
import com.gestaoescritorio.gestao.repository.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AndamentoService {

    private final AndamentoRepository andamentoRepository;
    private final ProcessoRepository processoRepository;


    public AndamentosEntity criarAndamento(AndamentosDTO dto) {
        ProcessosEntity processos = processoRepository.findByProcessoNumero(dto.processoNumero())
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
        System.out.println("DTO NUMERO PROCESSO"+processos);

        AndamentosEntity andamentos = new AndamentosEntity();
        andamentos.setDataAndamento(dto.dataAndamento());
        andamentos.setPrazoFinal(dto.prazoFinal());
        andamentos.setObservacao(dto.observacao());
        andamentos.setDataConferencia(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        andamentos.setResponsavelConferencia(dto.responsavelConferencia());
        andamentos.setProcesso(processos);
        return andamentoRepository.save(andamentos);
    }


}

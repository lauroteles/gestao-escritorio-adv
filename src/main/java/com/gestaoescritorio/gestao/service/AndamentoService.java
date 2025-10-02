package com.gestaoescritorio.gestao.service;

import com.gestaoescritorio.gestao.dto.AndamentosDTO;
import com.gestaoescritorio.gestao.entity.AndamentosEntity;
import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import com.gestaoescritorio.gestao.repository.AndamentoRepository;
import com.gestaoescritorio.gestao.repository.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.And;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AndamentoService {

    private final AndamentoRepository andamentoRepository;
    private final ProcessoRepository processoRepository;


    public AndamentosEntity criarAndamento(AndamentosDTO dto) {

        String usuario = obterUsuarioLogado();

        ProcessosEntity processos = processoRepository.findByProcessoNumero(dto.processoNumero())
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
        System.out.println("DTO NUMERO PROCESSO" + processos);

        AndamentosEntity andamentos = new AndamentosEntity();
        andamentos.setDataAndamento(dto.dataAndamento());
        andamentos.setPrazoFinal(dto.prazoFinal());
        andamentos.setObservacao(dto.observacao());
        andamentos.setDataConferencia(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        andamentos.setResponsavelConferencia(usuario);
        andamentos.setProcesso(processos);
        return andamentoRepository.save(andamentos);
    }

    private String obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ( authentication == null ) {
            throw  new RuntimeException("Usuário não autenticado");
        }

        return authentication.getName();
    }

    public List<AndamentosDTO> listarAndamentosPorProcesso(String numeroProcesso) {
        return andamentoRepository.findByProcesso_ProcessoNumero(numeroProcesso)
                .stream()
                .map(a -> new AndamentosDTO(
                        a.getDataAndamento(),
                        a.getPrazoFinal(),
                        a.getObservacao(),
                        a.getDataConferencia(),
                        a.getResponsavelConferencia(),
                        a.getProcesso().getProcessoNumero()
                ))
                .toList();
    }

    public Long getTotalItensProximaSemana() {
        LocalDate dataLimite = LocalDate.now().plusWeeks(1);
        Date dataLimiteDate = Date.from(dataLimite.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return andamentoRepository.totalVencimentosEmUmaSemana(dataLimiteDate);
    }

    public List<AndamentosDTO> getTotalAndamentosProximaSemana() {
        LocalDate dataLimite = LocalDate.now().plusWeeks(1);
        Date dataLimiteDate = Date.from(dataLimite.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return andamentoRepository.andamentosVencendoNaSemana(dataLimiteDate)
                .stream()
                .map(a -> new AndamentosDTO(
                        a.getDataAndamento(),
                        a.getPrazoFinal(),
                        a.getObservacao(),
                        a.getDataConferencia(),
                        a.getResponsavelConferencia(),
                        a.getProcesso().getProcessoNumero()

                )).toList();
    }


    public Long getTotalItensProximaMes() {
        LocalDate dataLimite = LocalDate.now().plusMonths(1);
        Date dataLimiteDate = Date.from(dataLimite.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return andamentoRepository.totalVencimentosEmUmaMes(dataLimiteDate);
    }


    public Long getTotalItensProximos15Dias() {
        LocalDate dataLimite = LocalDate.now().plusDays(15);
        Date dataLimiteDate = Date.from(dataLimite.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return andamentoRepository.totalVencimentosEmQuinzeDias(dataLimiteDate);
    }



}

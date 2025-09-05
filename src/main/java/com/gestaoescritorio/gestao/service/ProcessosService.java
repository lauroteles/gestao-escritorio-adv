package com.gestaoescritorio.gestao.service;

import com.gestaoescritorio.gestao.dto.processoDTO;
import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import com.gestaoescritorio.gestao.repository.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessosService {

    private final ProcessoRepository processoRepository;

    public ProcessosEntity criarProcesso(processoDTO dto) {
        ProcessosEntity processosEntity = new ProcessosEntity();
        processosEntity.setProcessoNumero(dto.processoNumero());
        processosEntity.setNome(dto.nome());
        processosEntity.setTribunal(dto.tribunal());
        return processoRepository.save(processosEntity);
    }
}

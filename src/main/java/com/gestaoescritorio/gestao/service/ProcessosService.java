package com.gestaoescritorio.gestao.service;

import com.gestaoescritorio.gestao.dto.processoDTO;
import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import com.gestaoescritorio.gestao.repository.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<processoDTO> listarProcessos(Pageable pageable) {
        return processoRepository.findAll(pageable)
                .map(p -> new processoDTO(p.getProcessoNumero(),p.getNome(),p.getTribunal()));

    }
}

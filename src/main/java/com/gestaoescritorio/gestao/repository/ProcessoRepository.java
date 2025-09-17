package com.gestaoescritorio.gestao.repository;

import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<ProcessosEntity, Long> {
    Optional<ProcessosEntity>  findByProcessoNumero(String processoNumero);

}

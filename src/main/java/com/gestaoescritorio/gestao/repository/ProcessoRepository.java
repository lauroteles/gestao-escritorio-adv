package com.gestaoescritorio.gestao.repository;

import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<ProcessosEntity, Long>, JpaSpecificationExecutor<ProcessosEntity> {
    Optional<ProcessosEntity>  findByProcessoNumero(String processoNumero);

}

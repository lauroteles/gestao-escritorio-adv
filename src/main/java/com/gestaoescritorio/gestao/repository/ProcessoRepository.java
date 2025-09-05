package com.gestaoescritorio.gestao.repository;

import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<ProcessosEntity, Long> {
    Optional<ProcessosEntity>  findByProcessoNumero(String processoNumero);
}

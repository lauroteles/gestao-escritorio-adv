package com.gestaoescritorio.gestao.repository;

import com.gestaoescritorio.gestao.entity.AndamentosEntity;
import org.aspectj.weaver.ast.And;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AndamentoRepository extends JpaRepository<AndamentosEntity, Long> {

    List<AndamentosEntity> findByProcesso_ProcessoNumero(String processoNumero);


}

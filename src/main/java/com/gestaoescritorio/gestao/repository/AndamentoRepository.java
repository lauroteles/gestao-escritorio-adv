package com.gestaoescritorio.gestao.repository;

import com.gestaoescritorio.gestao.entity.AndamentosEntity;
import org.aspectj.weaver.ast.And;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface AndamentoRepository extends JpaRepository<AndamentosEntity, Long> {

    List<AndamentosEntity> findByProcesso_ProcessoNumero(String processoNumero);

    @Query("SELECT COUNT(i) FROM AndamentosEntity i WHERE i.prazoFinal < :dataLimite")
    Long totalVencimentosEmUmaSemana(@Param("dataLimite") Date dataLimite);


    @Query("SELECT COUNT(i) FROM AndamentosEntity i WHERE i.prazoFinal < :dataLimite")
    Long totalVencimentosEmUmaMes(@Param("dataLimite") Date dataLimite);


    @Query("SELECT COUNT(i) FROM AndamentosEntity i WHERE i.prazoFinal < :dataLimite")
    Long totalVencimentosEmQuinzeDias(@Param("dataLimite") Date dataLimite);

    @Query("SELECT i FROM AndamentosEntity i WHERE i.prazoFinal < :dateLimit")
    List<AndamentosEntity> andamentosVencendoNaSemana(Date dateLimit);

}

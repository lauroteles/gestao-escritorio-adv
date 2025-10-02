package com.gestaoescritorio.gestao.service;


import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProcessoSpecification {

    public static Specification<ProcessosEntity> comFiltros(String processoNumero, String nome, String tribunal) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (processoNumero != null && !processoNumero.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("processoNumero")),
                        "%" + processoNumero.toLowerCase() + "%"
                ));
            }

            if (nome != null && !nome.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")),
                        "%" + nome.toLowerCase() + "%"
                ));
            }

            if (tribunal != null && !tribunal.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("tribunal")),
                        "%" + tribunal.toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }



}

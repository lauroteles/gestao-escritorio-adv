package com.gestaoescritorio.gestao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "processos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "processos_numero", unique = true, nullable = false)
    private String processoNumero;

    private String nome;

    private String tribunal;

    @OneToMany(mappedBy = "processo")
    private List<AndamentosEntity> andamentos = new ArrayList<>();
}

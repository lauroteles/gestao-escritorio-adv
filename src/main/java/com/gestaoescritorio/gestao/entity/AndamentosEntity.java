package com.gestaoescritorio.gestao.entity;


import com.gestaoescritorio.gestao.enums.AndamentoEnum;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.*;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "andamentos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AndamentosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_andamentos")
    private Date dataAndamento;

    @Column(name = "prazo_final")
    private Date prazoFinal;

    private String observacao;

    @Column(name = "data_de_conferencia")
    private LocalDateTime dataConferencia;

    @Column(name = "responsavel_pela_conferencia")
    @Enumerated(EnumType.STRING)
    private AndamentoEnum responsavelConferencia;

    @ManyToOne
    @JoinColumn(name = "processos_numero",referencedColumnName = "processos_numero")
    private ProcessosEntity processo;

}

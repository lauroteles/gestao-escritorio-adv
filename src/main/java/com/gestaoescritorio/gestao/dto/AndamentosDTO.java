package com.gestaoescritorio.gestao.dto;

import com.gestaoescritorio.gestao.entity.ProcessosEntity;

import java.time.LocalDateTime;
import java.util.Date;

public record AndamentosDTO(Long id, Date dataAndamento, Date prazoFinal, String observacao, LocalDateTime dataConferencia,
                            String responsavelConferencia, String processoNumero) {
}

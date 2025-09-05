package com.gestaoescritorio.gestao.dto;

import com.gestaoescritorio.gestao.entity.ProcessosEntity;
import com.gestaoescritorio.gestao.enums.AndamentoEnum;

import java.time.LocalDateTime;
import java.util.Date;

public record AndamentosDTO(Date dataAndamento, Date prazoFinal, String observacao, LocalDateTime dataConferencia,
                            AndamentoEnum responsavelConferencia, String processoNumero) {
}

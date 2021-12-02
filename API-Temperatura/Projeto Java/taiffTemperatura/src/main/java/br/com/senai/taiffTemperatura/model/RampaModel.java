package br.com.senai.taiffTemperatura.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RampaModel {
      private LocalDateTime inicio;
      private LocalDateTime fim;
      private long duracao;
      private String termopar;
      private boolean rampaSubindo;
      private double TempMinima;
      private double TempMaxima;
      private double TempAmbiente;      
}

package br.com.senai.taiffTemperatura.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class RampaModel {
      private LocalDateTime inicio;
      private LocalDateTime fim;
      private long duracao;
      private String termopar;
      private boolean rampaSubindo;

	public RampaModel() {
    	
    }
      
	public RampaModel(LocalDateTime inicio, LocalDateTime fim , String termopar, boolean rampaSubindo) {
		this.termopar = termopar;
		this.inicio = inicio;
		this.fim = fim;
		this.rampaSubindo = rampaSubindo;
		duracao = inicio.until(fim, ChronoUnit.MINUTES);
	}
	
	
	public LocalDateTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}
	public LocalDateTime getFim() {
		return fim;
	}
	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}
	public long getDuracao() {
		return duracao;
	}
	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}

	public String getTermopar() {
		return termopar;
	}

	public void setTermopar(String termopar) {
		this.termopar = termopar;
	}
	
    public boolean isRampaSubindo() {
		return rampaSubindo;
	}

	public void setRampaSubindo(boolean rampaSubindo) {
		this.rampaSubindo = rampaSubindo;
	}
      
      
    
      
}

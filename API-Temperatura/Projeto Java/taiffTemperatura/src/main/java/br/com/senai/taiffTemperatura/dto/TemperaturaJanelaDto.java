package br.com.senai.taiffTemperatura.dto;

import br.com.senai.taiffTemperatura.model.TemperaturaModel;

public class TemperaturaJanelaDto {
	
	private float termopar_1;
	private float termopar_2;
	private float termopar_3;
	private float termopar_amb;
	private int linha;
	
	
	
	public TemperaturaJanelaDto() {
		
	}
	
	

	public TemperaturaJanelaDto(TemperaturaModel temperatura) {
		super();
		this.termopar_1 = temperatura.getTermopar_1();
		this.termopar_2 = temperatura.getTermopar_2();
		this.termopar_3 = temperatura.getTermopar_3();
		this.termopar_amb = temperatura.getTermopar_amb();
		
	}



	public TemperaturaJanelaDto(float termopar_1, float termopar_2, float termopar_3, float termopar_amb, int linha) {
		super();
		this.termopar_1 = termopar_1;
		this.termopar_2 = termopar_2;
		this.termopar_3 = termopar_3;
		this.termopar_amb = termopar_amb;
		this.linha = linha;
		
	}
	
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public float getTermopar_1() {
		return termopar_1;
	}
	public void setTermopar_1(float termopar_1) {
		this.termopar_1 = termopar_1;
	}
	public float getTermopar_2() {
		return termopar_2;
	}
	public void setTermopar_2(float termopar_2) {
		this.termopar_2 = termopar_2;
	}
	public float getTermopar_3() {
		return termopar_3;
	}
	public void setTermopar_3(float termopar_3) {
		this.termopar_3 = termopar_3;
	}
	public float getTermopar_amb() {
		return termopar_amb;
	}
	public void setTermopar_amb(float termopar_amb) {
		this.termopar_amb = termopar_amb;
	}

}



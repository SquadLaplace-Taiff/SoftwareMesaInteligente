package br.com.senai.taiffTemperatura.model;

public class EstatisticaModel {
	
	private double termopar_1;
	private double termopar_2;
	private double termopar_3;
	private double termopar_amb;
	private double media;
	private double temperaturaCorrigida;
	
	
	
	
	public EstatisticaModel() {
		
	}
	
	


	public EstatisticaModel(double termopar_1, double termopar_2, double termopar_3, double termopar_amb) {
		super();
		this.termopar_1 = termopar_1;
		this.termopar_2 = termopar_2;
		this.termopar_3 = termopar_3;
		this.termopar_amb = termopar_amb; 
	}




	public double getTermopar_1() {
		return termopar_1;
	}


	public void setTermopar_1(double termopar_1) {
		this.termopar_1 = termopar_1;
	}


	public double getTermopar_2() {
		return termopar_2;
	}


	public void setTermopar_2(double termopar_2) {
		this.termopar_2 = termopar_2;
	}


	public double getTermopar_3() {
		return termopar_3;
	}


	public void setTermopar_3(double termopar_3) {
		this.termopar_3 = termopar_3;
	}


	public double getTermopar_amb() {
		return termopar_amb;
	}


	public void setTermopar_amb(double termopar_amb) {
		this.termopar_amb = termopar_amb;
	}




	public double getMedia() {
		return media;
	}



	public void setMedia(double media) {
		this.media = media;
	}




	public double getTemperaturaCorrigida() {
		return temperaturaCorrigida;
	}




	public void setTemperaturaCorrigida(double temperaturaCorrigida) {
		this.temperaturaCorrigida = temperaturaCorrigida;
	}
	
	
	
	
	
}

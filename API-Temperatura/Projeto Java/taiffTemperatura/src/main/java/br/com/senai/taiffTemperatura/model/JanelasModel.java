package br.com.senai.taiffTemperatura.model;

public class JanelasModel {
	
	private int valorInicial;
	private int valorFinal;
	
	public JanelasModel() {	}
	
	public JanelasModel(int valorInicial, int valorFinal) {
		super();
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
	}
	public int getValorInicial() {
		return valorInicial;
	}
	
	public void setValorInicial(int valorInicial) {
		this.valorInicial = valorInicial;
	}
	
	public int getValorFinal() {
		return valorFinal;
	}
	
	public void setValorFinal(int valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
	
}

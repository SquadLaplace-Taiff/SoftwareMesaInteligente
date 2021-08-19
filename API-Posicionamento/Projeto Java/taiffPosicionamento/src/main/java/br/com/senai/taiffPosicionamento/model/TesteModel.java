package br.com.senai.taiffPosicionamento.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Teste")
public class TesteModel {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String modelo;
	@OneToMany
	List<CoordenadaModel> coordenadas;
	@OneToOne
	ZeroPecaModel zeroPeca;
	
	
	public TesteModel() {
		
	}
	
	
	public TesteModel(String modelo, List<CoordenadaModel> coordenadas, ZeroPecaModel zeroPeca) {
		this.modelo = modelo;
		this.coordenadas = coordenadas;
		this.zeroPeca = zeroPeca;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public List<CoordenadaModel> getCoordenadas() {
		return coordenadas;
	}


	public void setCoordenadas(List<CoordenadaModel> coordenadas) {
		this.coordenadas = coordenadas;
	}


	public ZeroPecaModel getZeroPeca() {
		return zeroPeca;
	}


	public void setZeroPeca(ZeroPecaModel zeroPeca) {
		this.zeroPeca = zeroPeca;
	}
	
	
	
	
	
	
}

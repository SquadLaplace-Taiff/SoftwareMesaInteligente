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
	@OneToMany
	List<CoordenadaModel> coordenadas;
	@OneToOne
	ZeroPecaModel zeroPeca;
	
	
	public TesteModel(long id, List<CoordenadaModel> coordenadas, ZeroPecaModel zeroPeca) {
		
	}
	
	public TesteModel(long id, List<CoordenadaModel> coordenadas, ZeroPecaModel zeroPeca) {
		this.id = id;
		this.coordenadas = coordenadas;
		this.zeroPeca = zeroPeca;
	}

	public long getId() {
		return id;
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

package br.com.senai.taiffPosicionamento.model;

import java.util.List;

import javax.persistence.Column;
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
	private long id_teste;
	@Column(unique = true)
	private String modelo;
	@Column(unique = true)
	private String nome_teste;
	
	@OneToMany
	private List<CoordenadaModel> coordenada;
	@OneToOne
	private ZeroPecaModel zeroPeca;
	
	
	public TesteModel() {
	}


	public TesteModel(long id_teste, String modelo, String nome_teste, List<CoordenadaModel> coordenada,
			ZeroPecaModel zeroPeca) {
		
		this.id_teste = id_teste;
		this.modelo = modelo;
		this.nome_teste = nome_teste;
		this.coordenada = coordenada;
		this.zeroPeca = zeroPeca;
	}


	public long getId_teste() {
		return id_teste;
	}


	public void setId_teste(long id_teste) {
		this.id_teste = id_teste;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getNome_teste() {
		return nome_teste;
	}


	public void setNome_teste(String nome_teste) {
		this.nome_teste = nome_teste;
	}


	public List<CoordenadaModel> getCoordenada() {
		return coordenada;
	}


	public void setCoordenada(List<CoordenadaModel> coordenada) {
		this.coordenada = coordenada;
	}


	public ZeroPecaModel getZeroPeca() {
		return zeroPeca;
	}


	public void setZeroPeca(ZeroPecaModel zeroPeca) {
		this.zeroPeca = zeroPeca;
	}	
}

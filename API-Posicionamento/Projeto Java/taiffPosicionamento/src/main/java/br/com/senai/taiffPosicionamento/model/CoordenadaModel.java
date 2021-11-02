package br.com.senai.taiffPosicionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="coordenada")
public class CoordenadaModel {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long coordenada_id;
	
	private long teste_id;
	private int coordenada_x;
	private int coordenada_y;
	private int coordenada_z;
	private int eixo_r;
	private int tempo;
	private boolean zero_peca;

	public CoordenadaModel() {
		
	}

	public CoordenadaModel(long coordenada_id, long teste_id, int coordenada_x, int coordenada_y, int coordenada_z, int eixo_r, int tempo, boolean zero_peca) {
		
		this.coordenada_id = coordenada_id;
		this.teste_id = teste_id;
		this.coordenada_x = coordenada_x;
		this.coordenada_y = coordenada_y;
		this.coordenada_z = coordenada_z;
		this.eixo_r = eixo_r;
		this.tempo = tempo;
		this.zero_peca = zero_peca;
	}

	public long getCoordenada_id() {
		return coordenada_id;
	}

	public void setCoordenada_id(long coordenada_id) {
		this.coordenada_id = coordenada_id;
	}

	public long getTeste_id() {
		return teste_id;
	}

	public void setTeste_id(long teste_id) {
		this.teste_id = teste_id;
	}

	public int getCoordenada_x() {
		return coordenada_x;
	}

	public void setCoordenada_x(int coordenada_x) {
		this.coordenada_x = coordenada_x;
	}

	public int getCoordenada_y() {
		return coordenada_y;
	}

	public void setCoordenada_y(int coordenada_y) {
		this.coordenada_y = coordenada_y;
	}

	public int getCoordenada_z() {
		return coordenada_z;
	}

	public void setCoordenada_z(int coordenada_z) {
		this.coordenada_z = coordenada_z;
	}

	public int getEixo_r() {
		return eixo_r;
	}

	public void setEixo_r(int eixo_r) {
		this.eixo_r = eixo_r;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public boolean isZero_peca() {
		return zero_peca;
	}

	public void setZero_peca(boolean zero_peca) {
		this.zero_peca = zero_peca;
	}


	
}

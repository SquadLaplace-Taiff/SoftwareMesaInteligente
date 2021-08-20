package br.com.senai.taiffPosicionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.sun.istack.NotNull;

@Entity
@Table(name="coordenada")
public class CoordenadaModel {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long coordenada_id;
	
	private long teste_id;
	private int x;
	private int y;
	private int z;
	private int r;
	private int t;
	
	public CoordenadaModel() {
		
	}

	public CoordenadaModel(long coordenada_id, long teste_id, int x, int y, int z, int r, int t) {
		
		this.coordenada_id = coordenada_id;
		this.teste_id = teste_id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.t = t;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	
}

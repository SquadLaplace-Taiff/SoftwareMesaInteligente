package br.senai.taiffPosicionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coordenada")
public class CoodenadaModel {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int x;
	private int y;
	private int z;
	private int r;
	private int t;
	
	public CoodenadaModel() {
		
	}

	public CoodenadaModel(long id, int x, int y, int z, int r, int t) {
		
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.t = t;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

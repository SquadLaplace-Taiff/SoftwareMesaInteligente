package br.com.senai.taiffPosicionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Zero_peca")
public class ZeroPecaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long zeroPeca_Id;
	private Integer x;
	private Integer y;
	private Integer z;
	private long teste_id;
	
	
	public ZeroPecaModel() {
	}


	public ZeroPecaModel(long zeroPeca_Id, Integer x, Integer y, Integer z, long teste_id) {
	
		this.zeroPeca_Id = zeroPeca_Id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.teste_id = teste_id;
	}


	public long getZeroPeca_Id() {
		return zeroPeca_Id;
	}


	public void setZeroPeca_Id(long zeroPeca_Id) {
		this.zeroPeca_Id = zeroPeca_Id;
	}


	public Integer getX() {
		return x;
	}


	public void setX(Integer x) {
		this.x = x;
	}


	public Integer getY() {
		return y;
	}


	public void setY(Integer y) {
		this.y = y;
	}


	public Integer getZ() {
		return z;
	}


	public void setZ(Integer z) {
		this.z = z;
	}


	public long getTeste_id() {
		return teste_id;
	}


	public void setTeste_id(long teste_id) {
		this.teste_id = teste_id;
	}
	
	

		
}

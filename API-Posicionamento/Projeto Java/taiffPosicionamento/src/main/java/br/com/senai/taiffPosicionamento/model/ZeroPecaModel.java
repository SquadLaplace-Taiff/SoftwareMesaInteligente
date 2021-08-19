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
	private long id_teste;
	private Integer x;
	private Integer y;
	private Integer z;
	
	
	public ZeroPecaModel(long id, Integer x, Integer y, Integer z) {
		super();
		this.id_teste = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public long getId() {
		return id_teste;
	}
	public void setId(long id) {
		this.id_teste = id;
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
}

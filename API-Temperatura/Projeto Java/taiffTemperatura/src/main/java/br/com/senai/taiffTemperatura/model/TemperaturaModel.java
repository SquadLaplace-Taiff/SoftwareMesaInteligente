package br.com.senai.taiffTemperatura.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Temperaturas")
public class TemperaturaModel {
	
	@Id @Column(nullable=false)
	private LocalDateTime dt_leitura;
	private int x;
	private int y;
	private int z;
	private int r;
	private float termopar_1;
	private float termopar_2;
	private float termopar_3;
	private float termopar_amb;
	
	
	
	public TemperaturaModel() {
		
	}



	public TemperaturaModel(LocalDateTime dt_leitura, int x, int y, int z, int r, float termopar_1, float termopar_2,
			float termopar_3, float termopar_amb) {
		super();
		this.dt_leitura = dt_leitura;
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.termopar_1 = termopar_1;
		this.termopar_2 = termopar_2;
		this.termopar_3 = termopar_3;
		this.termopar_amb = termopar_amb;
	}



	public LocalDateTime getDt_leitura() {
		return dt_leitura;
	}



	public void setDt_leitura(LocalDateTime dt_leitura) {
		this.dt_leitura = dt_leitura;
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



	public float getTermopar_1() {
		return termopar_1;
	}



	public void setTermopar_1(float termopar_1) {
		this.termopar_1 = termopar_1;
	}



	public float getTermopar_2() {
		return termopar_2;
	}



	public void setTermopar_2(float termopar_2) {
		this.termopar_2 = termopar_2;
	}



	public float getTermopar_3() {
		return termopar_3;
	}



	public void setTermopar_3(float termopar_3) {
		this.termopar_3 = termopar_3;
	}



	public float getTermopar_amb() {
		return termopar_amb;
	}



	public void setTermopar_amb(float termopar_amb) {
		this.termopar_amb = termopar_amb;
	}
	
	
	
	
	
	
	
}

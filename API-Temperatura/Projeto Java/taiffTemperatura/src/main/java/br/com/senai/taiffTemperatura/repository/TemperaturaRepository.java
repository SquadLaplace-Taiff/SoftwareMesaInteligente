package br.com.senai.taiffTemperatura.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.taiffTemperatura.model.TemperaturaModel;

public interface TemperaturaRepository extends JpaRepository<TemperaturaModel, LocalDateTime>{

	public List<TemperaturaModel> findByCoordenadaId(long coordenadaId);

}

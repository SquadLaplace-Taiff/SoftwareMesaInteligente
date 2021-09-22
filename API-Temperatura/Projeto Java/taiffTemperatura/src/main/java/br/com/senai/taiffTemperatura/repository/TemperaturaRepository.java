package br.com.senai.taiffTemperatura.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.senai.taiffTemperatura.dto.TemperaturaJanelaDto;
import br.com.senai.taiffTemperatura.model.TemperaturaModel;

public interface TemperaturaRepository extends JpaRepository<TemperaturaModel, LocalDateTime>{

	public List<TemperaturaModel> findByCoordenadaId(long coordenadaId);
	
	
	 @Query("SELECT temperaturas FROM TemperaturaModel temperaturas")
     public List<TemperaturaModel> findWindow(@Param("valor_inicial") int valor_inicial, @Param("valor_final") int valor_final);
	 
	
}



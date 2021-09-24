package br.com.senai.taiffTemperatura.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.senai.taiffTemperatura.model.TemperaturaModel;

public interface TemperaturaRepository extends JpaRepository<TemperaturaModel, LocalDateTime>{

	public List<TemperaturaModel> findByCoordenadaId(long coordenadaId);
	
	
	 @Query("SELECT temperaturas FROM TemperaturaModel temperaturas WHERE temperaturas.coordenadaId = :coordenada_id ORDER BY temperaturas.dt_leitura")
     public List<TemperaturaModel> buscaTemperaturaPorOrdemDeData(@Param("coordenada_id") long coordenada_id);
	 
	
}



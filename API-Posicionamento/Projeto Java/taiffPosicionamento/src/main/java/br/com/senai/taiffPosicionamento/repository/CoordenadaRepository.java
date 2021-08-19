package br.com.senai.taiffPosicionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.taiffPosicionamento.model.CoordenadaModel;

public interface CoordenadaRepository extends JpaRepository<CoordenadaModel, Long>{
	
}

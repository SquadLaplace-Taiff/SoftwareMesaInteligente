package br.com.senai.taiffPosicionamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.taiffPosicionamento.model.TesteModel;

public interface TesteRepository extends JpaRepository <TesteModel, Long> {

	public List<TesteModel> findAllByModelo(String modelo);

}

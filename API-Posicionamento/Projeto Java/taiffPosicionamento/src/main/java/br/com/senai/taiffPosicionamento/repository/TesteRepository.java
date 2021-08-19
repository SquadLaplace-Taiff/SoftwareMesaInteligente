package br.com.senai.taiffPosicionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.taiffPosicionamento.model.TesteModel;

public interface TesteRepository extends JpaRepository <TesteModel, Long> {

}

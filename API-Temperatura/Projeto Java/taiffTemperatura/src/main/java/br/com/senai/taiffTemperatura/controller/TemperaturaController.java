package br.com.senai.taiffTemperatura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.taiffTemperatura.model.TemperaturaModel;
import br.com.senai.taiffTemperatura.repository.TemperaturaRepository;

@RestController
@RequestMapping("/temperatura")
public class TemperaturaController {

	@Autowired
	private TemperaturaRepository temperaturaRepository;
	
	@PostMapping
	public ResponseEntity<TemperaturaModel> registrarTemperatura(@RequestBody TemperaturaModel temperatura){
		
		try {
			temperaturaRepository.save(temperatura);
			return ResponseEntity.created(null).build();
		} 
		
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@GetMapping("/{coordenada_id}")
	public ResponseEntity<List<TemperaturaModel>> buscaTemperaturasPorCoordenada(@PathVariable long coordenadaId){
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.findByCoordenadaId(coordenadaId);
			return ResponseEntity.ok().body(listaTemperatura);
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
}

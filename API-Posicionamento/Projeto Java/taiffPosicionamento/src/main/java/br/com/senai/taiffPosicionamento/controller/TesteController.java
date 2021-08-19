package br.com.senai.taiffPosicionamento.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.taiffPosicionamento.model.TesteModel;
import br.com.senai.taiffPosicionamento.repository.CoordenadaRepository;
import br.com.senai.taiffPosicionamento.repository.TesteRepository;
import br.com.senai.taiffPosicionamento.repository.ZeroPecaRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private TesteRepository testeRepository;
	
	@Autowired
	private ZeroPecaRepository zeroPecaRepository;
	
	@Autowired
	private CoordenadaRepository coordenadaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TesteModel> criarTeste(TesteModel teste){
		
		testeRepository.save(teste);
				
		return ResponseEntity.created(null).body(teste);
	}
	
	
	@GetMapping
	public ResponseEntity<TesteModel> buscaTestePorModelo(@RequestBody String modelo){
		
		TesteModel teste = testeRepository.findByModelo(modelo);
		
		return ResponseEntity.ok().body(teste);	
	}
	
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TesteModel> editaTestePorId(@PathVariable long id, TesteModel testeNovo){
		
		Optional<TesteModel> testeOptional = testeRepository.findById(id);
		
		if (testeOptional.isPresent()) {
			TesteModel testeEditado = testeOptional.get();
			
			testeEditado.setZeroPeca(testeNovo.getZeroPeca());
			testeEditado.setCoordenadas(testeNovo.getCoordenadas());
			testeEditado.setModelo(testeNovo.getModelo());
			
			testeRepository.save(testeEditado);
						
			return ResponseEntity.ok().body(testeEditado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluiTestePorId(@PathVariable long id){
		
		testeRepository.deleteById(id);
		
		return ResponseEntity.ok(null);
		
	}
	
	
}

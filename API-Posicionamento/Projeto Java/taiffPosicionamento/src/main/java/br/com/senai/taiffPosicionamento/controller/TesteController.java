package br.com.senai.taiffPosicionamento.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.senai.taiffPosicionamento.model.CoordenadaModel;
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
	private CoordenadaRepository coordenadaRepository;
	@Autowired
	private ZeroPecaRepository zeroPecaRepository;
	
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<TesteModel> criarTeste(@RequestBody TesteModel teste){
		try {

			testeRepository.save(teste);
		
			return ResponseEntity.created(null).body(teste);
		} 
		
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET )
	public ResponseEntity<List<TesteModel>> buscaTudoMesmo(){
		try {
			List<TesteModel> listaTeste = testeRepository.findAll();
			return ResponseEntity.ok().body(listaTeste);
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET )
	public ResponseEntity<Optional<TesteModel>> buscaId(@PathVariable Long id) {
		try {
			Optional<TesteModel> buscaTeste = testeRepository.findById(id);
			return ResponseEntity.ok().body(buscaTeste);
		}
		
		catch (Exception e) {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	@RequestMapping(value="/{modelo}", method = RequestMethod.GET )
	public ResponseEntity<List<TesteModel>> buscaTestePorModelo(@PathVariable String modelo){
		try {
			
			List<TesteModel> teste = new ArrayList<>();
			
			teste = testeRepository.findAllByModelo(modelo);
			return ResponseEntity.ok().body(teste);
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT )
	@Transactional
	public ResponseEntity<TesteModel> editaTestePorId(@PathVariable long id, @RequestBody TesteModel testeNovo){
		
		Optional<TesteModel> testeOptional = testeRepository.findById(id);
		List<CoordenadaModel> listaCoordenadas = new ArrayList<>();
		
		if (testeOptional.isPresent()) {	
			for(CoordenadaModel coordenada: testeNovo.getCoordenada()) {
				
				coordenada.setTeste_id(id);
				coordenadaRepository.save(coordenada);
				listaCoordenadas.add(coordenada);
			}
			
			testeNovo.getZeroPeca().setTeste_id(id);
			zeroPecaRepository.save(testeNovo.getZeroPeca());
			
			if(testeNovo.getNome_teste() != null) {
				testeOptional.get().setNome_teste(testeNovo.getNome_teste());
			}
			testeOptional.get().setCoordenada(listaCoordenadas);
			testeOptional.get().setZeroPeca(testeNovo.getZeroPeca());
			testeRepository.save(testeOptional.get());
			
			return ResponseEntity.ok().body(testeOptional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
	@Transactional
	public ResponseEntity<?> excluiTestePorId(@PathVariable long id){
		try {
		testeRepository.deleteById(id);
		return ResponseEntity.ok(null);
		}
		
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
}

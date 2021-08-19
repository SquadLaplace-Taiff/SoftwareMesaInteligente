package br.com.senai.taiffPosicionamento.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.senai.taiffPosicionamento.model.ZeroPecaModel;
import br.com.senai.taiffPosicionamento.repository.ZeroPecaRepository;

@RestController
@RequestMapping("/posicao/zeroPeca")
public class ZeroPecaController {
	
	@Autowired
	ZeroPecaRepository zeroPecaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ZeroPecaModel> criarZeroPeca(@RequestBody ZeroPecaModel zeroPecaModel) {
		try {
			zeroPecaRepository.save(zeroPecaModel);
			return ResponseEntity.created(null).body(zeroPecaModel);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/{id_teste}")
	public ResponseEntity<ZeroPecaModel> buscarZeroPeca(@PathVariable Long id_teste) {
		Optional<ZeroPecaModel> zeroPeca = zeroPecaRepository.findById(id_teste);
		
		if (zeroPeca.isPresent()) {
			return ResponseEntity.ok(zeroPeca.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}

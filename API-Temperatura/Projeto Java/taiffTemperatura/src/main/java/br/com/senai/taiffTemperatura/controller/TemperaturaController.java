package br.com.senai.taiffTemperatura.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.taiffTemperatura.csvExport.CsvExportService;
import br.com.senai.taiffTemperatura.dto.TemperaturaJanelaDto;
import br.com.senai.taiffTemperatura.model.EstatisticaModel;
import br.com.senai.taiffTemperatura.model.JanelasModel;
import br.com.senai.taiffTemperatura.model.TemperaturaModel;
import br.com.senai.taiffTemperatura.repository.TemperaturaRepository;
import br.com.senai.taiffTemperatura.service.DataService;

@RestController
@RequestMapping("/temperatura")
public class TemperaturaController {
	
	DataService dataService = new DataService();
	
	EstatisticaModel estatistica = new EstatisticaModel(0, 0, 0, 0);

	private final CsvExportService csvExportService;
	
	 public TemperaturaController(CsvExportService csvExportService) {
	        this.csvExportService = csvExportService;
	 }

	@Autowired
	private TemperaturaRepository temperaturaRepository;
	
	 @RequestMapping(value = "/mediaGeral/{coordenadaId}", method = RequestMethod.GET)
	 public ResponseEntity<EstatisticaModel>  mediaGeral(@PathVariable long coordenadaId ) {
		   List<TemperaturaModel> temperaturas = temperaturaRepository.buscaTemperaturaPorOrdemDeData(coordenadaId);
           return ResponseEntity.ok().body(dataService.gerarMediaGeralDasJanelas(temperaturas));
	 }
	 
	 @RequestMapping(value= "/mediaJanelas/{coordenadaId}", method = RequestMethod.GET)
	 public ResponseEntity<List<EstatisticaModel>> mediaDasJanelas(@PathVariable long coordenadaId) {
		   List<TemperaturaModel> temperaturas = temperaturaRepository.buscaTemperaturaPorOrdemDeData(coordenadaId);
		   return ResponseEntity.ok().body(dataService.gerarMediaDeCadaJanelas(temperaturas));	
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TemperaturaModel> registrarTemperatura(@RequestBody TemperaturaModel temperatura){
		
		try {
			temperaturaRepository.save(temperatura);
			return ResponseEntity.created(null).build();
		} 
		
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@RequestMapping(value = "/{coordenadaId}", method = RequestMethod.GET)
	public ResponseEntity<List<TemperaturaModel>> buscaTemperaturasPorCoordenada(@PathVariable long coordenadaId){
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.findByCoordenadaId(coordenadaId);
			return ResponseEntity.ok().body(listaTemperatura);
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@RequestMapping(value = "/temperaturas/{id}", method = RequestMethod.GET)
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse, @PathVariable long id) throws IOException {
        
		servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"temperaturas.csv\"");
        
        csvExportService.convertendoTemperaturaEmCSV(servletResponse.getWriter(), id);
    }
	
	
	@RequestMapping(value = "/folhaDeRosto/{id}", method = RequestMethod.GET)
    public void gerarFolhadeRosto(HttpServletResponse servletResponse, @PathVariable long id) throws IOException {
        
		servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"FolhaDeRosto.csv\"");
        
        csvExportService.geraFolhaDeRostoCSV(servletResponse.getWriter(), id);
    }
	
	
	
	
	
	
	
}

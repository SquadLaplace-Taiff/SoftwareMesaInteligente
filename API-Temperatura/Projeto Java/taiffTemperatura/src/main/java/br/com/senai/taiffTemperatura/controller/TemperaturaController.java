package br.com.senai.taiffTemperatura.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public ResponseEntity<List<TemperaturaJanelaDto>> buscaTemperaturasPorCoordenada(@PathVariable long coordenadaId){
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.findByCoordenadaId(coordenadaId);
			List<TemperaturaJanelaDto> listaTemperaturaDto = new ArrayList<TemperaturaJanelaDto>();
			
			int linha = 1;
			for (TemperaturaModel temperaturaModel : listaTemperatura) {
				TemperaturaJanelaDto temperaturaDto = new TemperaturaJanelaDto(temperaturaModel); 
				temperaturaDto.setLinha(linha);
				linha++;
				
				listaTemperaturaDto.add(temperaturaDto);
			}
			
			
			return ResponseEntity.ok().body(listaTemperaturaDto);
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/{dataString}/{coordenadaId}", method = RequestMethod.GET)
	public ResponseEntity<List<TemperaturaModel>> buscaUltimasTemperaturas(@PathVariable long coordenadaId, @PathVariable String dataString){
		try {
			System.out.println("Antes " + dataString);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
			System.out.println("Meio " + dataString);
			LocalDateTime data = LocalDateTime.parse(dataString, formatter);
			System.out.println("Depois " + dataString);
			
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.buscaUltimasTemperatura(coordenadaId, data);
					
			return ResponseEntity.ok().body(listaTemperatura);
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@RequestMapping(value = "/temperaturasCSV/{id}", method = RequestMethod.GET)
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse, @PathVariable long id) throws IOException {
        
		servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"temperaturas.csv\"");
        
        csvExportService.convertendoTemperaturaEmCSV(servletResponse.getWriter(), id);
    }
	
	
	@RequestMapping(value = "/folhaDeRostoCSV/{id}", method = RequestMethod.GET)
    public void gerarFolhadeRostoCSV(HttpServletResponse servletResponse, @PathVariable long id) throws IOException {
        
		servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"FolhaDeRosto.csv\"");
        
        csvExportService.geraFolhaDeRostoCSV(servletResponse.getWriter(), id);
    }
	
	@RequestMapping(value = "/janelas/{coordenadaId}", method = RequestMethod.GET)
	public ResponseEntity<List<JanelasModel>> gerarJanelas(@PathVariable long coordenadaId){
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.findByCoordenadaId(coordenadaId);
			return ResponseEntity.ok().body(dataService.generateWindow(listaTemperatura));
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@RequestMapping(value = "/folhaDeRosto/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<EstatisticaModel>> gerarFolhaDeRosto(@PathVariable long id){
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.findByCoordenadaId(id);

			List<EstatisticaModel> listaEstatistica = dataService.gerarMediaDeCadaJanelas(listaTemperatura);
			
			List<EstatisticaModel> listaEstatisticaCompleta = new ArrayList<EstatisticaModel>();
			
            for (EstatisticaModel estatistica : listaEstatistica) {
            	
            	estatistica.setMedia((
            			estatistica.getTermopar_1() +
            			estatistica.getTermopar_2() +
            			estatistica.getTermopar_3() )
            			/ 3 );
            	estatistica.setTemperaturaCorrigida(
            			estatistica.getMedia() -
            			estatistica.getTermopar_amb() + 25);
               
            	listaEstatisticaCompleta.add(estatistica);
            	
            }          
	
			
			return ResponseEntity.ok().body(listaEstatisticaCompleta);
		}
		
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
}

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
	
	public List<TemperaturaJanelaDto> TartarugaAlbinaVerde(int valor_inicial, int valor_final) {
		List<TemperaturaModel> temperaturas = temperaturaRepository.findWindow(1);
		
		List<TemperaturaJanelaDto> temperaturaJanelas = new ArrayList<TemperaturaJanelaDto>();
		int linha = 0;
		for (TemperaturaModel temperatura : temperaturas) {
			linha++;
			TemperaturaJanelaDto tempJanelas = new TemperaturaJanelaDto(temperatura.getTermopar_1(), temperatura.getTermopar_2(), temperatura.getTermopar_3(), temperatura.getTermopar_amb(), linha);
			
			if (tempJanelas.getLinha() >= valor_inicial && tempJanelas.getLinha() <= valor_final) {
				
				temperaturaJanelas.add(tempJanelas);
			}
			
		}
		
		return temperaturaJanelas;
		
	 }
	
	 @RequestMapping(value = "/teste/{coordenadaId}", method = RequestMethod.GET)
	 public void teste(@PathVariable long coordenadaId) {
//		 dataService.getData(temperaturaRepository.findByCoordenadaId(1));
//		 for (TemperaturaJanelaDto janelaTemperaturas : TartarugaAlbinaVerde()) {
//			System.out.println("termopar_1 " + janelaTemperaturas.getTermopar_1() + "termopar_2 " + janelaTemperaturas.getTermopar_2() + "termopar_3 " +janelaTemperaturas.getTermopar_3() + "termopar_amb " + janelaTemperaturas.getTermopar_amb());
//		} 
		  List<JanelasModel> janelasModel = new ArrayList<JanelasModel>();
		  List<TemperaturaJanelaDto> janelasDto = new ArrayList<TemperaturaJanelaDto>();	  
		  
		  janelasModel = dataService.generateWindow(temperaturaRepository.findByCoordenadaId(coordenadaId));
		  	
		  janelasModel.forEach(janelas -> {
			 
			janelasDto.addAll(TartarugaAlbinaVerde(janelas.getValorInicial(), janelas.getValorFinal()));
			
		 });
		
		 for (TemperaturaJanelaDto temperaturas : janelasDto) {
			 estatistica.setTermopar_1(temperaturas.getTermopar_1() + estatistica.getTermopar_1());
			 estatistica.setTermopar_2(temperaturas.getTermopar_2() + estatistica.getTermopar_2());
			 estatistica.setTermopar_3(temperaturas.getTermopar_3() + estatistica.getTermopar_3());
			 estatistica.setTermopar_amb(temperaturas.getTermopar_amb() + estatistica.getTermopar_amb());
		}
		 
		 System.out.println("Média Termopar 1: " + (estatistica.getTermopar_1() / janelasDto.size()) + "\n Média Termopar 2: " + (estatistica.getTermopar_2() / janelasDto.size()) + "\n Média Termopar 3: " + (estatistica.getTermopar_3() / janelasDto.size()) + "\n Média Termopar amb: " + (estatistica.getTermopar_amb() / janelasDto.size()));
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
	
	
	@RequestMapping(value = "/csv", method = RequestMethod.GET)
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"temperaturas.csv\"");
        long id = 1;
        csvExportService.convertendoTemperaturaEmCSV(servletResponse.getWriter(), id);
    }
	
	
	
	
	
	
	
	
}

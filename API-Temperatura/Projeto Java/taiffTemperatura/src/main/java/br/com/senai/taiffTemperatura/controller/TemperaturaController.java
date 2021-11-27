package br.com.senai.taiffTemperatura.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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
import br.com.senai.taiffTemperatura.model.Key;
import br.com.senai.taiffTemperatura.model.RampaModel;
import br.com.senai.taiffTemperatura.model.RampasModel;
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

	@RequestMapping(value = "/mediaGeral", method = RequestMethod.GET)
	public ResponseEntity<EstatisticaModel> mediaGeral() {
		List<TemperaturaModel> temperaturas = temperaturaRepository.buscaTemperaturaPorOrdemDeData();
		return ResponseEntity.ok().body(dataService.gerarMediaGeralDasJanelas(temperaturas));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE )
	public ResponseEntity<?> apagarBanco(@RequestBody Key key){
		String senha = "3e3BT#GzAD0jLxeLGq";
		if(key.getKey().equals(senha) ) {
			try {
				temperaturaRepository.deleteAll();
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
			}
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Chave inválida ");
		}
	
	}
	@RequestMapping(value = "/rampaSubida", method = RequestMethod.GET)
	public ResponseEntity<List<RampasModel>> rampaSubida(){
		List<TemperaturaModel> temperaturas = temperaturaRepository.buscaTemperaturaPorOrdemDeData();
		return  ResponseEntity.ok().body(dataService.rampaSubida(temperaturas, 10, 1));
	}
	

	@RequestMapping(value = "/mediaJanelas", method = RequestMethod.GET)
	public ResponseEntity<List<EstatisticaModel>> mediaDasJanelas() {
		List<TemperaturaModel> temperaturas = temperaturaRepository.buscaTemperaturaPorOrdemDeData();
		return ResponseEntity.ok().body(dataService.gerarMediaDeCadaJanelas(temperaturas));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TemperaturaModel> registrarTemperatura(@RequestBody TemperaturaModel temperatura) {

		try {
			temperaturaRepository.save(temperatura);
			return ResponseEntity.created(null).build();
		}

		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TemperaturaJanelaDto>> buscaTemperaturasPorCoordenada() {
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.buscaTemperaturaPorOrdemDeData();
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

	@RequestMapping(value = "/{dataString}", method = RequestMethod.GET)
	public ResponseEntity<TemperaturaModel> buscaUltimasTemperaturas(@PathVariable String dataString) {
		try {
			
			dataString = dataString.replace("T", " ");

			switch (dataString.length()) {
				case 20:
					dataString = dataString.concat("000000");
					break;
				case 21:
					dataString = dataString.concat("00000");
					break;
				case 22:
					dataString = dataString.concat("0000");
					break;
				case 23:
					dataString = dataString.concat("000");
					break;
				case 24:
					dataString = dataString.concat("00");
					break;
			}

			System.out.println("Antes " + dataString);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
			System.out.println("Meio " + dataString);
			LocalDateTime data = LocalDateTime.parse(dataString, formatter);
			System.out.println("Depois " + dataString);

			List<TemperaturaModel> listaTemperatura = temperaturaRepository.buscaUltimasTemperatura(data);

			return ResponseEntity.ok().body(listaTemperatura.get(0));
		}

		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/temperaturasCSV", method = RequestMethod.GET)
	public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {

		servletResponse.setContentType("text/csv");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"temperaturas.csv\"");

		csvExportService.convertendoTemperaturaEmCSV(servletResponse.getWriter());
	}

	@RequestMapping(value = "/folhaDeRostoCSV", method = RequestMethod.GET)
	public void gerarFolhadeRostoCSV(HttpServletResponse servletResponse) throws IOException {

		servletResponse.setContentType("text/csv");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"FolhaDeRosto.csv\"");

		csvExportService.geraFolhaDeRostoCSV(servletResponse.getWriter());
	}

	@RequestMapping(value = "/janelas", method = RequestMethod.GET)
	public ResponseEntity<List<JanelasModel>> gerarJanelas() {
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.buscaTemperaturaPorOrdemDeData();
			return ResponseEntity.ok().body(dataService.generateWindow(listaTemperatura));
		}

		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/folhaDeRosto", method = RequestMethod.GET)
	public ResponseEntity<List<EstatisticaModel>> gerarFolhaDeRosto() {
		try {
			List<TemperaturaModel> listaTemperatura = temperaturaRepository.buscaTemperaturaPorOrdemDeData();

			List<EstatisticaModel> listaEstatistica = dataService.gerarMediaDeCadaJanelas(listaTemperatura);

			List<EstatisticaModel> listaEstatisticaCompleta = new ArrayList<EstatisticaModel>();

			for (EstatisticaModel estatistica : listaEstatistica) {

				estatistica.setMedia(
						(estatistica.getTermopar_1() + estatistica.getTermopar_2() + estatistica.getTermopar_3()) / 3);
				estatistica.setTemperaturaCorrigida(estatistica.getMedia() - estatistica.getTermopar_amb() + 25);

				listaEstatisticaCompleta.add(estatistica);

			}

			return ResponseEntity.ok().body(listaEstatisticaCompleta);
		}

		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}

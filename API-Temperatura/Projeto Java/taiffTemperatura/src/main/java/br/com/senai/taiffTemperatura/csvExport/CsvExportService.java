package br.com.senai.taiffTemperatura.csvExport;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.taiffTemperatura.model.TemperaturaModel;
import br.com.senai.taiffTemperatura.repository.TemperaturaRepository;

@Service
public class CsvExportService {
	
	private final TemperaturaRepository temperaturaRepository;
	
	public CsvExportService (TemperaturaRepository temperaturaRepository) {
		this.temperaturaRepository = temperaturaRepository;
	}
	
	
	public void convertendoTemperaturaEmCSV(Writer writer, long coordenadaId) {
		List<TemperaturaModel> temperaturas = temperaturaRepository.findByCoordenadaId(coordenadaId);
		
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("dt_leitura", "coordenadaId", "termopar_1", "termopar_2", "termopar_3", "termopar_amb", "linha");
			int linha = 1;
            for (TemperaturaModel temperatura : temperaturas) {
                csvPrinter.printRecord(temperatura.getDt_leitura(), temperatura.getCoordenadaId(), temperatura.getTermopar_1(), 
                	temperatura.getTermopar_2(), temperatura.getTermopar_3(), temperatura.getTermopar_amb(), linha);
                linha++;
            }
        } catch (IOException e) {
            
        }
	}
	
}

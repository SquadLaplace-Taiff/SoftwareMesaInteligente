package br.com.senai.taiffTemperatura.csvExport;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import br.com.senai.taiffTemperatura.model.EstatisticaModel;
import br.com.senai.taiffTemperatura.model.TemperaturaModel;
import br.com.senai.taiffTemperatura.repository.TemperaturaRepository;
import br.com.senai.taiffTemperatura.service.DataService;

@Service
public class CsvExportService {
	
	private final TemperaturaRepository temperaturaRepository;
	
	public CsvExportService (TemperaturaRepository temperaturaRepository) {
		this.temperaturaRepository = temperaturaRepository;
	}
	public String replasePontoParaVirgula(double temp ) {
		return String.valueOf(temp).replace(".", ",");
		
	}
	
	public void convertendoTemperaturaEmCSV(Writer writer, long coordenadaId) {
		List<TemperaturaModel> temperaturas = temperaturaRepository.findByCoordenadaId(coordenadaId);
		
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'))) {
			csvPrinter.printRecord("dt_leitura", "coordenadaId", "termopar_1", "termopar_2", "termopar_3", 
					"termopar_amb", "linha");
			int linha = 1;
            for (TemperaturaModel temperatura : temperaturas) {
                csvPrinter.printRecord(temperatura.getDt_leitura(), temperatura.getCoordenadaId(), 
                		replasePontoParaVirgula(temperatura.getTermopar_1()), 
                		replasePontoParaVirgula(temperatura.getTermopar_2()), 
                		replasePontoParaVirgula(temperatura.getTermopar_3()), 
                		replasePontoParaVirgula(temperatura.getTermopar_amb()), linha);
                linha++;
            }
        } catch (IOException e) {
            
        }
	}
	
	
	public void geraFolhaDeRostoCSV(Writer writer, long coordenadaId) {
		List<TemperaturaModel> temperaturas = temperaturaRepository.findByCoordenadaId(coordenadaId);
		
		DataService dataService = new DataService();
		List<EstatisticaModel> listaEstatistica = dataService.gerarMediaDeCadaJanelas(temperaturas);
		
		
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'))) {
			csvPrinter.printRecord("Janela", "Temperatura Ambiente", "Termopar 1", "Termopar 2", "Termopar 3", 
					"Temperatura Média", "Temperatura Corrigida");
			
			float tempAmb = 0;
			float termo1 = 0;
			float termo2 = 0;
			float termo3 = 0;
			float tempMed = 0;
			float tempCor = 0;
			
			int janela = 1;
            for (EstatisticaModel estatistica : listaEstatistica) {
            	csvPrinter.printRecord(
            			janela,
            			replasePontoParaVirgula(estatistica.getTermopar_amb()),
            			replasePontoParaVirgula(estatistica.getTermopar_1()),
            			replasePontoParaVirgula(estatistica.getTermopar_2()),
            			replasePontoParaVirgula(estatistica.getTermopar_3()),
            			replasePontoParaVirgula(estatistica.getMedia()),
            			replasePontoParaVirgula(estatistica.getTemperaturaCorrigida())
            			);
            	
            	tempAmb += estatistica.getTermopar_amb();
            	termo1 += estatistica.getTermopar_1();
            	termo2 += estatistica.getTermopar_2();
            	termo3 += estatistica.getTermopar_3();
            	tempMed += estatistica.getMedia();
            	tempCor += estatistica.getTemperaturaCorrigida();
                
                janela++;
            }
            
            janela -= 1;
            
            tempAmb /= janela;
            termo1 /= janela;
            termo2 /= janela;
            termo3 /= janela;
            tempMed /= janela;
            tempCor /= janela;
            
            csvPrinter.printRecord(
            		"Média",
            		replasePontoParaVirgula(tempAmb),
            		replasePontoParaVirgula(termo1),
            		replasePontoParaVirgula(termo2),
            		replasePontoParaVirgula(termo3),
            		replasePontoParaVirgula(tempMed),
            		replasePontoParaVirgula(tempCor)            		
            		);
            
        } catch (IOException e) {
            
        }
	}
	
}

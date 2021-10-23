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
	
	public void convertendoTemperaturaEmCSV(Writer writer) {
		List<TemperaturaModel> temperaturas = temperaturaRepository.buscaTemperaturaPorOrdemDeData();
		
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'))) {
			csvPrinter.printRecord( "Linha", "Data da leitura", "Termopar 1", "Termopar 2", "Termopar 3", 
					"Termopar ambiente", "Codernada X", "Codernada Y", "Codernada Z", "Codernada R");
			int linha = 1;
            for (TemperaturaModel temperatura : temperaturas) {
                csvPrinter.printRecord(linha,temperatura.getDt_leitura(),
                		replasePontoParaVirgula(temperatura.getTermopar_1()), 
                		replasePontoParaVirgula(temperatura.getTermopar_2()), 
                		replasePontoParaVirgula(temperatura.getTermopar_3()), 
                		replasePontoParaVirgula(temperatura.getTermopar_amb()),
                		temperatura.getX(),
                		temperatura.getY(),
                		temperatura.getZ(),
                		temperatura.getR());
                        
                linha++;
            }
        } catch (IOException e) {
            
        }
	}
	
	
	public void geraFolhaDeRostoCSV(Writer writer) {
		List<TemperaturaModel> temperaturas = temperaturaRepository.buscaTemperaturaPorOrdemDeData();
		
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

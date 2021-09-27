package br.com.senai.taiffTemperatura.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senai.taiffTemperatura.dto.TemperaturaJanelaDto;
import br.com.senai.taiffTemperatura.model.EstatisticaModel;
import br.com.senai.taiffTemperatura.model.JanelasModel;
import br.com.senai.taiffTemperatura.model.TemperaturaModel;
import br.com.senai.taiffTemperatura.repository.TemperaturaRepository;

public class DataService {

	@Autowired
	private TemperaturaRepository temperaturaRepository;

	private List<TemperaturaModel> temperaturas;

	public DataService() {
	}

	public void getData(List<TemperaturaModel> temperaturas) {
		this.generateWindow(temperaturas);
	}

	public List<TemperaturaJanelaDto> pegarJanela(int valor_inicial, int valor_final,
		   List<TemperaturaModel> temperaturas) {

		List<TemperaturaJanelaDto> temperaturaJanelas = new ArrayList<TemperaturaJanelaDto>();
		int linha = 0;
		for (TemperaturaModel temperatura : temperaturas) {
			linha++;
			TemperaturaJanelaDto tempJanelas = new TemperaturaJanelaDto(temperatura.getTermopar_1(),
					temperatura.getTermopar_2(), temperatura.getTermopar_3(), temperatura.getTermopar_amb(), linha);

			if (tempJanelas.getLinha() >= valor_inicial && tempJanelas.getLinha() <= valor_final) {

				temperaturaJanelas.add(tempJanelas);
			}

		}

		return temperaturaJanelas;

	}

	public List<EstatisticaModel> gerarMediaDeCadaJanelas(List<TemperaturaModel> listaTemperaturas) {

		List<JanelasModel> janelasModel = new ArrayList<JanelasModel>();
		List<EstatisticaModel> estatisticaModel = new ArrayList<EstatisticaModel>();
		

		janelasModel = generateWindow(listaTemperaturas);
		
		janelasModel.forEach(janelas -> {
			List<TemperaturaJanelaDto> janelasDto = new ArrayList<TemperaturaJanelaDto>();
			EstatisticaModel estatistica = new EstatisticaModel(0, 0, 0, 0);
			janelasDto = pegarJanela(janelas.getValorInicial(), janelas.getValorFinal(), listaTemperaturas);
			
			janelasDto.forEach(temperaturas -> {
				estatistica.setTermopar_1(temperaturas.getTermopar_1() + estatistica.getTermopar_1());
				estatistica.setTermopar_2(temperaturas.getTermopar_2() + estatistica.getTermopar_2());
				estatistica.setTermopar_3(temperaturas.getTermopar_3() + estatistica.getTermopar_3());
				estatistica.setTermopar_amb(temperaturas.getTermopar_amb() + estatistica.getTermopar_amb());
			});
			estatistica.setTermopar_1(estatistica.getTermopar_1() / janelasDto.size());
			estatistica.setTermopar_2(estatistica.getTermopar_2() / janelasDto.size());
			estatistica.setTermopar_3(estatistica.getTermopar_3() / janelasDto.size());
			estatistica.setTermopar_amb(estatistica.getTermopar_amb() /janelasDto.size());
			
			estatistica.setMedia((estatistica.getTermopar_1() + estatistica.getTermopar_2() 
				+ estatistica.getTermopar_3()) / 3);
			
			estatistica.setTemperaturaCorrigida(estatistica.getMedia() - estatistica.getTermopar_amb() + 25);
			
			estatisticaModel.add(estatistica);
		});

		

		return estatisticaModel;
	}

	public EstatisticaModel gerarMediaGeralDasJanelas(List<TemperaturaModel> listaTemperaturas) {

		List<JanelasModel> janelasModel = new ArrayList<JanelasModel>();
		List<TemperaturaJanelaDto> janelasDto = new ArrayList<TemperaturaJanelaDto>();
		EstatisticaModel estatistica = new EstatisticaModel(0, 0, 0, 0);

		janelasModel = generateWindow(listaTemperaturas);

		janelasModel.forEach(janelas -> {

			janelasDto.addAll(pegarJanela(janelas.getValorInicial(), janelas.getValorFinal(), listaTemperaturas));

		});

		for (TemperaturaJanelaDto temperaturas : janelasDto) {
			estatistica.setTermopar_1(temperaturas.getTermopar_1() + estatistica.getTermopar_1());
			estatistica.setTermopar_2(temperaturas.getTermopar_2() + estatistica.getTermopar_2());
			estatistica.setTermopar_3(temperaturas.getTermopar_3() + estatistica.getTermopar_3());
			estatistica.setTermopar_amb(temperaturas.getTermopar_amb() + estatistica.getTermopar_amb());
		}

		estatistica.setTermopar_1(estatistica.getTermopar_1() / janelasDto.size());
		estatistica.setTermopar_2(estatistica.getTermopar_2() / janelasDto.size());
		estatistica.setTermopar_3(estatistica.getTermopar_3() / janelasDto.size());
		estatistica.setTermopar_amb(estatistica.getTermopar_amb() / janelasDto.size());

		return estatistica;
	}

	public List<JanelasModel> generateWindow(List<TemperaturaModel> listaTemperaturas) {

		double temp_new;
		double temp_old = 0;
		double variation_new = 0;

		boolean inWindow = false;

		int RAMP_UP_THRESHOLD = 5;
		int RAMP_DOWN_THRESHOLD = -3;
		int RAMP_UP_PAD = 32;
		int RAMP_DOWN_PAD = -8;

		List<JanelasModel> janelas = new ArrayList<JanelasModel>();

		int auxValorInicial = 0;
		int auxValorFinal = 0;

		for (int i = 0; i < listaTemperaturas.size(); i++) {
			JanelasModel janelasModel = new JanelasModel();
			if (i >= 2) {
				temp_new = (listaTemperaturas.get(i).getTermopar_1() + listaTemperaturas.get(i - 1).getTermopar_1()
						+ listaTemperaturas.get(i - 2).getTermopar_1()) / 3;

				if (i == 2) {
					temp_old = temp_new;
				}

				variation_new = temp_new - temp_old;
				temp_old = temp_new;

				// Entrando na janela
				if (!inWindow && variation_new >= RAMP_UP_THRESHOLD) {
					auxValorInicial = i;
					inWindow = true;
				}

				// Saindo da janela
				if (inWindow && variation_new <= RAMP_DOWN_THRESHOLD) {
					auxValorFinal = i;
					inWindow = false;

					if (auxValorInicial != 0 && auxValorFinal != 0 && !inWindow) {

						if ((auxValorFinal - auxValorInicial) > 50) {
							janelasModel.setValorInicial(auxValorInicial + RAMP_UP_PAD);
							janelasModel.setValorFinal(auxValorFinal + RAMP_DOWN_PAD);

							System.out.println("valor inicial: " + (auxValorInicial + RAMP_UP_PAD));
							System.out.println("valor final: " + (auxValorFinal + RAMP_DOWN_PAD));

							janelas.add(janelasModel);
						}
					}

				}

			}
		}

//		System.out.println(janelas.get(3).getValorInicial() + "," + janelas.get(3).getValorFinal());

		return janelas;
	}

	public List<TemperaturaModel> getTemperaturas() {
		return temperaturas;
	}

	public void setTemperatuas(List<TemperaturaModel> temperaturas) {
		this.temperaturas = temperaturas;
	}
}

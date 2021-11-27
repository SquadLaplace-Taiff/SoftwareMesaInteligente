package br.com.senai.taiffTemperatura.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senai.taiffTemperatura.dto.TemperaturaJanelaDto;
import br.com.senai.taiffTemperatura.model.EstatisticaModel;
import br.com.senai.taiffTemperatura.model.JanelasModel;
import br.com.senai.taiffTemperatura.model.RampaModel;
import br.com.senai.taiffTemperatura.model.RampasModel;
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
	
	private double media(int ini, int qtd, double[] vetor) {
		double acum = 0;
		for (int j = ini; j > (ini - qtd) ; j--) {
			acum += vetor[j];
		}
		return acum / qtd;
	}

	public List<RampasModel> rampaSubida(List<TemperaturaModel> temperaturas, int pontosMedia, double variacao) {
		
		var rampasTermopar1 = new RampasModel();
		var rampasTermopar2 = new RampasModel();
		var rampasTermopar3 = new RampasModel();
		
		RampaModel rampaSubindoTermopar1 = new RampaModel();
		RampaModel rampaDescendoTermopar1 = new RampaModel();
		
		RampaModel rampaSubindoTermopar2 = new RampaModel();
		RampaModel rampaDescendoTermopar2 = new RampaModel();
		
		RampaModel rampaSubindoTermopar3 = new RampaModel();
		RampaModel rampaDescendoTermopar3 = new RampaModel();
		
		boolean inicioRampaTerm1 = true;
		boolean inicioRampaTerm2 = true;
		boolean inicioRampaTerm3 = true;
		
		double[] termopar1 = new double[temperaturas.size()];
		double[] termopar2 = new double[temperaturas.size()];
		double[] termopar3 = new double[temperaturas.size()];
		int x = 0;
		
		for (TemperaturaModel temperatura : temperaturas) {
			termopar1[x] = temperatura.getTermopar_1();
			termopar2[x] = temperatura.getTermopar_2();
			termopar3[x] = temperatura.getTermopar_3();
			x++;
		}
		
		double temperaturaTermopar1Anterior = 0;
		double temperaturaTermopar2Anterior = 0;
		double temperaturaTermopar3Anterior = 0;		
		
		for (int i = 9; i < temperaturas.size(); i++) {
			double temperaturaTermopar1Atual = media(i, 10, termopar1);
			double temperaturaTermopar2Atual = media(i, 10, termopar2);
			double temperaturaTermopar3Atual = media(i, 10, termopar3);
			
			if(i == 9) {
				temperaturaTermopar1Anterior = temperaturaTermopar1Atual;
				temperaturaTermopar2Anterior = temperaturaTermopar2Atual;
				temperaturaTermopar3Anterior = temperaturaTermopar3Atual;
			}
			double diferencaTemperaturaTermopar1 = temperaturaTermopar1Atual - temperaturaTermopar1Anterior;
			double diferencaTemperaturaTermopar2 = temperaturaTermopar2Atual - temperaturaTermopar2Anterior;
			double diferencaTemperaturaTermopar3 = temperaturaTermopar3Atual - temperaturaTermopar3Anterior;

			temperaturaTermopar1Anterior = temperaturaTermopar1Atual;
			temperaturaTermopar2Anterior = temperaturaTermopar2Atual;
			temperaturaTermopar3Anterior = temperaturaTermopar3Atual;
			
			
			
			if (Math.abs(diferencaTemperaturaTermopar1) < 1) {
				if(!inicioRampaTerm1) {
					
					if(rampaSubindoTermopar1.getFim() == null) {
						rampaSubindoTermopar1.setFim(temperaturas.get(i - 2).getDt_leitura());
						rampaSubindoTermopar1.setDuracao(rampaSubindoTermopar1.getInicio().until(rampaSubindoTermopar1.getFim(), ChronoUnit.MILLIS));
					}
					
					inicioRampaTerm1 = true;
				}
			} else {
				if (inicioRampaTerm1) {
					
					if (rampaSubindoTermopar1.getInicio() == null) {
						rampaSubindoTermopar1.setInicio(temperaturas.get(i).getDt_leitura());
						rampaSubindoTermopar1.setRampaSubindo(true);
						rampaSubindoTermopar1.setTermopar("Termopar1");
					} else {
						rampaDescendoTermopar1.setInicio(temperaturas.get(i).getDt_leitura());
						rampaDescendoTermopar1.setRampaSubindo(false);
						rampaDescendoTermopar1.setTermopar("Termopar1");
					}
					
					inicioRampaTerm1 = false;
				} else if (!inicioRampaTerm1 && (i + 1) == temperaturas.size()) {
					rampaDescendoTermopar1.setFim(temperaturas.get(i).getDt_leitura());
					rampaDescendoTermopar1.setDuracao(rampaDescendoTermopar1.getInicio().until(rampaDescendoTermopar1.getFim(), ChronoUnit.MILLIS));
				}
			}
			
			
			
			
			if (Math.abs(diferencaTemperaturaTermopar2) < 1) {
				if(!inicioRampaTerm2) {
					
					if(rampaSubindoTermopar2.getFim() == null) {
						rampaSubindoTermopar2.setFim(temperaturas.get(i - 2).getDt_leitura());
						rampaSubindoTermopar2.setDuracao(rampaSubindoTermopar2.getInicio().until(rampaSubindoTermopar2.getFim(), ChronoUnit.MILLIS));
					}
					
					inicioRampaTerm2 = true;
				}
			} else {
				if (inicioRampaTerm2) {
					
					if (rampaSubindoTermopar2.getInicio() == null) {
						rampaSubindoTermopar2.setInicio(temperaturas.get(i).getDt_leitura());
						rampaSubindoTermopar2.setRampaSubindo(true);
						rampaSubindoTermopar2.setTermopar("Termopar2");
					} else {
						rampaDescendoTermopar2.setInicio(temperaturas.get(i).getDt_leitura());
						rampaDescendoTermopar2.setRampaSubindo(false);
						rampaDescendoTermopar2.setTermopar("Termopar2");
					}
					
					inicioRampaTerm2 = false;
				} else if (!inicioRampaTerm2 && (i + 1) == temperaturas.size()) {
					rampaDescendoTermopar2.setFim(temperaturas.get(i).getDt_leitura());
					rampaDescendoTermopar2.setDuracao(rampaDescendoTermopar2.getInicio().until(rampaDescendoTermopar2.getFim(), ChronoUnit.MILLIS));
				}
			}

			
			if (Math.abs(diferencaTemperaturaTermopar3) < 1) {
				if(!inicioRampaTerm3) {
					
					if(rampaSubindoTermopar3.getFim() == null) {
						rampaSubindoTermopar3.setFim(temperaturas.get(i - 2).getDt_leitura());
						rampaSubindoTermopar3.setDuracao(rampaSubindoTermopar3.getInicio().until(rampaSubindoTermopar3.getFim(), ChronoUnit.MILLIS));
					}
					
					inicioRampaTerm3 = true;
				}
			} else {
				if (inicioRampaTerm3) {
					
					if (rampaSubindoTermopar3.getInicio() == null) {
						rampaSubindoTermopar3.setInicio(temperaturas.get(i).getDt_leitura());
						rampaSubindoTermopar3.setRampaSubindo(true);
						rampaSubindoTermopar3.setTermopar("Termopar3");
					} else {
						
						rampaDescendoTermopar3.setInicio(temperaturas.get(i).getDt_leitura());
						rampaDescendoTermopar3.setRampaSubindo(false);
						rampaDescendoTermopar3.setTermopar("Termopar3");
					}
					
					inicioRampaTerm3 = false;
				} else if (!inicioRampaTerm3 && (i + 1) == temperaturas.size()) {
					rampaDescendoTermopar3.setFim(temperaturas.get(i).getDt_leitura());
					rampaDescendoTermopar3.setDuracao(rampaDescendoTermopar3.getInicio().until(rampaDescendoTermopar3.getFim(), ChronoUnit.MILLIS));
				}
			}
		}
		
		rampasTermopar1.setRampaSubindo(rampaSubindoTermopar1);
		rampasTermopar1.setRampaDescendo(rampaDescendoTermopar1);
		
		rampasTermopar2.setRampaSubindo(rampaSubindoTermopar2);
		rampasTermopar2.setRampaDescendo(rampaDescendoTermopar2);
		
		rampasTermopar3.setRampaSubindo(rampaSubindoTermopar3);
		rampasTermopar3.setRampaDescendo(rampaDescendoTermopar3);
		
		var rampas = new ArrayList<RampasModel>();
		
		rampas.add(rampasTermopar1);
		rampas.add(rampasTermopar2);
		rampas.add(rampasTermopar3);
		
		return rampas;
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
			estatistica.setTermopar_amb(estatistica.getTermopar_amb() / janelasDto.size());

			estatistica.setMedia(
					(estatistica.getTermopar_1() + estatistica.getTermopar_2() + estatistica.getTermopar_3()) / 3);

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

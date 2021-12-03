package br.com.senai.taiffTemperatura.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
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
		for (int j = ini; j > (ini - qtd); j--) {
			acum += vetor[j];
		}
		return acum / qtd;
	}

	public double getMedia(int inicio, int fim, List<TemperaturaModel> temperaturas, int termopar) {
		double media = 0;

		for (int i = inicio; i < fim ; i++) {
			switch (termopar) {
			case 1:
				media += temperaturas.get(i).getTermopar_1();
				break;
			case 2:
				media += temperaturas.get(i).getTermopar_2();
				break;
			case 3:
				media += temperaturas.get(i).getTermopar_3();
				break;
			case 4:
				media += temperaturas.get(i).getTermopar_amb();
				break;
			}

		}

		return media / (fim - inicio);

	}

	public double getTemperaturaMinima(int inicio, int fim, List<TemperaturaModel> temperaturas, int termopar) {
		double minTemp = 999;
		for (int i = inicio; i <= fim; i++) {

			switch (termopar) {
			case 1:
				minTemp = (temperaturas.get(i).getTermopar_1() < minTemp) ? temperaturas.get(i).getTermopar_1()
						: minTemp;
				break;
			case 2:
				minTemp = (temperaturas.get(i).getTermopar_2() < minTemp) ? temperaturas.get(i).getTermopar_2()
						: minTemp;
				break;
			case 3:
				minTemp = (temperaturas.get(i).getTermopar_3() < minTemp) ? temperaturas.get(i).getTermopar_3()
						: minTemp;
				break;
			}

		}

		return minTemp;
	}

	public double getTemperaturaMaxima(int inicio, int fim, List<TemperaturaModel> temperaturas, int termopar) {
		double maxTemp = 0;
		for (int i = inicio; i <= fim; i++) {

			switch (termopar) {
			case 1:
				maxTemp = (temperaturas.get(i).getTermopar_1() > maxTemp) ? temperaturas.get(i).getTermopar_1()
						: maxTemp;
				break;
			case 2:
				maxTemp = (temperaturas.get(i).getTermopar_2() > maxTemp) ? temperaturas.get(i).getTermopar_2()
						: maxTemp;
				break;
			case 3:
				maxTemp = (temperaturas.get(i).getTermopar_3() > maxTemp) ? temperaturas.get(i).getTermopar_3()
						: maxTemp;
				break;
			}

		}

		return maxTemp;
	}

	public double getMediaAmb(int inicio, int fim, List<TemperaturaModel> temperaturas) {
		double media = 0;
		for (int i = inicio; i <= fim; i++) {
			media += temperaturas.get(i).getTermopar_amb();
		}

		media /= Math.abs(inicio - fim - 1);

		return media;
	}

	public List<RampaModel> mediaTermopar(List<TemperaturaModel> temperaturas) {
		var termopar1 = new RampaModel();
		var termopar2 = new RampaModel();
		var termopar3 = new RampaModel();

		var inicioTermopar1 = 0;
		var inicioTermopar2 = 0;
		var inicioTermopar3 = 0;

		var janela1 = false;
		var janela2 = false;
		var janela3 = false;

		double temperaturaTermopar1Anterior = 0;
		double temperaturaTermopar2Anterior = 0;
		double temperaturaTermopar3Anterior = 0;

		var estabilizado = false;

		for (int i = 0; i < temperaturas.size(); i++) {

			double temperaturaTermopar1Atual = 0;
			double temperaturaTermopar2Atual = 0;
			double temperaturaTermopar3Atual = 0;

			double diferencaTermopar1 = 0;
			double diferencaTermopar2 = 0;
			double diferencaTermopar3 = 0;

			if (i > 50) {
				for (int j = i; j > i - 50; j--) {
					temperaturaTermopar1Atual += temperaturas.get(j).getTermopar_1();
					temperaturaTermopar2Atual += temperaturas.get(j).getTermopar_1();
					temperaturaTermopar3Atual += temperaturas.get(j).getTermopar_1();
				}

				temperaturaTermopar1Atual /= 50;
				temperaturaTermopar2Atual /= 50;
				temperaturaTermopar3Atual /= 50;

				if (temperaturaTermopar1Anterior == 0) {
					temperaturaTermopar1Anterior = temperaturaTermopar1Atual;
					temperaturaTermopar2Anterior = temperaturaTermopar2Atual;
					temperaturaTermopar3Anterior = temperaturaTermopar3Atual;
				}

				diferencaTermopar1 = Math.abs(temperaturaTermopar1Atual - temperaturaTermopar1Anterior);
				diferencaTermopar2 = Math.abs(temperaturaTermopar2Atual - temperaturaTermopar2Anterior);
				diferencaTermopar3 = Math.abs(temperaturaTermopar3Atual - temperaturaTermopar3Anterior);
				
				if (estabilizado || temperaturaTermopar1Atual > temperaturaTermopar1Anterior) {
					estabilizado = true;

					if (diferencaTermopar1 < 1 && !janela1) {
						janela1 = true;
						termopar1.setInicio(temperaturas.get(i).getDt_leitura());
						termopar1.setTermopar("Termopar1");
						inicioTermopar1 = i;
					} else if (diferencaTermopar1 > 0.5 && janela1 && i > inicioTermopar1 + 50) {
						termopar1.setFim(temperaturas.get(i - 50).getDt_leitura());
						termopar1.setDuracao(termopar1.getInicio().until(termopar1.getFim(), ChronoUnit.MILLIS));
						termopar1.setMedia(getMedia(inicioTermopar1, i - 50, temperaturas, 1));
						termopar1.setTempMinima(
								getTemperaturaMinima(inicioTermopar1, i - 50, temperaturas, 1));
						termopar1.setTempMaxima(
								getTemperaturaMaxima(inicioTermopar1, i - 50, temperaturas, 1));
						termopar1.setTempAmbiente(getMedia(inicioTermopar1, i - 50, temperaturas, 4));
					}
				}
				
				if (estabilizado || temperaturaTermopar1Atual > temperaturaTermopar1Anterior) {
					estabilizado = true;

					if (diferencaTermopar2 < 1 && !janela2) {
						janela2 = true;
						termopar2.setInicio(temperaturas.get(i).getDt_leitura());
						termopar2.setTermopar("Termopar2");
						inicioTermopar2 = i;
					} else if (diferencaTermopar2 > 0.5 && janela2 && i > inicioTermopar2 + 50) {
						termopar2.setFim(temperaturas.get(i - 50).getDt_leitura());
						termopar2.setDuracao(termopar2.getInicio().until(termopar2.getFim(), ChronoUnit.MILLIS));
						termopar2.setMedia(getMedia(inicioTermopar2, i - 50, temperaturas, 2));
						termopar2.setTempMinima(
								getTemperaturaMinima(inicioTermopar2, i - 50, temperaturas, 2));
						termopar2.setTempMaxima(
								getTemperaturaMaxima(inicioTermopar2, i - 50, temperaturas, 2));
						termopar2.setTempAmbiente(getMedia(inicioTermopar2, i - 50, temperaturas, 4));
					}
				}
				
				if (estabilizado || temperaturaTermopar1Atual > temperaturaTermopar1Anterior) {
					estabilizado = true;

					if (diferencaTermopar3 < 1 && !janela3) {
						janela3 = true;
						termopar3.setInicio(temperaturas.get(i).getDt_leitura());
						termopar3.setTermopar("Termopar3");
						inicioTermopar3 = i;
					} else if (diferencaTermopar3 > 0.5 && janela3 && i > inicioTermopar3 + 50) {
						termopar3.setFim(temperaturas.get(i - 50).getDt_leitura());
						termopar3.setDuracao(termopar3.getInicio().until(termopar3.getFim(), ChronoUnit.MILLIS));
						termopar3.setMedia(getMedia(inicioTermopar3, i - 50, temperaturas, 3));
						termopar3.setTempMinima(
								getTemperaturaMinima(inicioTermopar3, i - 50, temperaturas, 3));
						termopar3.setTempMaxima(
								getTemperaturaMaxima(inicioTermopar3, i - 50, temperaturas, 3));
						termopar3.setTempAmbiente(getMedia(inicioTermopar3, i - 50, temperaturas, 4));
					}
				}
				
				temperaturaTermopar1Anterior = temperaturaTermopar1Atual;
				temperaturaTermopar2Anterior = temperaturaTermopar2Atual;
				temperaturaTermopar3Anterior = temperaturaTermopar3Atual;

			}
		}

		var termopares = new ArrayList<RampaModel>();

		termopares.add(termopar1);
		termopares.add(termopar2);
		termopares.add(termopar3);

		return termopares;

	}

	public List<RampasModel> rampaSubida(List<TemperaturaModel> temperaturas, int pontosMedia, double variacao) {

		var rampasTermopar1 = new RampasModel();
		var rampasTermopar2 = new RampasModel();
		var rampasTermopar3 = new RampasModel();

		var InicioRampaTerm1 = 0;
		var InicioRampaTerm2 = 0;
		var InicioRampaTerm3 = 0;

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

		for (int i = pontosMedia - 1; i < temperaturas.size(); i++) {
			double temperaturaTermopar1Atual = media(i, pontosMedia, termopar1);
			double temperaturaTermopar2Atual = media(i, pontosMedia, termopar2);
			double temperaturaTermopar3Atual = media(i, pontosMedia, termopar3);

			if (i == pontosMedia - 1) {
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

			if (Math.abs(diferencaTemperaturaTermopar1) < variacao) {
				if (!inicioRampaTerm1) {

					if (rampaSubindoTermopar1.getFim() == null) {
						rampaSubindoTermopar1.setFim(temperaturas.get(i - 2).getDt_leitura());
						rampaSubindoTermopar1.setDuracao(rampaSubindoTermopar1.getInicio()
								.until(rampaSubindoTermopar1.getFim(), ChronoUnit.MILLIS));
						rampaSubindoTermopar1
								.setTempMinima(getTemperaturaMinima(InicioRampaTerm1, i + 1, temperaturas, 1));
						rampaSubindoTermopar1
								.setTempMaxima(getTemperaturaMaxima(InicioRampaTerm1, i + 1, temperaturas, 1));
						rampaSubindoTermopar1.setTempAmbiente(getMediaAmb(InicioRampaTerm1, i + 1, temperaturas));
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
						rampaDescendoTermopar1.setInicio(temperaturas.get(i - 5).getDt_leitura());
						InicioRampaTerm1 = i - 5;
						rampaDescendoTermopar1.setRampaSubindo(false);
						rampaDescendoTermopar1.setTermopar("Termopar1");
					}

					inicioRampaTerm1 = false;
				} else if (!inicioRampaTerm1 && (i + 1) == temperaturas.size()) {
					rampaDescendoTermopar1.setFim(temperaturas.get(i).getDt_leitura());
					rampaDescendoTermopar1.setDuracao(rampaDescendoTermopar1.getInicio()
							.until(rampaDescendoTermopar1.getFim(), ChronoUnit.MILLIS));
					rampaDescendoTermopar1.setTempMinima(getTemperaturaMinima(InicioRampaTerm1, i, temperaturas, 1));
					rampaDescendoTermopar1.setTempMaxima(getTemperaturaMaxima(InicioRampaTerm1, i, temperaturas, 1));
					rampaDescendoTermopar1.setTempAmbiente(getMediaAmb(InicioRampaTerm1, i, temperaturas));
				}
			}

			if (Math.abs(diferencaTemperaturaTermopar2) < variacao) {
				if (!inicioRampaTerm2) {

					if (rampaSubindoTermopar2.getFim() == null) {
						rampaSubindoTermopar2.setFim(temperaturas.get(i - 2).getDt_leitura());
						rampaSubindoTermopar2.setDuracao(rampaSubindoTermopar2.getInicio()
								.until(rampaSubindoTermopar2.getFim(), ChronoUnit.MILLIS));
						rampaSubindoTermopar2
								.setTempMinima(getTemperaturaMinima(InicioRampaTerm2, i + 1, temperaturas, 2));
						rampaSubindoTermopar2
								.setTempMaxima(getTemperaturaMaxima(InicioRampaTerm2, i + 1, temperaturas, 2));
						rampaSubindoTermopar2.setTempAmbiente(getMediaAmb(InicioRampaTerm2, i + 1, temperaturas));
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
						rampaDescendoTermopar2.setInicio(temperaturas.get(i - 5).getDt_leitura());
						InicioRampaTerm2 = i - 5;
						rampaDescendoTermopar2.setRampaSubindo(false);
						rampaDescendoTermopar2.setTermopar("Termopar2");
					}

					inicioRampaTerm2 = false;
				} else if (!inicioRampaTerm2 && (i + 1) == temperaturas.size()) {
					rampaDescendoTermopar2.setFim(temperaturas.get(i).getDt_leitura());
					rampaDescendoTermopar2.setDuracao(rampaDescendoTermopar2.getInicio()
							.until(rampaDescendoTermopar2.getFim(), ChronoUnit.MILLIS));
					rampaDescendoTermopar2.setTempMinima(getTemperaturaMinima(InicioRampaTerm2, i, temperaturas, 2));
					rampaDescendoTermopar2.setTempMaxima(getTemperaturaMaxima(InicioRampaTerm2, i, temperaturas, 2));
					rampaDescendoTermopar2.setTempAmbiente(getMediaAmb(InicioRampaTerm2, i, temperaturas));
				}
			}

			if (Math.abs(diferencaTemperaturaTermopar3) < variacao) {
				if (!inicioRampaTerm3) {

					if (rampaSubindoTermopar3.getFim() == null) {
						rampaSubindoTermopar3.setFim(temperaturas.get(i - 2).getDt_leitura());
						rampaSubindoTermopar3.setDuracao(rampaSubindoTermopar3.getInicio()
								.until(rampaSubindoTermopar3.getFim(), ChronoUnit.MILLIS));
						rampaSubindoTermopar3
								.setTempMinima(getTemperaturaMinima(InicioRampaTerm3, i + 1, temperaturas, 3));
						rampaSubindoTermopar3
								.setTempMaxima(getTemperaturaMaxima(InicioRampaTerm3, i + 1, temperaturas, 3));
						rampaSubindoTermopar3.setTempAmbiente(getMediaAmb(InicioRampaTerm3, i + 1, temperaturas));
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

						rampaDescendoTermopar3.setInicio(temperaturas.get(i - 5).getDt_leitura());
						InicioRampaTerm3 = i - 5;
						rampaDescendoTermopar3.setRampaSubindo(false);
						rampaDescendoTermopar3.setTermopar("Termopar3");
					}

					inicioRampaTerm3 = false;
				} else if (!inicioRampaTerm3 && (i + 1) == temperaturas.size()) {
					rampaDescendoTermopar3.setFim(temperaturas.get(i).getDt_leitura());
					rampaDescendoTermopar3.setDuracao(rampaDescendoTermopar3.getInicio()
							.until(rampaDescendoTermopar3.getFim(), ChronoUnit.MILLIS));
					rampaDescendoTermopar3.setTempMinima(getTemperaturaMinima(InicioRampaTerm3, i, temperaturas, 3));
					rampaDescendoTermopar3.setTempMaxima(getTemperaturaMaxima(InicioRampaTerm3, i, temperaturas, 3));
					rampaDescendoTermopar3.setTempAmbiente(getMediaAmb(InicioRampaTerm3, i, temperaturas));
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

							janelas.add(janelasModel);
						}
					}

				}

			}
		}

		return janelas;
	}

	public List<TemperaturaModel> getTemperaturas() {
		return temperaturas;
	}

	public void setTemperatuas(List<TemperaturaModel> temperaturas) {
		this.temperaturas = temperaturas;
	}
}

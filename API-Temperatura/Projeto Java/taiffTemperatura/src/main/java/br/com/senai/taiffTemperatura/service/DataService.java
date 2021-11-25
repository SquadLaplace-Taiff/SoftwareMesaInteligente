package br.com.senai.taiffTemperatura.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senai.taiffTemperatura.dto.TemperaturaJanelaDto;
import br.com.senai.taiffTemperatura.model.EstatisticaModel;
import br.com.senai.taiffTemperatura.model.JanelasModel;
import br.com.senai.taiffTemperatura.model.RampaModel;
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
//			System.out.println("J: " + j);
		}
//		System.out.println("média: " + (acum / qtd));
		return acum / qtd;
	}

	public List<RampaModel> rampaSubida(List<TemperaturaModel> temperaturas, int pontosMedia, double variacao) {
		double medTemp1, medTemp2, medTemp3;
		double medTempAnterior1 = 0, medTempAnterior2 = 0, medTempAnterior3 = 0;
		double variacaoTemp1, variacaoTemp2, variacaoTemp3;
		List<RampaModel> rampas = new ArrayList<RampaModel>();
		boolean tempSubindo = false;
		boolean estabilizacaoTemp1 = false,  estabilizacaoTemp2 = false, estabilizacaoTemp3 = false;
		int inicioRampa = 0;
		
		double[] vetTemp1 = new double[temperaturas.size()];
		int x = 0;
		
//		for (TemperaturaModel temperatura : temperaturas) {
//			vetTemp1[x] = temperatura.getTermopar_1();
//			x++;
//		}
//		
//		
//		for (int i = 9; i < temperaturas.size(); i++) {
//			double valor1 = media(i, 10, vetTemp1);
//			double valor2 = media(i + 1, 10, vetTemp1);
//			
//			double diferenca = valor1 - valor2;
//			
//			System.out.println(i);
//			
//			if (Math.abs(diferenca) < 1) {
//				System.out.println("estabilizacao: " + temperaturas.get(i).getDt_leitura() + ", valor1: " + valor1 + " valor 2: " + valor2 + " Diferença: " + diferenca);
//			} else {
//				System.out.println("desestabilizado: " + temperaturas.get(i).getDt_leitura() + ", valor1: " + valor1 + " valor 2: " + valor2 + " Diferença: " + diferenca);
//			}
//		}
		

		for (int i = 0; i < temperaturas.size(); i++) {
			if (tempSubindo || (i >= 1 && temperaturas.get(i).getTermopar_1() >= temperaturas.get(i - 1).getTermopar_1() + 2)) {
				
				if (!tempSubindo) { inicioRampa = i; }
				
				tempSubindo = true;

				if (i >= inicioRampa + pontosMedia ) {
					double auxMedTemp1 = 0, auxMedTemp2 = 0, auxMedTemp3 = 0;
					for (int k = 0; k < pontosMedia; k++) {
						auxMedTemp1 += temperaturas.get(i - pontosMedia).getTermopar_1();
						auxMedTemp2 += temperaturas.get(i - pontosMedia).getTermopar_2();
						auxMedTemp3 += temperaturas.get(i - pontosMedia).getTermopar_3();
						
						System.out.println(temperaturas.get(i - pontosMedia).getTermopar_1());
					}
					
					medTemp1 = auxMedTemp1 / pontosMedia;
					medTemp2 = auxMedTemp2 / pontosMedia;
					medTemp3 = auxMedTemp3 / pontosMedia;

					if (i == pontosMedia) {
						medTempAnterior1 = medTemp1;
						medTempAnterior2 = medTemp2;
						medTempAnterior3 = medTemp3;
					}
					
					variacaoTemp1 = medTemp1 - medTempAnterior1;
					variacaoTemp2 = medTemp2 - medTempAnterior2;
					variacaoTemp3 = medTemp3 - medTempAnterior3;

//					System.out.println("VariaçãoTemp1: " + variacaoTemp1 + "\nvariacao: " + variacao + "\ni: " + i + "\npontosMedia: " + pontosMedia);
					if (variacaoTemp1 < variacao && !estabilizacaoTemp1 && i > pontosMedia) {
						estabilizacaoTemp1 = true;
						RampaModel rampaTemp1 = new RampaModel(temperaturas.get(inicioRampa).getDt_leitura(),
								temperaturas.get(i - pontosMedia).getDt_leitura(), "Termopar_1");
						rampas.add(rampaTemp1);
//						System.out.println("variacao temp1: " + variacaoTemp1 + " variacao: " + variacao);
//						System.out.println("media temp 1: " + medTemp1 + " media temp anterior: " + medTempAnterior1);
					}
					if (variacaoTemp2 < variacao && !estabilizacaoTemp2 && i > pontosMedia) {
						estabilizacaoTemp2 = true;
						RampaModel rampaTemp2 = new RampaModel(temperaturas.get(inicioRampa).getDt_leitura(),
								temperaturas.get(i - pontosMedia).getDt_leitura(), "Termopar_2");
						rampas.add(rampaTemp2);
					}
					if (variacaoTemp3 < variacao && !estabilizacaoTemp3 && i > pontosMedia) {
						estabilizacaoTemp3 = true;
						RampaModel rampaTemp3 = new RampaModel(temperaturas.get(inicioRampa).getDt_leitura(),
								temperaturas.get(i - pontosMedia).getDt_leitura(), "Termopar_3");
						rampas.add(rampaTemp3);
					}
					
					medTempAnterior1 = medTemp1;
					medTempAnterior2 = medTemp2;
					medTempAnterior3 = medTemp3;

				}
			}
		}
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

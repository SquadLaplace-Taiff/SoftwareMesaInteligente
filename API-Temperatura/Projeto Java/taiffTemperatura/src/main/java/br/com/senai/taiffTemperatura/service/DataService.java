package br.com.senai.taiffTemperatura.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senai.taiffTemperatura.model.JanelasModel;
import br.com.senai.taiffTemperatura.model.TemperaturaModel;
import br.com.senai.taiffTemperatura.repository.TemperaturaRepository;

public class DataService {
	
	private List<TemperaturaModel> temperaturas;
	
	public DataService () {
	}
	
	public void getData(List<TemperaturaModel> temperaturas) {
		this.generateWindow(temperaturas);
	}
	
	public List<JanelasModel> generateWindow(List<TemperaturaModel> listaTemperaturas) {
		
		double temp_new;
		double temp_old = 0;
		double variation_new = 0;
		
		boolean inWindow = false;
		
	    int RAMP_UP_THRESHOLD = 5;
	    int RAMP_DOWN_THRESHOLD = -3;
	    int RAMP_UP_PAD = 30;
	    int RAMP_DOWN_PAD = -10;
	    
	    List<JanelasModel> janelas = new ArrayList<JanelasModel>();
	    
	    int auxValorInicial = 0;
	    int auxValorFinal = 0;
		
		for (int i = 0; i < listaTemperaturas.size(); i ++) {
			JanelasModel janelasModel = new JanelasModel();
			if (i >= 2) {				
				temp_new = (listaTemperaturas.get(i).getTermopar_1() + 
						    listaTemperaturas.get(i - 1).getTermopar_1() + 
						    listaTemperaturas.get(i - 2).getTermopar_1()) / 3;
				
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

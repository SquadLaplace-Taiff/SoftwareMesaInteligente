package br.com.senai.taiffTemperatura.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RampasModel {
	private RampaModel rampaSubindo;
	private RampaModel rampaDescendo;
}

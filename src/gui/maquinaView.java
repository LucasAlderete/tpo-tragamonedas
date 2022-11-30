package gui;

import java.util.List;

import negocio.Fruta;
import negocio.Premio;

public class maquinaView {
	private float credito, verCredito, precioJugada;
	private List<String> premioActual;

	public maquinaView() {
	}

	public maquinaView(float credito, float verCredito, List<String> premioActual, float precioJugada) {
		this.credito = credito;
		this.precioJugada = precioJugada;
		this.premioActual = premioActual;
		this.verCredito = verCredito;
	}

	public float getCredito() {
		return credito;
	}

	public float getVerCredito() {
		return verCredito;
	}

	public List<String> getPremioActual() {
		return premioActual;
	}

	public float getPrecioJugada() {
		return precioJugada;
	}

}

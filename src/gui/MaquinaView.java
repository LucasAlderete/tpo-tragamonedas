package gui;

import java.util.List;

import negocio.Casilla;
import negocio.Fruta;
import negocio.Premio;

public class MaquinaView {
	private float credito;
	private String estado; // gano-perdio
	private float montoPremio;
	private List<Casilla> combinacion;
	private String msj;
	private float recaudacion;

	public MaquinaView() {
	}

	public MaquinaView(float credito, String estado, float montoPremio, List<Casilla> combinacion, String msj, float recaudacion) {
		super();
		this.credito = credito;
		this.estado = estado;
		this.montoPremio = montoPremio;
		this.combinacion = combinacion;
		this.msj = msj;
	}

	public MaquinaView(float credito) {
		this.credito = credito;
	}

	public float getCredito() {
		return credito;
	}

	public String getEstado() {
		return estado;
	}

	public float getMontoPremio() {
		return montoPremio;
	}

	public List<Casilla> getCombinacion() {
		return combinacion;
	}

	public String getMsj() {
		return msj;
	}
	
	public float getRecaudacion() {
		return recaudacion;
	}
}

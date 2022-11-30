package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gui.MaquinaView;

public class Maquina {
	private List<Casilla> casillas;
	private float recaudacion;
	private List<Premio> premios;
	private float minimo;
	private float precioJugada;
	private float credito;
	private String identificador;
	private int numero;
	private boolean hasPremio;
	private float montoPremio;
	private String msj;

	private final int MAX_NUMERO_TICKET = 9999;
	private final float CREDITO_DEFAULT = 0;

	public Maquina() {
		premios = new ArrayList<Premio>();
	}

	public Maquina(String identificador, float precioJugada) {
		premios = new ArrayList<Premio>();
		this.identificador = identificador;
		this.precioJugada = precioJugada;
		numero = 0;
	}

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public float getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}

	public List<Premio> getPremios() {
		return premios;
	}

	public void setPremios(List<Premio> premios) {
		this.premios = premios;
	}

	public float getMinimo() {
		return minimo;
	}

	public void setMinimo(float minimo) {
		this.minimo = minimo;
	}

	public float getPrecioJugada() {
		return precioJugada;
	}

	public void setPrecioJugada(float precioJugada) {
		this.precioJugada = precioJugada;
	}

	public float getCredito() {
		return credito;
	}

	public void setCredito(float credito) {
		this.credito = credito;
	}

	public void jugar() {
		hasPremio = false;

		// se seleccionan frutas al azar por cada casilla
		for (Casilla casilla : casillas) {
			casilla.setFrutaAleatoria();
		}

		// se comprueba si la combinacion tiene premio
		for (Premio premio : premios) {

			// tiene premio
			if (premio.soyElPremio(casillas)) {
				montoPremio = premio.getPremio();
				sumarCredito(montoPremio);
				restarRecaudacion(montoPremio);
				hasPremio = true;

				this.msj = "Ganaste: $" + premio.getPremio() + " (" + premio.getNombre() + ")"; 
			}
		}

		// si no se gano nada, entonces sumo la jugada a la recaudacion de la maquina
		if (!hasPremio) {
			incrementarRecaudacion(precioJugada);
			// se resta el precio de la jugada
			restarCredito(precioJugada);

		}

	}

	public void addPremio(Premio premio) {
		this.premios.add(premio);
	}

	public void addPremios(List<Premio> premios) {
		for (Premio premio : premios) {
			this.premios.add(premio);
		}
	}

	public void limpiarPremios() {
		this.premios.clear();
	}

	public void incrementarRecaudacion(float monto) {
		this.recaudacion += monto;
	}

	public void restarRecaudacion(float monto) {
		recaudacion -= monto;
	}

	public Comprobante generarComprobante() {

		Comprobante comprobante = new Comprobante();
		comprobante.setNumeroComprobante(generarNumeroComprobante());

		// poner credito en 0
		this.setCredito(CREDITO_DEFAULT);

		return comprobante;
	}

	public void sumarCredito(float monto) {
		credito += monto;
	}

	public void restarCredito(float monto) {
		credito -= monto;
	}

	public boolean hasRecaudacionMinima() {
		return this.recaudacion >= this.getPremioMaximo();
	}

	public float getPremioMaximo() {
		Float max = null;

		for (Premio premio : premios) {
			if (max == null || premio.getPremio() > max) {
				max = premio.getPremio();
			}
		}

		return max;
	}

	public boolean hasCreditoParaJugar() {
		return credito >= precioJugada;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getIdentificador() {
		return identificador;
	}

	private String generarNumeroComprobante() {
		this.numero++;
		String numero = this.identificador + "-" + this.numero;
		return numero;
	}

	public MaquinaView toView() {
		String estado = this.hasPremio ? "Ganaste!" : "No ganaste";
		return new MaquinaView(this.credito, estado, this.montoPremio, this.casillas, this.msj, this.recaudacion);
	}

}

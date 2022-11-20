package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maquina {
	private List<Casilla> casillas;
	private float recaudacion;
	private List<Premio> premios;
	private float minimo;
	private float precioJugada;
	private float credito;
	
	private final int MAX_NUMERO_TICKET = 9999;
	private final float CREDITO_DEFAULT = 0;

	public Maquina() {
		premios = new ArrayList<Premio>();
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
		boolean hasPremio = false;

		// se seleccionan frutas al azar por cada casilla
		for (Casilla casilla : casillas) {
			casilla.setFrutaAleatoria();

			// TODO: eliminar este sout
			System.out.println(casilla.getNumeroCasilla() + " - " + casilla.getFruta().getNombre());

		}

		// se comprueba si la combinacion tiene premio
		for (Premio premio : premios) {

			// tiene premio
			if (premio.soyElPremio(casillas)) {
				float montoPremio = premio.getPremio();
				sumarCredito(montoPremio);
				restarRecaudacion(montoPremio);
				hasPremio = true;

				// TODO: este sout no va aca moverlo al controlador o lugar correspondiente
				System.out.println("Ganaste: $" + premio.getPremio() + " (" + premio.getNombre() + ")");
			}
		}

		// si no se gano nada, entonces sumo la jugada a la recaudacion de la maquina
		if (!hasPremio) {
			incrementarRecaudacion(precioJugada);
			// se resta el precio de la jugada
			restarCredito(precioJugada);

			// TODO: eliminar este sout
			System.out.println("Perdiste! la recaudacion es de: $" + recaudacion);
		}

		// TODO: eliminar souts
		System.out.println("La recaudacion de la maquina quedo en: $" + this.recaudacion);
		System.out.println("El credito disponible es: $" + this.credito);

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

	public void ingresarCredito(int numeroTicket) {
		// existe ticket?
		// no fue utilizado?
		// agregar credito
	}

	public Ticket generarComprobante() {
		// poner credito en comprobante o ticket
		Random random = new Random();
		int nroTicketRandom = random.nextInt(MAX_NUMERO_TICKET);

		Ticket ticket = new Ticket();
		ticket.setMonto(this.credito);
		ticket.setNumeroTicket(nroTicketRandom);
		ticket.setUtilizado(false);

		// poner credito en 0
		this.setCredito(CREDITO_DEFAULT);

		return ticket;
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
}

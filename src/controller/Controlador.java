package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gui.MaquinaView;
import negocio.Caja;
import negocio.Casilla;
import negocio.Comprobante;
import negocio.Fruta;
import negocio.Maquina;
import negocio.Premio;
import negocio.Ticket;

public class Controlador {
	private static Controlador instancia;
	private List<Fruta> frutas;
	private List<Premio> premios;
	private List<Casilla> casillas;
	private List<Comprobante> comprobantes;
	private boolean sePuedeJugar = false;

	final float PRECIO_JUGADA = 10f;

	Maquina maquina;

	private Caja caja;

	public Controlador() {
		this.caja = new Caja();
		this.maquina = new Maquina();
		this.comprobantes = new ArrayList<>();
		cargarDatos();
	}

	public static Controlador getInstance() {
		if (instancia == null) {
			instancia = new Controlador();
		}

		return instancia;
	}

	private void cargarDatos() {
		cargarFrutas();
		cargarCasillas();
		cargarPremios();

		inicilizarMaquina();
	}

	private void cargarPremios() {
		// Definicion de premios

		Fruta pera = new Fruta("pera");
		Fruta uva = new Fruta("Uva");
		Fruta frutilla = new Fruta("Frutilla");

		// premio 1: frutilla - frutilla - frutilla
		Casilla primerCasillaPremio1 = new Casilla(1);
		primerCasillaPremio1.setFruta(frutilla);
		Casilla segundaCasillaPremio1 = new Casilla(2);
		segundaCasillaPremio1.setFruta(frutilla);
		Casilla tercerCasillaPremio1 = new Casilla(3);
		tercerCasillaPremio1.setFruta(frutilla);
		List<Casilla> combinacionPremio1 = List.of(primerCasillaPremio1, segundaCasillaPremio1, tercerCasillaPremio1);

		Premio premio1 = new Premio();
		premio1.setCombinacion(combinacionPremio1);
		premio1.setNombre("triple frutilla");
		premio1.setPremio(500f);

		// premio 2: manzana - manzana - uva
		Casilla primerCasillaPremio2 = new Casilla(1);
		primerCasillaPremio2.setFruta(pera);
		Casilla segundaCasillaPremio2 = new Casilla(2);
		segundaCasillaPremio2.setFruta(pera);
		Casilla tercerCasillaPremio2 = new Casilla(3);
		tercerCasillaPremio2.setFruta(uva);
		List<Casilla> combinacionPremio2 = List.of(primerCasillaPremio2, segundaCasillaPremio2, tercerCasillaPremio2);

		Premio premio2 = new Premio();
		premio2.setCombinacion(combinacionPremio2);
		premio2.setNombre("pera pera uva");
		premio2.setPremio(300f);

		this.premios = List.of(premio1, premio2);
	}

	private void cargarFrutas() {
		// Definicion el set de frutas que puede contener cada casilla
		Fruta uva = new Fruta("Uva");
		Fruta frutilla = new Fruta("Frutilla");
		Fruta banana = new Fruta("Banana");
		Fruta guinda = new Fruta("Guinda");
		Fruta sandia = new Fruta("Sandia");
		Fruta pera = new Fruta("Pera");
		frutas = List.of(pera, uva, frutilla, banana, guinda, sandia);
		// frutas = List.of(frutilla);
	}

	private void cargarCasillas() {

		Casilla casilla1 = new Casilla(1, frutas);
		Casilla casilla2 = new Casilla(2, frutas);
		Casilla casilla3 = new Casilla(3, frutas);
		casillas = List.of(casilla1, casilla2, casilla3);
	}

	public void inicilizarMaquina() {
		maquina.setCasillas(casillas);
		maquina.addPremios(premios);
		maquina.setIdentificador("MAQ");
		maquina.setPrecioJugada(PRECIO_JUGADA);
		maquina.setRecaudacion(maquina.getPremioMaximo());
	}

	public void jugar() {
		sePuedeJugar = false;
		if (!maquina.hasRecaudacionMinima()) {
			maquina.setMsj(
					"Se ha alcanzado la recaudación mínima y existe posibilidad de no poder pagar los próximos premios");
		}

		if (maquina.hasCreditoParaJugar()) {
			sePuedeJugar = true;
			maquina.jugar();
		} else {
			maquina.setMsj("La maquina no tiene credito para realizar la jugada");
		}
	}

	public boolean retirarDinero(String nroComprobante) {
		this.existeComprobante(nroComprobante).ifPresent(comprobante -> {
			caja.retirarDinero(comprobante);
			usarComprobante(comprobante);
		});

		return this.existeComprobante(nroComprobante).isPresent();
	}

	public boolean ingresarCredito(int numeroTicket) {

		if (caja.existeTicket(numeroTicket).isPresent()) {
			Ticket ticket = caja.existeTicket(numeroTicket).get();
			usarTicket(numeroTicket);
			maquina.setCredito(maquina.getCredito() + ticket.getMonto());

			return true;
		}

		return false;
	}

	public float getCreditoMaquina() {
		return maquina.getCredito();
	}

	private Optional<Comprobante> existeComprobante(String nroComprobante) {
		return comprobantes.stream()
				.filter(comprobante -> comprobante.soyElComprobante(nroComprobante) && comprobante.sePuedeUtilizar())
				.findFirst();
	}

	public Ticket generarTicket(float monto) {
		return caja.generarTicket(monto);
	}

	public boolean usarTicket(int nroTicket) {
		return caja.usarTicket(nroTicket);
	}

	private void usarComprobante(Comprobante comprobante) {
		for (int i = 0; i < comprobantes.size(); i++) {
			if (comprobantes.get(i).getNumeroComprobante().equals(comprobante.getNumeroComprobante())) {
				comprobantes.get(i).setUtilizado(true);
			}
		}
	}

	public Comprobante generarComprobante() {
		Comprobante comprobante = maquina.generarComprobante();
		comprobantes.add(comprobante);
		return comprobante;
	}

	public MaquinaView getMaquinaView() {
		return this.maquina.toView();
	}

	public boolean sePuedeJugar() {
		return this.sePuedeJugar;
	}

}

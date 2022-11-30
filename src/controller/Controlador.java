package controller;

import java.util.List;
import java.util.Optional;

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
	private List<Ticket> tickets;

	final float PRECIO_JUGADA_1 = 10f;
	final float PRECIO_JUGADA_2 = 50f;
	
	//todo:sacar esta maquina
	Maquina maquina1;
	
	private Caja caja;
	
	public Controlador() {
		this.caja = new Caja();
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
		//Definicion de premios
		
		//no repetir instancias /////
		Fruta manzana = new Fruta("Manzana");
		Fruta uva = new Fruta("Uva");
		Fruta frutilla = new Fruta("Frutilla");
		//////////
		
		//premio 1: frutilla - frutilla - frutilla
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
		

		//premio 2: manzana - manzana - uva
		Casilla primerCasillaPremio2 = new Casilla(1);
		primerCasillaPremio2.setFruta(manzana);
		Casilla segundaCasillaPremio2 = new Casilla(2);
		segundaCasillaPremio2.setFruta(manzana);
		Casilla tercerCasillaPremio2 = new Casilla(3);
		tercerCasillaPremio2.setFruta(uva);
		List<Casilla> combinacionPremio2 = List.of(primerCasillaPremio2, segundaCasillaPremio2, tercerCasillaPremio2);
				
		Premio premio2 = new Premio();
		premio2.setCombinacion(combinacionPremio2);
		premio2.setNombre("doble manzana + uva");
		premio2.setPremio(300f);
		
		this.premios = List.of(premio1, premio2);  
	}
	
	private void cargarFrutas() {
		//Definicion el set de frutas que puede contener cada casilla
		Fruta manzana = new Fruta("Manzana");
		Fruta uva = new Fruta("Uva");
		Fruta frutilla = new Fruta("Frutilla");
		Fruta banana = new Fruta("Banana");
		Fruta guinda = new Fruta("Guinda");
		Fruta sandia = new Fruta("Sandia");
		List<Fruta> frutas = List.of(manzana, uva, frutilla, banana, guinda, sandia);

		//lista hardcoded para ganar //TODO: eliminar este set, es solo para pruebas
		this.frutas = List.of(frutilla);
		////
	}
	
	private void cargarCasillas() {

		Casilla casilla1 = new Casilla(1, frutas);
		Casilla casilla2 = new Casilla(2, frutas);
		Casilla casilla3 = new Casilla(3, frutas);
		casillas = List.of(casilla1, casilla2, casilla3);
	}
	
	public void inicilizarMaquina() {
		maquina1 = new Maquina();
		maquina1.setCasillas(casillas);
		maquina1.addPremios(premios);
		maquina1.setIdentificador("MAQ1");
		
		
		
		maquina1.setPrecioJugada(PRECIO_JUGADA_1);
		maquina1.setRecaudacion(maquina1.getPremioMaximo());
		
		//TODO: eliminar este hardcodeo de credito y hacerlo con el ticket como indicar el enunciado
		maquina1.setCredito(200f);
		
		
		/*
		Ticket ticket1 = new Ticket();
		ticket1.setMonto(100.5f);
		ticket1.setNumeroTicket(1);
		ticket1.setUtilizado(false);
		
		Ticket ticket2 = new Ticket();
		ticket2.setMonto(100.5f);
		ticket2.setNumeroTicket(7);
		ticket2.setUtilizado(false);

		maquina1.ingresarCredito(ticket1.getNumeroTicket());
		maquina1.ingresarCredito(ticket2.getNumeroTicket());
		*/
	}
	
	public void jugar() {

		if (!maquina1.hasRecaudacionMinima()) {
			System.out.print("Se ha alcanzado la recaudación mínima y existe posibilidad de no poder pagar los próximos premios");
		}
		
		if (maquina1.hasCreditoParaJugar()) {
			maquina1.jugar();
		} else {
			System.out.print("La maquina no tiene credito para realizar la jugada");
		}
	}

	public void retirarDinero(String nroComprobante) {
		this.existeComprobante(nroComprobante).ifPresentOrElse(comprobante -> {
			this.caja.retirarDinero(comprobante);
		}, () -> {
			System.out.print("No existe un comprobante valido con ese numero.");
		});
	}

	
	private Optional<Ticket> existeTicket(int nroTicket) {
		return tickets.stream().filter(ticket -> ticket.soyElTicket(nroTicket) && ticket.sePuedeUtilizar()).findFirst();
	}
	
	private Optional<Comprobante> existeComprobante(String nroComprobante) {
		return comprobantes.stream().filter(comprobante -> comprobante.soyElComprobante(nroComprobante) && comprobante.sePuedeUtilizar()).findFirst();
	}
	
}

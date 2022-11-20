package negocio;

import java.util.List;
import java.util.Random;

public class Casilla {
	private List<Fruta> frutas;
	private Fruta fruta;
	private int numeroCasilla;

	public Casilla() {

	}

	public Casilla(int numeroCasilla) {
		this.numeroCasilla = numeroCasilla;
	}
	
	public Casilla(int numeroCasilla, List<Fruta> frutas) {
		this.numeroCasilla = numeroCasilla;
		this.frutas = frutas;
	}

	public Fruta getFruta() {
		return fruta;
	}

	public void setFruta(Fruta fruta) {
		this.fruta = fruta;
	}
	
	public int getNumeroCasilla() {
		return numeroCasilla;
	}

	public void setNumeroCasilla(int numeroCasilla) {
		this.numeroCasilla = numeroCasilla;
	}

	public void setFrutas(List<Fruta> frutas) {
		this.frutas = frutas;
	}

	public void setFrutaAleatoria() {
		Random random = new Random();
		int position = random.nextInt(this.frutas.size());
		setFruta(frutas.get(position));
	}
}

package test;

import controller.Controlador;

public class Main {

	public static void main(String[] args) {

		Controlador.getInstance().inicilizarMaquina();
		
		Controlador.getInstance().jugar();
	}

}

package negocio;

import java.util.ArrayList;
import java.util.List;

public class Caja {
	private List<Ticket> tickets; 
	
	public Caja() {
		tickets = new ArrayList();
	}
	
	public void generarTicket(float monto) {
		Ticket ticket = new Ticket(monto);
		ticket.setNumeroTicket(getSiguienteNroTicket());
		tickets.add(ticket);
	}
	
	public Comprobante retirarDinero(Comprobante comprobante) {
		comprobante.setUtilizado(true);
		return comprobante;
	}
	
	private int getSiguienteNroTicket() {
		if (tickets.size() == 0) {
			return 0;
		}
		
		return tickets.get(tickets.size() - 1).getNumeroTicket() + 1;
	}
	
}

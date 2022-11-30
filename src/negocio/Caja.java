package negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Caja {
	private List<Ticket> tickets; 
	
	public Caja() {
		tickets = new ArrayList();
	}
	
	public Ticket generarTicket(float monto) {
		Ticket ticket = new Ticket(monto);
		ticket.setNumeroTicket(getSiguienteNroTicket());
		tickets.add(ticket);
		return ticket;
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

	public Optional<Ticket> existeTicket(int nroTicket) {
		return tickets.stream().filter(ticket -> ticket.soyElTicket(nroTicket) && ticket.sePuedeUtilizar()).findFirst();
	}
	
	public boolean usarTicket(int nroTicket) {
			for (int i = 0; i < tickets.size(); i++) {
				if (tickets.get(i).getNumeroTicket() == nroTicket) {
					tickets.get(i).setUtilizado(true);

					return true;
				}
			}

		return false;
	}
	
}

package negocio;

public class Ticket {
	
	private int numeroTicket;
	private float monto;
	private boolean utilizado;

	public Ticket() {
		
	}
	
	public Ticket(float monto) {
		this.monto = monto;
		this.utilizado = false;
	}
	

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getNumeroTicket() {
		return numeroTicket;
	}

	public void setNumeroTicket(int numeroTicket) {
		this.numeroTicket = numeroTicket;
	}

	public boolean isUtilizado() {
		return utilizado;
	}

	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}

	public boolean soyElTicket(int numeroTicket) {
		return this.numeroTicket == numeroTicket;
	}
	public Ticket soyElTicket2(Ticket ticket) {
		if (this.numeroTicket == ticket.numeroTicket) {
			return ticket;
		}
		return null;
	}
	
	public boolean sePuedeUtilizar() {
		return !utilizado;
	}
}

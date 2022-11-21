package negocio;

public class Comprobante {
	private String numeroComprobante;
	private float monto;
	private boolean utilizado;
	
	
	public boolean soyElComprobante(String numeroComprobante) {
		return this.numeroComprobante.equals(numeroComprobante);
	}

	public Comprobante getComprobante() {
		return this;
	}
	
	public boolean sePuedeUtilizar() {
		return !utilizado;
	}
	
	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}
	
}

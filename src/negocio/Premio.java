package negocio;

import java.util.List;

public class Premio {
	private String nombre;
	private List<Casilla> combinacion;
	private float premio;
	
	public Premio() {
		
	}

	public List<Casilla> getCombinacion() {
		return combinacion;
	}

	public void setCombinacion(List<Casilla> combinacion) {
		this.combinacion = combinacion;
	}

	public float getPremio() {
		return premio;
	}

	public void setPremio(float premio) {
		this.premio = premio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCantidadCasillas() {
		return this.combinacion.size();
	}
	
	public boolean soyElPremio(List<Casilla> casillas) {
		boolean result = false;
		
		for (int i = 0; i < getCantidadCasillas(); i++) {
			if (casillas.get(i).getNumeroCasilla() == combinacion.get(i).getNumeroCasilla() &&
					casillas.get(i).getFruta().getNombre().equals(combinacion.get(i).getFruta().getNombre())) {
				result = true;
			}else {
				return false;
			}
		}
		
		return result;
	}
}

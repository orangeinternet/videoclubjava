package videoclub;


public class Oficina {


	/*
	 * a continuacion vamos a declarar los atributos de la clase Oficina.
	 */
	private int telefono;
	private String direccion;
	private String responsable;

	// creamos los setters and getters
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// el constructor Oficina
	public Oficina() {

	}

	// un metodo toString que nos devuelve los traibutos de la clase
	
	public String toString() {
		return ("La Clase Oficina: " + responsable + direccion + telefono);

	}

}

package videoclub;

public class Distribuidor {
	/**
	 * Atributos
	 */
	private String nombre;
	private String empresa;
	private String telefono;
	//Alto, medio, o bajo que indica el nivel de relación
	private String nivelRelacion;
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the nivelRelacion
	 */
	public String getNivelRelacion() {
		return nivelRelacion;
	}
	/**
	 * @param nivelRelacion the nivelRelacion to set
	 */
	public void setNivelRelacion(String nivelRelacion) {
		this.nivelRelacion = nivelRelacion;
	}

}

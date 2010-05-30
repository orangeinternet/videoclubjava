package videoclub;
import java.util.ArrayList;

	/**
	 * @author Jairo Figueroa Jimenez
	 * @version 1.0
	 * Fecha: 25/05/2010 
	 */

public class Distribuidor {
	/**
	 * Nombre del districuidor
	 */
	private String nombre;
	private int peliculas;

	/**
	 * Metodo toString sobre-escrito de la clase object
	 * que devuelve una cadena
	 * 
	 * return @String
	 */
	public String toString(){
		return ("La Clase Distribuidor: " + nombre + peliculas);
	}

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
	 * @return the peliculas
	 */
	public int getPeliculas() {
		return peliculas;
	}

	/**
	 * @param peliculas the peliculas to set
	 */
	public void setPeliculas(int peliculas) {
		this.peliculas = peliculas;
	}
}

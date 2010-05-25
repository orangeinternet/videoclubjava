package videoclub;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

	/**
	 * @author Jairo Figueroa Jimenez
	 * @version 1.0
	 * Fecha: 25/05/2010 
	 */

public class Alquiler {
	
	// Atributos de la clase
	
	private Socio socio;
	private Pelicula pelicula;
	private Date fechaAlquiler;
	
	// Constructor
	
	Alquiler(){}

	// Getters y Setters de la clase Alquiler
	
	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Date getFechaAlquiler() {
		return fechaAlquiler;
	}

	public void setFechaAlquiler(Date fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}
	
	/**
	 * Metodo toString sobre-escrito de la clase object
	 * que devuelve una cadena
	 */
	
	public String toString(){
		return null;
	}
	
}

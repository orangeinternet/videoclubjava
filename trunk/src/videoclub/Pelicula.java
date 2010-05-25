package videoclub;

import java.lang.*;
import java.util.*;
/*
 * @version VIDEOCLUB 1.0
 * @author Miguel Angel Llamas
 * idpeli conendrá un valor único para cada objeo película, independientemente
 * si hay más de una copia del mismo título.
 * numalguiler contendrá el número de alquiler
 * título el nombre de la película
 * género, en principio tomará los valores de un objeto género que contendrá
 * cargados los valores.
 * altabd será la fecha con la que damos de alta un objeto pelicula en nuestra
 * base de datos.
 * ultimoalq contendrá la fecha del último alquiler del tíulo alquilado.
 *
 */
public class Pelicula {

	private int idpeli = 0;
	private int numalquiler = 0;
	private String titulo = "";
	private String genero = "";
	private Date altabd;
	private Date ultimoalq;
	

	
	public int getIdpeli() {
		return idpeli;
	}

	public void setIdpeli(int idpeli) {
		this.idpeli = idpeli;
	}

	public int getNumalquiler() {
		return numalquiler;
	}

	public void setNumalquiler(int numalquiler) {
		this.numalquiler = numalquiler;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getAltabd() {
		return altabd;
	}

	public void setAltabd(Date altabd) {
		this.altabd = altabd;
	}

	public Date getUltimoalq() {
		return ultimoalq;
	}

	public void setUltimoalq(Date ultimoalq) {
		this.ultimoalq = ultimoalq;
	}

}

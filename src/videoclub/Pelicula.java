package videoclub;

import java.lang.*;
import java.util.*;

/**
 * @version VIDEOCLUB 1.0
 * @author Miguel Angel Llamas fecha 25/05/2010
 */
/*
 * idpeli conendrá un valor único para cada objeo película, independientementesi
 * hay más de una copia del mismo título. numalguiler contendrá el número de
 * alquiler título el nombre de la película género, en principio tomará los
 * valores de un arraylist género que contendrá cargados los valores. altabd será
 * la fecha con la que damos de alta un objeto pelicula en nuestra base de
 * datos. ultimoalq contendrá la fecha del último alquiler del tíulo alquilado.
 */

public class Pelicula {

	private int idpeli = 0;
	private int numalquiler = 0;
	private String titulo = "";
	private String genero = "";
	private Date altabd;
	private Date ultimoalq;

	/**
	 * 
	 *  devolvera el id del objeto película valor int**/
	
	public int getIdpeli() {
		return idpeli;
	}

	/** 
	 * asignará el Id de la película  int**/
	
	public void setIdpeli(int idpeli) {
		this.idpeli = idpeli;
	}

	/**
	 *  devolverá el número de alquiler de la película alquilada **/
	
	public int getNumalquiler() {
		return numalquiler;
	}

	/**
	 *  asignará el valor int del alquiler**/
	
	public void setNumalquiler(int numalquiler) {
		this.numalquiler = numalquiler;
	}

	/**
	 *  devolverá el título de la película**/
	
	public String getTitulo() {
		return titulo;
	}
	/**
	 *  asignará el título como valor String**/
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 *  devolverá valores ya cargados en un arraylist género**/
	
	public String getGenero() {
		return genero;
	}

	/**
	 * asignará el del objeto al arraylist género **/
	
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 *  Se devolverá la fecha con la que se ha dado de alta el titulo o la copia 
	 * del mismo en nuestra base de datos con un tipo Date**/
	
	public Date getAltabd() {
		return altabd;
	}

	/**
	 *  Asignará la fecha con la que se ha dado de alta el tíulo o
	 * la copia del mismo en nuestra base de datos**/
	 
	
	public void setAltabd(Date altabd) {
		this.altabd = altabd;
	}

	/** 
	 * 
	 * Devolverá la fecha del último alquiler del objeto película**/
	
	public Date getUltimoalq() {
		return ultimoalq;
	}

	/** 
	 * 
	 * Asignará la fecha del último alquiler del objeo película**/
	
	public void setUltimoalq(Date ultimoalq) {
		this.ultimoalq = ultimoalq;
	}

}

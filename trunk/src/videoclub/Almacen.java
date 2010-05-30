package videoclub;

import java.util.*;

public class Almacen {

	/*
	 * definimos atributo de ciudad que sera el nombre de mi almacen
	 */

	private String ciudad;
	/*
	 * creamos el arraylist peliculas que guarda cada pelicula nueva y el
	 * arraylist stock que nos dira el nº de peliculas que hay de cada nombre
	 */

	ArrayList<String> stock;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * inicializar variables, el stock con el nº de peliculas
	 * que contiene el arraylist de peliculas de la clase principal
	 */
	public Almacen(String ciudad,ArrayList p) {
		this.ciudad = ciudad;
		stock = new ArrayList<String>();
	}
	
	/**
	 * Método que aumenta el stock del almacen con otra pelicula
	 */
	public void aumentarStock (){
		String pelicula= Utiles.leerDatos();
		stock.add(pelicula);
	}
	/**
	 * metodo toString sobrecargado
	 */
	public String toString(){
		return ("Almacen: "+ ciudad);
	}
	
}

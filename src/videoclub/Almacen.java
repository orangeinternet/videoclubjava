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

	ArrayList stock;

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
		stock = new ArrayList(p.size());
	}
	/**
	 * el metodo aumentar: creamos un id cogiendo el valor  de idpeli,
	 * le preguntamos si ese valor es distinto de cero aumentara ese valor,
	 * y si no existe nos lo añade con valor 1
	 * @param p
	 */
	public void aumentarStock(Pelicula p) {
		String id;
		id = (String) stock.get(p.getIdpeli());
		int entero = Integer.parseInt(id);
		if (entero !=0) {
			entero++;
			stock.set(p.getIdpeli(), (Object) entero);
		} else {
			stock.add(p.getIdpeli(), (Object) 1);
		}

	}
	/**
	 * metodo disminuir: disminuye la cantidad de esa
	 * pelicula que tenemos 
	 * @param p
	 */
	public void disminuirStock(Pelicula p){
		String id;
		id = (String) stock.get(p.getIdpeli());
		int entero = Integer.parseInt(id);
		entero--;
		stock.set(p.getIdpeli(), (Object) entero);
		
	}
	/**
	 * metodo toString sobrecargado
	 */
	public String toString(){
		return ("Almacen: "+ ciudad);
	}
	
}

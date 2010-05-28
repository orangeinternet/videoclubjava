package videoclub;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import videoclub.Utiles;

public class Videoclub {

	
	private int gastos;
	private int ingresos;
	
	
	/**
	 * Atributos de la clase Videoclub
	 * Contiene la cadena que representa el idioma
	 * de la interfaz
	 */
	private String idioma;

	
	/**
	 * @return los gastos
	 */
	public int getGastos() {
		return gastos;
	}

	/**
	 * @param los gastos
	 */
	public void setGastos(int gastos) {
		this.gastos = gastos;
	}

	/**
	 * @return los ingresos
	 */
	public int getIngresos() {
		return ingresos;
	}

	/**
	 * @param los ingresos
	 */
	public void setIngresos(int ingresos) {
		this.ingresos = ingresos;
	}
	
	/**
	 * @return el idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param el idioma
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	/**
	 * @param args
	 * Método principal que inicia el programa Videoclub
	 * por consola y llama a las clases auxiliares
	 */
	public static void main(String[] args) {
		/*
		 * Este constructor inicializa gastos, ingresos 
		 * e idioma
		 */
		Videoclub videoclub= new Videoclub();
		/*
		 * Este constructor ya conecta con base de datos
		 * y establece la conexión
		 */
		BaseDeDatos datos= new BaseDeDatos();

		Bienvenida(videoclub);
		Menu(videoclub, datos);
	}
	
	/**
	 * Metodo que nos imprime por pantalla el segundo menu,
	 * en este caso el menu que nos permite alquilar, devolver o salir
	 * 
	 * @param utiles,videoclub
	 */
	
	public static void Menu(Videoclub videoclub, BaseDeDatos datos){
		boolean flag = true;
		String sOpcion;
		int iOpcion;
		
		/* Ojo: Las operaciones se pueden llevar a cabo todas las veces que 
		 		el usuario desee, hasta que no presione la opcion 3. Salir*/
		while (flag) {
			System.out.println("\n------------Menú TOPE------------");
			System.out.println("Seleccione una opcion: ");
			System.out.println("1. Alquilar");
			System.out.println("2. Devolver");
			System.out.println("3. Salir");
			System.out.println("-------------");
			System.out.println("4. Menú Admin");
			
			// Recojo la opcion seleccionada por el usuario
			sOpcion = Utiles.leerDatos();
			
			// Parseo el string a entero ya que el switch solo recoje enteros
			iOpcion = Utiles.StringAint(sOpcion);
			
			// Segun la opcion seleccionada se accedera a un metodo u otro
			switch (iOpcion) {
			case 1:
				alquilar(videoclub, datos);
				break;
			case 2:
				System.out.println("Introduzca id de película");
				sOpcion=Utiles.leerDatos();
				devolverPelicula(sOpcion);
				break;
			case 3:
				flag = false;
				break;
			case 4:
				menuAdmin(videoclub, datos);
			default:
				System.out.println("Introduzca una de las 3 opciones por favor");
			}
		}
		
	}
	
	private static void menuAdmin(Videoclub videoclub, BaseDeDatos datos) {
		boolean flag = true;
		String sOpcion;
		int iOpcion;		
		/* Ojo: Las operaciones se pueden llevar a cabo todas las veces que 
		 		el usuario desee, hasta que no presione la opcion 3. Salir*/
		while (flag) {
			System.out.println("\n------------Alquiler TOPE------------");
			System.out.println("Seleccione una opcion: ");
			System.out.println("1. Nuevo Socio");
			System.out.println("2. Nueva Película");
			System.out.println("3. --");
			System.out.println("4. --");
			System.out.println("5. Volver a Menu principal");
			
			// Recojo la opcion seleccionada por el usuario
			sOpcion = Utiles.leerDatos();
			
			// Parseo el string a entero ya que el switch solo recoje enteros
			iOpcion = Utiles.StringAint(sOpcion);
			
			// Segun la opcion seleccionada se accedera a un metodo u otro
			switch (iOpcion) {
			case 1:
				almacenarSocio(Utiles.rellenarSocio());
				break;
			case 2:
				almacenarPelicula(Utiles.rellenarPelicula());
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				flag = false;
				break;
			default:
				System.out.println("Introduzca una de las 3 opciones por favor");
			}
		}
	}

	
	/**
	 * Metodo que nos permite alquilar una pelicula
	 * @param videoclub
	 */
	
	public static void alquilar(Videoclub videoclub, BaseDeDatos datos){
		boolean flag = true;
		String sOpcion;
		int iOpcion;
		ArrayList<Pelicula> resultados = new ArrayList<Pelicula>();
		
		/* Ojo: Las operaciones se pueden llevar a cabo todas las veces que 
		 		el usuario desee, hasta que no presione la opcion 3. Salir*/
		while (flag) {
			System.out.println("\n------------Alquiler TOPE------------");
			System.out.println("Seleccione una opcion: ");
			System.out.println("1. TopSemanal");
			System.out.println("2. TopMensual");
			System.out.println("3. Novedades");
			System.out.println("4. Busqueda de Películas");
			System.out.println("5. Alquilar Pélicula");
			System.out.println("6. Volver a Menu principal");
			
			// Recojo la opcion seleccionada por el usuario
			sOpcion = Utiles.leerDatos();
			
			// Parseo el string a entero ya que el switch solo recoje enteros
			iOpcion = Utiles.StringAint(sOpcion);
			
			// Segun la opcion seleccionada se accedera a un metodo u otro
			switch (iOpcion) {
			case 1:
				datos.topSemanalMensualNovedades(iOpcion);
				break;
			case 2:
				datos.topSemanalMensualNovedades(iOpcion);
				break;
			case 3:
				datos.topSemanalMensualNovedades(iOpcion);
				break;
			case 4:
				System.out.println("Introduzca la búsqueda");
				sOpcion=Utiles.leerDatos();
				datos.busquedaPelicula(sOpcion);
				break;
			case 5:
				System.out.println("Introduzca id de película");
				sOpcion=Utiles.leerDatos();
				alquilarPelicula(sOpcion);
			case 6:
				flag = false;
				break;
			default:
				System.out.println("Introduzca una de las 3 opciones por favor");
			}
		}
	}
	

	/**
	 * Metodo que presenta la pantalla de los idiomas
	 * 
	 * @param videoclub
	 */
	
	public static void Bienvenida(Videoclub videoclub){
		
		System.out.println("------------Bienvenido al Videoclub TOPE------------");
		System.out.println("Seleccione el idioma: ");
		System.out.println("1. Español");
		System.out.println("2. Ingles");
		System.out.println("3. Frances");
		System.out.println("4. Italiano");
		System.out.println("5. Alemán");
		String aux= Utiles.leerDatos();
		videoclub.idioma= aux;
		
	}
}
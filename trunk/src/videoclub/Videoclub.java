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

	/**
	 * Atributos de la clase Videoclub
	 * Contiene los ArrayList repesentativas a las clases
	 * que irán a base de datos
	 */
	private ArrayList peliculas;
	private ArrayList socios;
	private ArrayList alquileres;
	private ArrayList distribuidores;
	private ArrayList almacenes;
	private ArrayList oficinas;
	
	Videoclub(){
		this.alquileres = new ArrayList();
	}
	
	/**
	 * Atributos de la clase Videoclub
	 * Contiene un ArrayList interno para el mantenimiento
	 * de los generos de las películas
	 */
	private ArrayList generos;

	/**
	 * Atributos de la clase Videoclub
	 * Contiene los datos de facturación
	 */
	private int gastos;
	private int ingresos;

	/**
	 * Atributos de la clase Videoclub
	 * Contiene la cadena que representa el idioma
	 * de la interfaz
	 */
	private String idioma;

	/**
	 * @return las películas
	 */
	public ArrayList getPeliculas() {
		return peliculas;
	}

	/**
	 * @param las películas
	 */
	public void setPeliculas(ArrayList peliculas) {
		this.peliculas = peliculas;
	}

	/**
	 * @return los socios
	 */
	public ArrayList getSocios() {
		return socios;
	}

	/**
	 * @param los socios
	 */
	public void setSocios(ArrayList socios) {
		this.socios = socios;
	}

	/**
	 * @return los alquileres
	 */
	public ArrayList getAlquileres() {
		return alquileres;
	}

	/**
	 * @param los alquileres
	 */
	public void setAlquileres(ArrayList alquileres) {
		this.alquileres = alquileres;
	}

	/**
	 * @return los distribuidores
	 */
	public ArrayList getDistribuidores() {
		return distribuidores;
	}

	/**
	 * @param los distribuidores
	 */
	public void setDistribuidores(ArrayList distribuidores) {
		this.distribuidores = distribuidores;
	}

	/**
	 * @return los almacenes
	 */
	public ArrayList getAlmacenes() {
		return almacenes;
	}

	/**
	 * @param los almacenes
	 */
	public void setAlmacenes(ArrayList almacenes) {
		this.almacenes = almacenes;
	}

	/**
	 * @return las oficinas
	 */
	public ArrayList getOficinas() {
		return oficinas;
	}

	/**
	 * @param las oficinas
	 */
	public void setOficinas(ArrayList oficinas) {
		this.oficinas = oficinas;
	}

	/**
	 * @return los generos
	 */
	public ArrayList getGeneros() {
		return generos;
	}

	/**
	 * @param los generos
	 */
	public void setGeneros(ArrayList generos) {
		this.generos = generos;
	}

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
		Videoclub videoclub= new Videoclub();
		
		Pelicula p1 = new Pelicula();
		Pelicula p2 = new Pelicula();
		Pelicula p3 = new Pelicula();
		Pelicula p4 = new Pelicula();
		Pelicula p5 = new Pelicula();
		
		p1.setNumalquiler(1);
		p1.setUltimoalq(new Date(25,05,2010));
		p2.setNumalquiler(10);
		p2.setUltimoalq(new Date(25,05,2010));
		p3.setNumalquiler(6);
		p3.setUltimoalq(new Date(25,05,2010));
		p4.setNumalquiler(4);
		p4.setUltimoalq(new Date(25,05,2010));
		p5.setNumalquiler(2);
		p5.setUltimoalq(new Date(25,05,2010));
		
		videoclub.getAlquileres().add(p1);
		videoclub.getAlquileres().add(p2);
		videoclub.getAlquileres().add(p3);
		videoclub.getAlquileres().add(p4);
		videoclub.getAlquileres().add(p5);
		
		Utiles utiles = new Utiles();
		Bienvenida(videoclub);
		Menu(utiles,videoclub);
	}
	
	/**
	 * Metodo que nos imprime por pantalla el segundo menu,
	 * en este caso el menu que nos permite alquilar, devolver o salir
	 * 
	 * @param utiles,videoclub
	 */
	
	public static void Menu(Utiles utiles,Videoclub videoclub){
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
			
			// Recojo la opcion seleccionada por el usuario
			sOpcion = cogerCadena();
			
			// Parseo el string a entero ya que el switch solo recoje enteros
			iOpcion = utiles.StringAint(sOpcion);
			
			// Segun la opcion seleccionada se accedera a un metodo u otro
			switch (iOpcion) {
			case 1:
				Alquilar(videoclub,utiles);
				break;
			case 2:
				Devolver();
				break;
			case 3:
				flag = false;
				break;
			default:
				System.out.println("Introduzca una de las 3 opciones por favor");
			}
		}
		
	}
	
	/**
	 * Metodo que nos permite devolver una pelicula
	 */
	
	public static void Devolver(){
		
	}
	
	/**
	 * Metodo que nos permite alquilar una pelicula
	 * @param videoclub
	 */
	
	public static void Alquilar(Videoclub videoclub,Utiles utiles){
		boolean flag = true;
		String sOpcion;
		int iOpcion;
		
		/* Ojo: Las operaciones se pueden llevar a cabo todas las veces que 
		 		el usuario desee, hasta que no presione la opcion 3. Salir*/
		while (flag) {
			System.out.println("\n------------Alquiler TOPE------------");
			System.out.println("Seleccione una opcion: ");
			System.out.println("1. TopSemanal");
			System.out.println("2. TopMensual");
			System.out.println("3. Novedades");
			System.out.println("4. Busqueda de Películas");
			System.out.println("5. Volver a Menu principal");
			
			// Recojo la opcion seleccionada por el usuario
			sOpcion = cogerCadena();
			
			// Parseo el string a entero ya que el switch solo recoje enteros
			iOpcion = utiles.StringAint(sOpcion);
			
			// Segun la opcion seleccionada se accedera a un metodo u otro
			switch (iOpcion) {
			case 1:
				topSemanalMensual(videoclub,iOpcion);
				break;
			case 2:
				topSemanalMensual(videoclub,iOpcion);
				break;
			case 5:
				flag = false;
				break;
			default:
				System.out.println("Introduzca una de las 3 opciones por favor");
			}
		}
	}
	
	public static void topSemanalMensual(Videoclub videoclub, int iOpcion){
		
		Pelicula topSemanal[];
		Pelicula topMensual[];
		Pelicula pelicula[];
		Pelicula top[];
		int cont = 0;
		int contTopSemanal = 0;
		int contTopMensual = 0;
		int diaActual;
		int mesActual;
		int anioActual;
		int diaObjeto;
		int mesObjeto;
		int anioObjeto;
		int dias;
		
		Calendar calendarioActual = new GregorianCalendar();
		Calendar calendarioObjeto = Calendar.getInstance();
		Date fecha = new Date();
		
		diaActual = calendarioActual.get(Calendar.DAY_OF_MONTH);
		mesActual = calendarioActual.get(Calendar.MONTH + 1) ;
		anioActual = calendarioActual.get(Calendar.YEAR) ;
		
		for(Object o:videoclub.getAlquileres()){
			if(o instanceof Pelicula){
				cont++;
			}
		}
		
		topSemanal = new Pelicula[cont];
		topMensual = new Pelicula[cont];
		
		for(Object o:videoclub.getAlquileres()){
			if(o instanceof Pelicula){
				//fecha = pelicula[i].getUltimoalq();
				
				fecha = (Date) ((Pelicula) o).getUltimoalq();
				
				calendarioObjeto.setTime(fecha);
				
				diaObjeto = calendarioObjeto.get(Calendar.DAY_OF_MONTH);
				mesObjeto = calendarioObjeto.get(Calendar.MONTH + 1);
				anioObjeto = calendarioObjeto.get(Calendar.YEAR);
				
				dias = DiferenciaDias(anioActual, mesActual, diaActual, anioObjeto, mesObjeto, diaObjeto);
				
				if (dias <= 7) {
					topSemanal[contTopSemanal] = (Pelicula) o;
					contTopSemanal++;
				} 
				else if(dias<=31) {
					topSemanal[contTopMensual] = (Pelicula) o;
					contTopMensual++;
				}
			}
		}
		
		
		if (iOpcion == 1) {
			topSemanal = ordenacionTop(topSemanal);
			mostrarTop(topSemanal);
		} else if (iOpcion == 2) {
			topMensual = ordenacionTop(topMensual);
			mostrarTop(topMensual);
		}
	}
	
	
	public static void mostrarTop(Pelicula[] top) {
		for(int i = 0; i<top.length;i++){
			System.out.println("\n" + top[i].getNumalquiler());
		}
	}

	/**
	 * Metodo que ordena un array de peliculas segun el nº de veces que haya sido alquilada
	 * 
	 * @param ordenacionTop
	 * @return
	 */
	
	public static Pelicula[] ordenacionTop(Pelicula[] ordenacionTop) {
		Pelicula aux = new Pelicula();
		
		for(int i=2;i<=ordenacionTop.length;i++){
			for(int j=0;j<ordenacionTop.length-1;j++) {
				if (ordenacionTop[j].getNumalquiler()<ordenacionTop[j+1].getNumalquiler()){
					aux = ordenacionTop[j];
					ordenacionTop[j] = ordenacionTop[j+1];
					ordenacionTop[j+1] = aux;
				}
			}
		}
		
		return ordenacionTop;
	}

	/**
	 * Metodo que te devuelve la diferencia de dias entre 2 fechas
	 * 
	 * @param anioActual
	 * @param mesActual
	 * @param diaActual
	 * @param anioObjeto
	 * @param mesObjeto
	 * @param diaObjeto
	 * @return
	 */
	
	public static int DiferenciaDias(int anioActual, int mesActual, int diaActual, int anioObjeto, int mesObjeto, int diaObjeto){
		GregorianCalendar fechaActual; 
		GregorianCalendar fechaObjeto; 
		int diasDiferencia;
		
		fechaActual = new GregorianCalendar(anioActual, mesActual, diaActual);
		fechaObjeto = new GregorianCalendar(anioObjeto, mesObjeto, diaObjeto);
		//Obtengo los objetos Date para cada una de ellas
		Date fec1 = (Date) fechaActual.getTime();
		Date fec2 = (Date) fechaObjeto.getTime();
		//Realizo la operación
		long time = fec2.getTime() - fec1.getTime();
		//Guardo el resultado en días
		diasDiferencia = (int) (time/(3600*24*1000));
		
		return diasDiferencia;
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
		String aux= cogerCadena();
		videoclub.idioma= aux;
		
	}
	
	
	/**
	 * Método auxiliar de recogida de datos por teclado
	 */
	public static String cogerCadena(){
		String texto="";
		try{
			//Obtención del objeto Reader
			InputStreamReader conv= new InputStreamReader(System.in);
			//Obtención del BufferedReader
			BufferedReader entrada= new BufferedReader(conv);
			texto= entrada.readLine();
		} catch (IOException e){
			System.out.println(e.toString());
		}
		return texto;
	}
		

}

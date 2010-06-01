package videoclub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Jairo Figueroa Jimenez
 * @version 1.0 Fecha: 25/05/2010
 */

public class Utiles {

	/**
	 * La clave de todo está en el método setLenient. Si se establece a false,
	 * como se hace en el método se fuerza a que la fecha
	 * "tenga sentido estricto", y por lo tanto rechaza un "30 de febrero" o un
	 * "29 de febrero de 2007" como fechas válidas. Si no establecemos el
	 * lenient a false, al parsear una fecha "interpretará" la fecha correcta.
	 * Un "30 de febrero" se convertirá en 1 marzo, (en 2 de marzo si es un año
	 * no bisiesto)...
	 * 
	 * @param fechax
	 * @return
	 */

	public static boolean isFechaValida(String fechax) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",
					Locale.getDefault());
			formatoFecha.setLenient(false);
			formatoFecha.parse(fechax);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	/**
	 * Funcion que pasea un string a un Date
	 * 
	 * @return Date
	 */

	public static Date StringAdate(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date d = null;
		try {
			d = formato.parse(fecha);
		} catch (ParseException e) {
			System.out.println("Error al parsear la fecha: " + e.toString());
		} finally {
			return d;
		}
	}

	/**
	 * Funcion que parsea una cadena a un entero
	 * 
	 * @param texto
	 */

	public static int StringAint(String texto) {
		int opcion = 0;
		try {
			opcion = Integer.parseInt(texto);

		} catch (NumberFormatException e) {
			System.out.println(e.toString());
		} finally {
			return opcion;
		}
	}

	public static String leerDatos() {
		String dato = "";

		try {
			// Obtencion del objeto Reader
			InputStreamReader conv = new InputStreamReader(System.in);
			// Obtencion del BufferedReader
			BufferedReader entrada = new BufferedReader(conv);
			dato = entrada.readLine();

		} catch (IOException e) {
			System.out.println("Error");
		}
		String datos = dato;
		return datos;

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
	 * Metodo que asigna valores a los atributos del objeto socios
	 */

	public static Socio rellenarSocio() {
		Socio p = new Socio();

		System.out.println("Introduzca el nombre: ");
		p.setNombre(leerDatos());
		System.out.println("Introduzca el apellido: ");
		p.setApellido(leerDatos());
		System.out.println("Introduzca el saldo: ");
		p.setSaldo(leerDouble());
		System.out.println("Introduzca el ID: ");
		p.setIdSocio(leerInt());
		

		return p;
	}

	/**
	 * Metodo que asigna valores a los atributos del objeto pelicula
	 */

	public static Pelicula rellenarPelicula() {

		Pelicula i = new Pelicula();
		System.out.println("Introduzca el ID: ");
		i.setIdPeli(leerInt());
		System.out.println("Introduzca el titulo: ");
		i.setTitulo(leerDatos());
		System.out.println("Introduzca el género: ");
		i.setGenero(leerDatos());
		//Recojo la fecha y hora actual
		java.util.Date utilDate = new Date();
		//Lo convierto al tipo java.sql.Date que usan las bases de datos
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		i.setFechaAltaBD(date);
		

		return i;
	}
	public static double leerDouble(){
		double dato = 0.0;

		try {
			// Obtencion del objeto Reader
			InputStreamReader conv = new InputStreamReader(System.in);
			// Obtencion del BufferedReader
			BufferedReader entrada = new BufferedReader(conv);
			dato = Double.parseDouble(entrada.readLine());

		} catch (IOException e) {
			System.out.println("Error");
		}
		
		return dato;
	}
	
	public static int leerInt(){
		int dato = 0;

		try {
			// Obtencion del objeto Reader
			InputStreamReader conv = new InputStreamReader(System.in);
			// Obtencion del BufferedReader
			BufferedReader entrada = new BufferedReader(conv);
			dato = Integer.parseInt(entrada.readLine());

		} catch (IOException e) {
			System.out.println("Error");
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
		}
		
		return dato;
	}

	public static Oficina rellenarOficina() {
		
		Oficina o = new Oficina();
		System.out.println("Introduzca la ciudad: ");
		o.setCiudad(leerDatos());
		System.out.println("Introduzca la direccion: ");
		o.setDireccion(leerDatos());
		System.out.println("Introduzca el telefono: ");
		o.setTelefono(leerDatos());
		//Recojo la fecha y hora actual
		java.util.Date utilDate = new Date();
		//Lo convierto al tipo java.sql.Date que usan las bases de datos
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		o.setFechaDeAlta(date);
		return o;
	}

	/**
	 * Rellena un objeto distribuidor
	 * @return
	 */
	public static Distribuidor rellenarDistribuidor() {
		Distribuidor p = new Distribuidor();

		System.out.println("Introduzca el nombre: ");
		p.setNombre(leerDatos());
		System.out.println("Introduzca el empresa: ");
		p.setEmpresa(leerDatos());
		System.out.println("Introduzca el saldo: ");
		p.setTelefono(leerDatos());
		System.out.println("Introduzca el nivel de la relación (alto, medio, bajo) : ");
		p.setNivelRelacion(leerDatos());

		return p;
	}
}

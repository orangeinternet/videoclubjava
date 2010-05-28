package videoclub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	 * Metodo que asigna valores a los atributos del objeto socios
	 */

	public Socio rellenarSocio() {
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

	public Pelicula rellenarPelicula() {

		Pelicula i = new Pelicula();
		System.out.println("Introduzca el ID: ");
		i.setIdPeli(leerInt());
		System.out.println("Introduzca el titulo: ");
		i.setTitulo(leerDatos());
		System.out.println("Introduzca el género: ");
		i.setGenero(leerDatos());
		i.setFechaAltaBD(new Date());
		

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
		}
		
		return dato;
	}

}

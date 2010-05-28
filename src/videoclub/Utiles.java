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
 * @version 1.0
 * Fecha: 25/05/2010 
 */

public class Utiles {


	/**
	 * La clave de todo est� en el m�todo setLenient. Si se establece a false, como se hace en el m�todo 
	 * se fuerza a que la fecha "tenga sentido estricto",
	 * y por lo tanto rechaza un "30 de febrero" o un "29 de febrero de 2007" como fechas v�lidas.
       Si no establecemos el lenient a false, al parsear una fecha "interpretar�" la fecha correcta.
       Un "30 de febrero" se convertir� en 1 marzo, (en 2 de marzo si es un a�o no bisiesto)...
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
			return 
				false;
		}

		return true;
	} 
	
	/**
	 * Funcion que pasea un string a un Date
	 * 
	 * @return Date
	 */
	
	public static Date StringAdate(String fecha) {
		SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
		Date d = null;
		try {
			d = formato.parse(fecha);
		} catch (ParseException e) {
			System.out.println("Error al parsear la fecha: " + e.toString());
		} finally{
			return d;
		}
	}
	
	/**
	 * Funcion que parsea una cadena a un entero
	 * 
	 * @param texto
	 */
	
	public static int StringAint(String texto){
		int opcion = 0; 
		try{
			opcion = Integer.parseInt(texto);
			
		} catch (NumberFormatException e){
			System.out.println(e.toString());
		} finally{
			return opcion;
		}
	}
	public static String leerDatos(){
		String dato = "";
		
		try {
			//Obtencion del objeto Reader
			InputStreamReader conv = new InputStreamReader (System.in);
			//Obtencion del BufferedReader
			BufferedReader entrada = new BufferedReader (conv);
			dato = entrada.readLine();
			
		}catch (IOException e) 
		{
			System.out.println("Error");
		}
		String datos= dato;
		return datos;
	}
	
	/**
	 * Metodo que asigna valores a los atributos del objeto socios
	 */
	 
	public Socio rellenarSocio(){
	
	Socio p = new Socio();
	String nombre = p.getNombre();
	String apellido = p.getApellido();
	double saldo = p.getSaldo();
	int socio = p.getIdsocio();
	
	return p;
	}
	
	/**
	 * Metodo que asigna valores a los atributos del objeto pelicula
	 */
	 
	public Pelicula rellenarPelicula(){
	
	Pelicula i = new Pelicula();
		int idpeli = i.getIdpeli();
	 int numalquiler = i.getNumalquiler();
	String titulo = i.getTitulo();
	 String genero = i.getGenero();
	Date altabd=i.getAltabd();
	 Date ultimoalq=i.getUltimoalq();
	
	return i;
	}
	
	
	
}
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

public class Utiles {
	
	/**
	 * La clave de todo está en el método setLenient. Si se establece a false, como se hace en el método 
	 * se fuerza a que la fecha "tenga sentido estricto",
	 * y por lo tanto rechaza un "30 de febrero" o un "29 de febrero de 2007" como fechas válidas.
       Si no establecemos el lenient a false, al parsear una fecha "interpretará" la fecha correcta.
       Un "30 de febrero" se convertirá en 1 marzo, (en 2 de marzo si es un año no bisiesto)...
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
	
	public Date StringAdate(String fecha) {
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
	
	public int StringAint(String texto){
		int opcion = 0; 
		try{
			opcion = Integer.parseInt(texto);
			
		} catch (NumberFormatException e){
			System.out.println(e.toString());
		} finally{
			return opcion;
		}
		
	}
}

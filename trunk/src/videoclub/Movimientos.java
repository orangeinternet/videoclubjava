package videoclub;
import java.sql.Date;

public class Movimientos {
	/**
	 * Atributos
	 */
	private double ingreso;
	private double gasto;
	private Date fecha;
	/**
	 * @return the ingreso
	 */
	public double getIngreso() {
		return ingreso;
	}
	/**
	 * @param ingreso the ingreso to set
	 */
	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}
	/**
	 * @return the gasto
	 */
	public double getGasto() {
		return gasto;
	}
	/**
	 * @param gasto the gasto to set
	 */
	public void setGasto(double gasto) {
		this.gasto = gasto;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String toString() {
		return ("La Clase Movimientos: " + ingreso + gasto + fecha);		
	}
	

}

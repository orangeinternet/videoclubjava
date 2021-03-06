package videoclub;

import java.lang.*;
import java.util.*;

/**
 * @version VIDEOCLUB 1.0
 * @author Miguel Angel Llamas fecha 25/05/2010
 */
/*
 * nombre contendrá en nombre del Socio apellido idem saldo contendrá un valor
 * double, simula la tarjeta de credito del cliente para poder sacar películas
 * idsocio contendrá un valor único para identificar al socio.
 */
public class Socio {
	private String nombre = "";
	private String apellido = "";
	private double saldo = 0.0;
	private int idSocio = 0;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

}
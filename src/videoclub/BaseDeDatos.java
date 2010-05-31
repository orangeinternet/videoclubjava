package videoclub;
import java.sql.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class BaseDeDatos {
	private String bd; 
	private String login; 
	private String password; 
	private String url; 
	private Connection con;
	
	
	public BaseDeDatos(){
		bd = "clama23_video_grupo02"; 
		login = "grupo02"; 
		password = "123456"; 
		url = "jdbc:mysql://clapinsa.com/"+bd; 
		 try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url,login,password);
		 } catch(SQLException ex) { 
			 System.out.println("Hubo un problema al intentar conectarse  con la base de datos "+url); 
		 } catch(ClassNotFoundException ex) {
			 System.out.println(ex.getMessage());
		 } catch (InstantiationException e) {
			 System.out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList devuelvePeliculas(){
		 PreparedStatement psSelect = null;
		 Pelicula pelicula;
		 ArrayList aList = new ArrayList();
		 BaseDeDatos bd = new BaseDeDatos();
		 
		 try { 
			 psSelect = con.prepareStatement("select p.numalquiler,p.titulo,p.genero,p.fechaAltaBD,p.fechaUltimoAlq from peliculas p"); 
             ResultSet rs = psSelect.executeQuery(); 
             
             while (rs.next()) {
            	 pelicula = new Pelicula();
            	 pelicula.setNumAlquiler(rs.getInt(1));
                 pelicula.setTitulo(rs.getString(2));
                 pelicula.setGenero(rs.getString(3));
                 pelicula.setFechaAltaBD(rs.getDate(4));
                 pelicula.setFechaUltimoAlq(rs.getDate(5));
                 aList.add(pelicula);
             } 
         } catch (SQLException e) { 
             System.out.println(e.getMessage()); 
         } finally{
			if(psSelect != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}
         
        return aList;
	}
	
	public void topSemanalMensualNovedades(int iOpcion){
		
		Pelicula topSemanal[];
		Pelicula topMensual[];
		int contNovedades = 0;
		int contTopSemanal = 0;
		int contTopMensual = 0;
		int diaActual;
		int mesActual;
		int anioActual;
		int diaObjeto;
		int mesObjeto;
		int anioObjeto;
		int diaNovedad;
		int mesNovedad;
		int anioNovedad;
		int diasTop;
		int diasNovedad;
		Calendar calendarioActual = new GregorianCalendar();
		ArrayList pelicula = new ArrayList();
		ArrayList novedades = new ArrayList();
		Date fechaTop = new Date();
		Date fechaNovedad = new Date();
		BaseDeDatos baseDeDatos = new BaseDeDatos();
		
		
		Calendar calendarioObjeto = Calendar.getInstance();
		
		diaActual = calendarioActual.get(Calendar.DAY_OF_MONTH);
		mesActual = calendarioActual.get(Calendar.MONTH + 1) ;
		anioActual = calendarioActual.get(Calendar.YEAR) ;
		
		pelicula = baseDeDatos.devuelvePeliculas();
		
		topSemanal = new Pelicula[pelicula.size()];
		topMensual = new Pelicula[pelicula.size()];
		
		for(Object o:pelicula){
			fechaTop = (Date) ((Pelicula) o).getFechaUltimoAlq();
				
			calendarioObjeto.setTime(fechaTop);
			
			diaObjeto = calendarioObjeto.get(Calendar.DAY_OF_MONTH);
			mesObjeto = calendarioObjeto.get(Calendar.MONTH + 1);
			anioObjeto = calendarioObjeto.get(Calendar.YEAR);
				
			diasTop = DiferenciaDias(anioActual, mesActual, diaActual, anioObjeto, mesObjeto, diaObjeto);
			
			/******************************************************/
			
			fechaNovedad = (Date) ((Pelicula) o).getFechaAltaBD();
			
			calendarioObjeto.setTime(fechaNovedad);
			
			diaNovedad = calendarioObjeto.get(Calendar.DAY_OF_MONTH);
			mesNovedad = calendarioObjeto.get(Calendar.MONTH + 1);
			anioNovedad = calendarioObjeto.get(Calendar.YEAR);
			
			diasNovedad = DiferenciaDias(anioActual, mesActual, diaActual, anioNovedad, mesNovedad, diaNovedad);
			
			if (diasTop <= 7) {
				topSemanal[contTopSemanal] = (Pelicula) o;
				contTopSemanal++;
			} 
			if(diasTop<=31) {
				topMensual[contTopMensual] = (Pelicula) o;
				contTopMensual++;
			}
			if(diasNovedad<=31){
				novedades.add((Pelicula) o);
			}
		}
		
		if (iOpcion == 1) {
			topSemanal = ordenacionTop(topSemanal);
			mostrarTop(topSemanal);
		} else if (iOpcion == 2) {
			topMensual = ordenacionTop(topMensual);
			mostrarTop(topMensual);
		} else if (iOpcion == 3){
			mostrarNovedades(novedades);
		}
	}
	
	public void mostrarNovedades(ArrayList novedades){
		
		if(novedades.size() == 0) {
			System.out.println("En este momento no hay novedades");
		} else {
			for(Object o:novedades){
				System.out.println("\n Nº de veces alquiladas: " + ((Pelicula) o).getNumAlquiler());
				System.out.println(" Titulo: " + ((Pelicula) o).getTitulo());
				System.out.println(" Genero: " + ((Pelicula) o).getGenero());
				System.out.println(" Ultimo Alquiler: " + ((Pelicula) o).getFechaUltimoAlq());
			}
		}
	}
	
	public void mostrarTop(Pelicula[] top) {
		for(int i = 0; i<top.length;i++){
			System.out.println("\n Nº de veces alquiladas: " + top[i].getNumAlquiler());
			System.out.println(" Titulo: " + top[i].getTitulo());
			System.out.println(" Genero: " + top[i].getGenero());
			System.out.println(" Ultimo Alquiler: " + top[i].getFechaUltimoAlq());
		}
	}

	public Pelicula[] ordenacionTop(Pelicula[] ordenacionTop) {
		Pelicula aux = new Pelicula();
		
		for(int i=2;i<=ordenacionTop.length;i++){
			for(int j=0;j<ordenacionTop.length-1;j++) {
				if (ordenacionTop[j].getNumAlquiler()<ordenacionTop[j+1].getNumAlquiler()){
					aux = ordenacionTop[j];
					ordenacionTop[j] = ordenacionTop[j+1];
					ordenacionTop[j+1] = aux;
				}
			}
		}
		
		return ordenacionTop;
	}
	
	public int DiferenciaDias(int anioActual, int mesActual, int diaActual, int anioObjeto, int mesObjeto, int diaObjeto){
		GregorianCalendar fechaActual; 
		GregorianCalendar fechaObjeto; 
		int diasDiferencia;
		
		fechaActual = new GregorianCalendar(anioActual, mesActual, diaActual);
		fechaObjeto = new GregorianCalendar(anioObjeto, mesObjeto, diaObjeto);
		//Obtengo los objetos Date para cada una de ellas
		Date fec1 = (Date) fechaActual.getTime();
		Date fec2 = (Date) fechaObjeto.getTime();
		//Realizo la operación
		long time = fec1.getTime() - fec2.getTime();
		//Guardo el resultado en días
		diasDiferencia = (int) (time/(3600*24*1000));
		
		return diasDiferencia;
	}
	
	public void busquedaPelicula(String cadena){
		PreparedStatement psSelect = null;
		Pelicula pelicula = new Pelicula();
		ArrayList aList = new ArrayList();
		BaseDeDatos bd = new BaseDeDatos();
		 
		try { 
			psSelect = con.prepareStatement("select p.idPeli,p.numAlquiler,p.titulo,p.genero,p.fechaAltaBD,fechaUltimaAlq from peliculas p where p.titulo LIKE ('?') "); 
			psSelect.setString(1, cadena);
			
			ResultSet rs = psSelect.executeQuery(); 
            
            while (rs.next()) { 
            	pelicula.setIdPeli(rs.getInt(1));
                pelicula.setNumAlquiler(rs.getInt(2));
                pelicula.setTitulo(rs.getString(3));
                pelicula.setGenero(rs.getString(4));
                pelicula.setFechaAltaBD(rs.getDate(5));
                pelicula.setFechaUltimoAlq(rs.getDate(6));
                aList.add(pelicula);
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } finally{
			if(psSelect != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}
        
        if (aList.size() == 0) {
        	System.out.println("No se encontro ningun resultado");
        } else {
        	mostrarPeliculas(aList);
        }
	}

	public void mostrarPeliculas(ArrayList peliculas) {
		for(Object o:peliculas){
			// Muestro las pelis
		}
	}
	
	/**
	 * Almacena una nueva película en base de datos
	 * @param p
	 */
	public void almacenarPelicula(Pelicula p){
		try {
			java.sql.Statement st= con.createStatement();
			System.out.println(st.executeUpdate("INSERT Into peliculas (numAlquiler, titulo, genero, fechaAltaBD) Values ("+p.getNumAlquiler()+", '"+p.getTitulo()+"','"+p.getGenero()+"','"+p.getFechaAltaBD()+"')"));
		} catch (SQLException e) {
			System.out.println("e.toString()");
			e.printStackTrace();
		}
	}
	
	/**
	 * Almacena un nuevo socio en base de datos
	 * @param s
	 */
	public void almacenarSocio(Socio s){
		try {
			java.sql.Statement st= con.createStatement();
			System.out.println(st.executeUpdate("INSERT Into socios (nombre, apellido, saldo) Values ('"+s.getNombre()+"', '"+s.getApellido()+"',"+s.getSaldo()+")"));
		} catch (SQLException e) {
			System.out.println("e.toString()");
			e.printStackTrace();
		}
	}
	
	/**
	 * Un socio devuelve una película
	 * @param idPelicula
	 * @param idSocio
	 */
	public void devolverPelicula(int idPelicula, int idSocio){
		
	}
	
	/**
	 * Un socio alquila una pelicula
	 * @param idPelicula
	 * @param idSocio
	 */
	public void alquilarPelicula(int idPelicula, int idSocio)
	{
		
	}

	public void almacenarOficina(Oficina o) {
		// TODO Auto-generated method stub
		
	}

	public void mostrarSocios() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarPeliculas() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarOficinas() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarMovimientos() {
		// TODO Auto-generated method stub
		
	}
	
}

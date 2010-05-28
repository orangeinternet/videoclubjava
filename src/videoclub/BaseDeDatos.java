package videoclub;

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
	static String bd = "clama23_video_grupo02"; 
	static String login = "grupo02"; 
	static String password = "123456"; 
	static String url = "jdbc:mysql://clapinsa.com/"+bd; 
	static Connection con;
	
	
	BaseDeDatos(){
		con = null;
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
	
	public ArrayList devuelveAlquileres(){
		 PreparedStatement psSelect = null;
		 Pelicula pelicula = new Pelicula();
		 ArrayList aList = new ArrayList();
		 BaseDeDatos bd = new BaseDeDatos();
		 
		 try { 
			 psSelect = con.prepareStatement("select p.numalquiler,p.titulo,p.genero,p.fechaAltaBD,p.fechaUltimaAlq from alquileres a,peliculas p where p.idpeli=a.idpeli "); 
             ResultSet rs = psSelect.executeQuery(); 
             
             while (rs.next()) { 
                 pelicula.setNumalquiler(rs.getInt(1));
                 pelicula.setTitulo(rs.getString(2));
                 pelicula.setGenero(rs.getString(3));
                 pelicula.setAltabd(rs.getDate(4));
                 pelicula.setUltimoalq(rs.getDate(5));
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
		
		pelicula = baseDeDatos.devuelveAlquileres();
		
		topSemanal = new Pelicula[pelicula.size()];
		topMensual = new Pelicula[pelicula.size()];
		
		for(Object o:pelicula){
			fechaTop = (Date) ((Pelicula) o).getUltimoalq();
				
			calendarioObjeto.setTime(fechaTop);
			
			diaObjeto = calendarioObjeto.get(Calendar.DAY_OF_MONTH);
			mesObjeto = calendarioObjeto.get(Calendar.MONTH + 1);
			anioObjeto = calendarioObjeto.get(Calendar.YEAR);
				
			diasTop = DiferenciaDias(anioActual, mesActual, diaActual, anioObjeto, mesObjeto, diaObjeto);
			
			/******************************************************/
			
			fechaNovedad = (Date) ((Pelicula) o).getAltabd();
			
			calendarioObjeto.setTime(fechaNovedad);
			
			diaNovedad = calendarioObjeto.get(Calendar.DAY_OF_MONTH);
			mesNovedad = calendarioObjeto.get(Calendar.MONTH + 1);
			anioNovedad = calendarioObjeto.get(Calendar.YEAR);
			
			diasNovedad = DiferenciaDias(anioActual, mesActual, diaActual, anioNovedad, mesNovedad, diaNovedad);
			
			if (diasTop <= 7) {
				topSemanal[contTopSemanal] = (Pelicula) o;
				contTopSemanal++;
			} 
			else if(diasTop<=31) {
				topSemanal[contTopMensual] = (Pelicula) o;
				contTopMensual++;
			}
			else if(diasNovedad<=31){
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
		for(Object o:novedades){
			//Muestro las novedades
		}
	}
	
	public void mostrarTop(Pelicula[] top) {
		for(int i = 0; i<top.length;i++){
			System.out.println("\n" + top[i].getNumalquiler());
		}
	}

	public Pelicula[] ordenacionTop(Pelicula[] ordenacionTop) {
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
	
	public int DiferenciaDias(int anioActual, int mesActual, int diaActual, int anioObjeto, int mesObjeto, int diaObjeto){
		GregorianCalendar fechaActual; 
		GregorianCalendar fechaObjeto; 
		int diasDiferencia;
		
		fechaActual = new GregorianCalendar(anioActual, mesActual, diaActual);
		fechaObjeto = new GregorianCalendar(anioObjeto, mesObjeto, diaObjeto);
		//Obtengo los objetos Date para cada una de ellas
		Date fec1 = (Date) fechaActual.getTime();
		Date fec2 = (Date) fechaObjeto.getTime();
		//Realizo la operaci�n
		long time = fec2.getTime() - fec1.getTime();
		//Guardo el resultado en d�as
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
            	pelicula.setIdpeli(rs.getInt(1));
                pelicula.setNumalquiler(rs.getInt(2));
                pelicula.setTitulo(rs.getString(3));
                pelicula.setGenero(rs.getString(4));
                pelicula.setAltabd(rs.getDate(5));
                pelicula.setUltimoalq(rs.getDate(6));
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
	
	
}

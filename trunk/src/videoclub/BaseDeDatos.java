package videoclub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
			 psSelect = con.prepareStatement("select p.numalquiler,p.titulo,p.genero,p.altabd,p.ultimoalq from alquileres a,peliculas p where p.idpeli=a.idpeli "); 
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
}

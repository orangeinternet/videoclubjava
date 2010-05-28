package videoclub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BD {
	static String bd = "videoclub"; 
	static String login = "root"; 
	static String password = "videoclub"; 
	static String url = "jdbc:mysql://localhost/"+bd; 
	static Connection con;
	
	
	BD(){
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
		 BD bd = new BD();
		 
		 try { 
			 psSelect = con.prepareStatement("select p.numalquiler,p.titulo,p.genero,p.altabd,p.ultimoalq  from alquiler"); 
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

package videoclub;

import Cliente;
import Persistencia;

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
			 psSelect = con.prepareStatement("select * from alquiler"); 
             ResultSet rs = psSelect.executeQuery(); 
             
             while (rs.next()) { 
                 /*System.out.println("idCliente=" + rs.getInt(1) + " Nombre=" 
                         + rs.getString(2) + " Edad=" + rs.getInt(3) 
                         + " Direccion=" + rs.getString(4)
                         + " Tlf=" + rs.getString(5));*/
                 
                 cliente.setIdCliente(rs.getInt(1));
                 cliente.setNombre(rs.getString(2));
                 cliente.setEdad(rs.getInt(3));
                 cliente.setDireccion(rs.getString(4));
                 cliente.setTlf(rs.getString(5));
                 aList.add(cliente);
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

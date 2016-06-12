package videoclub;
import java.io.InputStreamReader;
import java.sql.*;
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
	
	/**
	 * Constructor para establecer la conexion
	 */
	
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
	
	/**
	 * Metodo que devuelve todas las peliculas de la BD
	 * @return
	 */
	
	public ArrayList devuelvePeliculas(){
		 PreparedStatement psSelect = null;
		 Pelicula pelicula;
		 ArrayList aList = new ArrayList();
		 BaseDeDatos bd = new BaseDeDatos();
		 
		 try { 
			 //con = bd.conexionBD();
			 psSelect = con.prepareStatement("select sum(p.numalquiler) as cantidad, p.numalquiler,p.titulo,p.genero,p.fechaAltaBD,p.fechaUltimoAlq, datediff(now(),p.fechaUltimoAlq) <= 7 as topSemanalMensual," +
					                         "datediff(now(),p.fechaAltaBd) <= 7 as novedades from peliculas p group by p.titulo order by p.numAlquiler desc;"); 
            ResultSet rs = psSelect.executeQuery(); 
            
            while (rs.next()) {
           	 pelicula = new Pelicula();
           	 pelicula.setNumAlquiler(rs.getInt(1));
                pelicula.setTitulo(rs.getString(3));
                pelicula.setGenero(rs.getString(4));
                pelicula.setFechaAltaBD(rs.getDate(5));
                pelicula.setFechaUltimoAlq(rs.getDate(6));
                pelicula.setDiffTopSemMen(rs.getInt(7));
                pelicula.setDiffNovedades(rs.getInt(8));
                aList.add(pelicula);
            } 
        } catch (SQLException e) { 
            System.out.println(e.getMessage()); 
        }/* finally{
			if(psSelect != null) {
				try {
					//con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}*/
        
       return aList;
	}
	
	/**
	 * Metodo que te devuelve las peliculas ordenadas alfabeticamente
	 * @return
	 */
	
	public ArrayList devuelvePeliculasOrd(){
		 PreparedStatement psSelect = null;
		 Pelicula pelicula;
		 ArrayList aList = new ArrayList();
		 BaseDeDatos bd = new BaseDeDatos();
		 
		 try { 
			// con = bd.conexionBD();
			 psSelect = con.prepareStatement("select p.numalquiler,p.titulo,p.genero,p.fechaAltaBD,p.fechaUltimoAlq " +
					                         "from peliculas p order by p.titulo asc"); 
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
       } /*finally{
			if(psSelect != null) {
				try {
					//con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}*/
       
      return aList;
	}
	
	/**
	 * Metodo que recoje las ultimas novedades, top semana y top mensual
	 * @param iOpcion
	 */
	
	public void topSemanalMensualNovedades(int iOpcion){
		
		ArrayList peliculas = new ArrayList();
		ArrayList topSemanal = new ArrayList();
		ArrayList topMensual = new ArrayList();
		ArrayList novedades = new ArrayList();
		BaseDeDatos baseDeDatos = new BaseDeDatos();
		Pelicula p;
		peliculas = baseDeDatos.devuelvePeliculas();
		
		for(Object o:peliculas){
			p = new Pelicula();
			p = (Pelicula) o;
			
			if (p.getDiffTopSemMen() == 1) {
				topSemanal.add(p);
				topMensual.add(p);
			} else 
				topMensual.add(p);
			
			if(p.getDiffNovedades()==1){
				novedades.add((Pelicula) o);
			}
		}
		
		if (iOpcion == 1) {
			mostrarTop(topSemanal,"topSemanal");
		} else if (iOpcion == 2) {
			mostrarTop(topMensual,"topMensual");
		} else if (iOpcion == 3){
			mostrarNovedades(novedades);
		}
	}
	
	/**
	 * Metodo que muestra las novedades semanales
	 * @param novedades
	 */
	
	public void mostrarNovedades(ArrayList novedades){
		
		if(novedades.size() == 0) {
			System.out.println("En este memento no hay novedades");
		} else {
			for(Object o:novedades){
				System.out.println("\n N� de veces alquiladas: " + ((Pelicula) o).getNumAlquiler());
				System.out.println(" Titulo: " + ((Pelicula) o).getTitulo());
				System.out.println(" Genero: " + ((Pelicula) o).getGenero());
				System.out.println(" Ultimo Alquiler: " + ((Pelicula) o).getFechaUltimoAlq());
			}
		}
	}
	
	/**
	 * Metodo que muestra el Top Semanal de peliculas y el Top Mensual
	 * @param top
	 * @param lista
	 */
	
	public void mostrarTop(ArrayList top,String lista) {
		Pelicula p;
		if(top.size() == 0) {
			System.out.println("En estos momentos no hay peliculas en el " + lista);
		} else {
			for(Object o:top){
				p = (Pelicula) o;
				System.out.println("\n N� de veces alquiladas: " + p.getNumAlquiler());
				System.out.println(" Titulo: " + p.getTitulo());
				System.out.println(" Genero: " + p.getGenero());
				System.out.println(" Ultimo Alquiler: " + p.getFechaUltimoAlq());
			}
		}
	}
	
	/**
	 * Metodo que muestra una o varias peliculas
	 * @param cadena
	 */
	
	public void busquedaPelicula(String cadena){
		Statement psSelect = null;
		Pelicula pelicula = new Pelicula();
		ArrayList aList = new ArrayList();
		BaseDeDatos bd = new BaseDeDatos();
		
		try {
			//con = bd.conexionBD();
			
			psSelect = con.createStatement(); 
			
			ResultSet rs = psSelect.executeQuery("select p.idPeli,p.numAlquiler,p.titulo,p.genero,p.fechaAltaBD,fechaUltimoAlq from peliculas p where p.titulo LIKE '%"+cadena+"%'"); 
            
            while (rs.next()) { 
            	pelicula = new Pelicula();
            	pelicula.setIdPeli(rs.getInt(1));
                pelicula.setNumAlquiler(rs.getInt(2));
                pelicula.setTitulo(rs.getString(3));
                pelicula.setGenero(rs.getString(4));
                pelicula.setFechaAltaBD(rs.getDate(5));
                pelicula.setFechaUltimoAlq(rs.getDate(6));
                aList.add(pelicula);
            } 
        } catch (SQLException e) { 
            System.out.println("Se ha producido un error: "+ e.getMessage());
        } /*finally{
			if(psSelect != null) {
				try {
					//con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}*/
        
        if (aList.size() == 0) {
        	System.out.println("No se encontro ningun resultado");
        } else {
        	mostrarPeliculas(aList);
        }
	}
	
	/**
	 * Metodo que devuelve el numero de d�as entre la fecha actual y la fecha del ultimo alquiler
	 * @param idPelicula
	 * @return
	 */
	
	public int devuelveDias(int idPelicula){
		Statement psSelect = null;
		BaseDeDatos bd = new BaseDeDatos();
		int dias = 0;
		
		try {
			psSelect = con.createStatement(); 
			
			ResultSet rs = psSelect.executeQuery("select datediff(now(), fechaAlq) as dias from alquileres where idPelicula="+idPelicula); 
            
            if (rs.next()) { 
            	dias = rs.getInt(rs.getInt(1));
            } 
        } catch (SQLException e) { 
            System.out.println("Se ha producido un error: "+ e.getMessage());
        }
        
        return dias;
    }
	
	/**
	 * Metodo que muestra por pantalla todas las peliculas
	 * @param peliculas
	 */
	
	public void mostrarPeliculas(ArrayList peliculas) {
		Pelicula p;
		for(Object o:peliculas){
			p = (Pelicula) o;
			System.out.println("\nTitulo: " + p.getTitulo());
			System.out.println("Genero: " + p.getGenero());
		}
	}
	
	/**
	 * Almacena una nueva pel�cula en base de datos
	 * @param p
	 */
	public void almacenarPelicula(Pelicula p){
		try {
			java.sql.Statement st= con.createStatement();
			System.out.println(st.executeUpdate("INSERT Into peliculas (numAlquiler, titulo, genero, fechaAltaBD) Values ("+p.getNumAlquiler()+", '"+p.getTitulo()+"','"+p.getGenero()+"','"+p.getFechaAltaBD()+"')"));
			insertarGasto(3);
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
	 * Un socio devuelve una pel�cula
	 * @param idPelicula
	 * @param idSocio
	 */
	public void devolverPelicula(int idPelicula, int idSocio){
		int dias;
		double diferencia;
		if (verificarAlquiler(idPelicula)) {
			dias = devuelveDias(idPelicula);
			eliminarAlquiler(idSocio,idPelicula);
			diferencia = 0.60*dias;
			restarDiferencia(diferencia,idSocio);
			
		} else {
			System.out.println("La pelicula no se encuentra alquilada");
		}
	}
	
	/**
	 * Metodo que resta el saldo del cliente segun los dias que hayan transcurrido
	 * 
	 * @param diferencia
	 * @param idSocio
	 */
	
	public void restarDiferencia(double diferencia,int idSocio){
		try {
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			Statement st = con.createStatement();
			System.out.println(st.executeUpdate("UPDATE socios SET saldo= (saldo - "+diferencia+") where idSocio = "+idSocio));
			insertarIngreso(diferencia);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que nos permite devolver una pelicula
	 * @param idSocio
	 * @param idPelicula
	 */
	
	public void eliminarAlquiler(int idSocio,int idPelicula){
		Statement ps = null;
		
		try { 
			 ps = con.createStatement();
             ps.executeUpdate("delete from alquileres where idPelicula="+idPelicula+" and idSocio="+idSocio); 
          
             System.out.println("Se ha devuelto la pelicula correctamente");
         } catch (SQLException e) { 
             System.out.println("Se ha producido un error al eliminar al cliente"); 
         } /*finally{
			if(ps != null) {
				try {
					//con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}*/
		
	}
	
	/**
	 * Un socio alquila una pelicula
	 * @param idPelicula
	 * @param idSocio
	 */
	public void alquilarPelicula(int idPelicula, int idSocio)
	{
		if(verificarPelicula(idPelicula)){
			insertarAlquiler(idPelicula, idSocio);
			aumentarNumAlquiler(idPelicula);
		} else {
			System.out.println("El identificador de la pelicula no existe");
		}
	}

	/**
	 * Metodo que aumenta el numero de alquileres para una pelicula
	 * @param idPelicula
	 */
	
	public void aumentarNumAlquiler(int idPelicula){
		BaseDeDatos bd = new BaseDeDatos();
		try {
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			Statement st = con.createStatement();
			System.out.println(st.executeUpdate("UPDATE peliculas SET numAlquiler= numAlquiler + 1, fechaUltimoAlq='"+date+"' WHERE idPeli="+idPelicula));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que verifica si una pelicula est� alquilada
	 * @param idPelicula
	 * @return
	 */
	
	public boolean verificarAlquiler(int idPelicula){
		 Statement psSelect = null;
		 Pelicula pelicula;
		 ArrayList aList = new ArrayList();
		 BaseDeDatos bd = new BaseDeDatos();
		 boolean flag = false;
		 
		 try { 
			//con = bd.conexionBD();
			psSelect = con.createStatement(); 
           ResultSet rs = psSelect.executeQuery("select * from alquileres where idPelicula = "+idPelicula); 
        
          // psSelect.setInt(1, idPelicula);
          
           if (rs.next()) {
           	flag = true;
           } 
      } catch (SQLException e) { 
          System.out.println(e.getMessage()); 
      }/* finally{
			if(psSelect != null) {
				try {
					//con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}*/
      
     return flag;
	}
	
	/**
	 * 
	 * @param idPelicula
	 * @return
	 */

	
	public boolean verificarPelicula(int idPelicula){
		 Statement psSelect = null;
		 Pelicula pelicula;
		 ArrayList aList = new ArrayList();
		 BaseDeDatos bd = new BaseDeDatos();
		 boolean flag = false;
		 
		 try { 
			//con = bd.conexionBD();
			psSelect = con.createStatement(); 
            ResultSet rs = psSelect.executeQuery("select * from peliculas where idPeli = "+idPelicula); 
         
           // psSelect.setInt(1, idPelicula);
           
            if (rs.next()) {
            	flag = true;
            } 
       } catch (SQLException e) { 
           System.out.println(e.getMessage()); 
       }/* finally{
			if(psSelect != null) {
				try {
					//con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}*/
       
      return flag;
	}
	
	/**
	 * Metodo que nos permite insertar un alquiler
	 * @param idPelicula
	 * @param idSocio
	 */
	
	public void insertarAlquiler(int idPelicula, int idSocio){
		
		Statement psInsertar = null ;
		BaseDeDatos bd = new BaseDeDatos(); 
		//con = bd.conexionBD();
		
		try {
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			
			psInsertar = con.createStatement(); 
			psInsertar.executeUpdate("INSERT Into alquileres (idPelicula,idSocio,fechaAlq) Values ("+idPelicula+","+idSocio+",'"+date+"'");
			psInsertar.executeUpdate("UPDATE alquileres SET titulo = (SELECT titulo FROM peliculas WHERE alquileres.idPeliculas = peliculas.idPelicula)");
			System.out.println("Se ha alquilado la pelicula correctamente");
		}catch (SQLException e) {
			System.out.println("No se pudo alquilar la pelicula correctamente: " + e.getMessage());
		}/*finally{
			if(psInsertar != null) {
				try {
					//con.close();
				} catch (SQLException e) {
					System.out.println("No se pudo cerrar correctamente la conexion");
				}
			}
		}*/
	}
	
	/**
	 * Metodo que inserta un gasto del cliente sobre nuestros movimientos
	 * @param gasto
	 */
	
	public void insertarGasto(double gasto) {
		try {
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			java.sql.Statement st= con.createStatement();
			System.out.println(st.executeUpdate("INSERT Into movimientos (ingreso, gasto, fecha) Values ("+0+", "+gasto+",'"+date+"')"));
		} catch (SQLException e) {
			System.out.println("e.toString()");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que inserta un ingreso sobre la tabla movimientos a la hora de devolver una pelicula
	 * @param ingreso
	 */
	
	public void insertarIngreso(double ingreso) {
		try {
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			java.sql.Statement st= con.createStatement();
			System.out.println(st.executeUpdate("INSERT Into movimientos (ingreso, gasto, fecha) Values ("+ingreso+", "+0+",'"+date+"')"));
		} catch (SQLException e) {
			System.out.println("e.toString()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param o
	 */
	
	public void almacenarOficina(Oficina o) {
		
			try {
				java.sql.Statement st= con.createStatement();
				System.out.println(st.executeUpdate("INSERT Into oficinas (ciudad,direccion,telefono) Values ('"+o.getCiudad()+"','"+o.getDireccion()+"','"+o.getTelefono()+"')"));
			} catch (SQLException e) {
				System.out.println("e.toString()");
				e.printStackTrace();
			}
	}
	
	/**
	 * Metodo que muestra todos los socios que tenemos en BD
	 */

	public void mostrarSocios() {
		try {
			Statement st= con.createStatement();
			ResultSet datos= st.executeQuery("SELECT * FROM socios");
			System.out.println("----Tabla Socios recogida de la BASE DE DATOS----");
			while(datos.next()) {
				System.out.println("--> "+datos.getInt("Id")+" "+datos.getString("nombre")+"  "+datos.getString("apellido")+" "+datos.getDouble("saldo")+"<--");
			}
		} catch (SQLException e) {
				System.out.println(e.toString());                                                                                            
			}
	}

	/**
	 * Metodo que muestra todas las peliculas insertadas en BD
	 */
	
	public void mostrarPeliculas() {
		try {
			Statement st= con.createStatement();
			ResultSet datos= st.executeQuery("SELECT * FROM peliculas");
			System.out.println("----Tabla pel�culas recogida de la BASE DE DATOS----");
			while(datos.next()) {
				System.out.println("--> "+datos.getInt("Id")+" "+datos.getString("titulo")+"  "+datos.getString("genero")+" <--");
			}
		} catch (SQLException e) {
				System.out.println(e.toString());                                                                                            
			}
		
	}
	
	/**
	 * Metodo que muestra todas las oficinas de nuestra BD
	 */

	public void mostrarOficinas() {
		
		try {
			java.sql.Statement st= con.createStatement();
			java.sql.ResultSet datos = st.executeQuery("Select * From oficinas");
			System.out.println("----Tabla Oficinas Recogida de la Base de Datos----");
				while(datos.next()){
				System.out.println("--> "+datos.getString("ciudad")+" "+datos.getString("direccion")+" "+datos.getNString("telefono")+" <--");
			}
		} catch (SQLException e) {
			System.out.println("e.toString()");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Metodo que muestra los movimientos economicos de nuestro videoclub
	 */

	public void mostrarMovimientos() {
		try {
			Statement st= con.createStatement();
			ResultSet datos= st.executeQuery("SELECT * FROM movimientos");
			System.out.println("----Tabla pel�culas recogida de la BASE DE DATOS----");
			System.out.println(" Ingresos   Gastos    Fecha");
			while(datos.next()) {
				System.out.println("--> "+datos.getDouble("ingreso")+"	     "+datos.getDouble("gasto")+"    "+datos.getDate("fecha")+"<--");
			}
		} catch (SQLException e) {
				System.out.println(e.toString());                                                                                            
			}
	}
	
	/**
	 * Metodo que borra la pelicula de la BD
	 * @param id
	 */


	public void borrarPelicula(int id) {
		try {
			java.sql.Statement st= con.createStatement();
			System.out.println(st.executeUpdate("DELETE From peliculas where Id='"+id+"'"));
		} catch (SQLException e) {
			System.out.println("e.toString()");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que muestra los alquileres de las peliculas
	 */
	
	public void mostrarAlquileres() {
		try {
			Statement st= con.createStatement();
			ResultSet datos= st.executeQuery("SELECT * FROM alquileres");
			System.out.println("----Tabla alquileres recogida de la BASE DE DATOS----");
			while(datos.next()) {
				System.out.println("--> "+datos.getInt("idPelicula")+"  "+datos.getString("titulo")+"  "+datos.getInt("idSocio")+" "+datos.getDate("fechaAlq")+"<--");
			}
		} catch (SQLException e) {
				System.out.println(e.toString());                                                                                            
			}
	}

	/**
	 * Metodo que muestra todos los distribuidores de la BD
	 */
	
	public void mostrarDistribuidores() {
		try {
			Statement st= con.createStatement();
			ResultSet datos= st.executeQuery("SELECT * FROM distribuidores");
			System.out.println("----Tabla distribuidores recogida de la BASE DE DATOS----");
			while(datos.next()) {
				System.out.println("--> "+datos.getInt("Id")+"  "+datos.getString("nombre")+" "+datos.getString("empresa")+"  "+datos.getString("telefono")+" "+datos.getString("nivelRelacion")+"<--");
			}
		} catch (SQLException e) {
				System.out.println(e.toString());                                                                                            
			}
	}

	/**
	 * Metodo que inserta distribuidores de nuestra BD
	 * @param d
	 */
	
	public void almacenarDistribuidor(Distribuidor d) {
		try {
			java.sql.Statement st= con.createStatement();
			System.out.println(st.executeUpdate("INSERT Into distribuidores (nombre,empresa,telefono,nivelRelacion) Values ('"+d.getNombre()+"','"+d.getEmpresa()+"','"+d.getTelefono()+"','"+d.getNivelRelacion()+"')"));
		} catch (SQLException e) {
			System.out.println("e.toString()");
			e.printStackTrace();
		}
	}
	/**
	 * Get Connection
	 * @return
	 */
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return con;
	}
	
}

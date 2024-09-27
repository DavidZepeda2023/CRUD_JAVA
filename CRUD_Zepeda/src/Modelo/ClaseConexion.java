
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ClaseConexion {
    
       //Variables para la cadena de conexión
    private static final String URL = "jdbc:oracle:thin:@AGREGAR IP:1521:xe";
    private static final String USUARIO = "System";
    private static final String CONTRASENA = "ITR2024";
    //Creación del metodo de conexión que retorna la conexión
    public static Connection getConexion() {
        try {
            // Cargar el driver JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");
 
            // Obtener la conexión en una variable
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            // Retornamos la variable que tiene la conexión
            return conexion;
        } catch (SQLException e) {
            System.out.println("Este es el error" + e);
              return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("este es el error de la clase" + ex);
              return null;
        }
    }
    
}


package Modelo;

import Vista.FrmAbogados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Abogado {
    
    private String uuid_Abogado;
    private String nombre;
    private int edad;
    private int peso;
    private String correo;
    
     public String getUuid_Abogado() {
        return uuid_Abogado;
    }

    public void setUuid_Abogado(String uuid_Abogado) {
        this.uuid_Abogado = uuid_Abogado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
     public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addAbogado = conexion.prepareStatement("INSERT INTO Abogado(UUID_Abogado, Nombre_Abogado, Edad_Abogado, Peso_Abogado, Correo_Abogado ) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addAbogado.setString(1, UUID.randomUUID().toString());
            addAbogado.setString(2, getNombre());
            addAbogado.setInt(3, getEdad());
            addAbogado.setInt(4, getPeso());
            addAbogado.setString(5, getCorreo());
 
            //Ejecutar la consulta
            addAbogado.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("Este es el error en el modelo:metodo guardar " + ex);
        }
    }
     
     
     public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Abogado", "Nombre_Abogado", "Edad_Abogado", "Peso_Abogado", "Correo_Abogado",});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM Abogado");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Abogado"), 
                    rs.getString("Nombre_Abogado"), 
                    rs.getInt("Edad_Abogado"), 
                    rs.getInt("Peso_Abogado"), 
                    rs.getString("Correo_Abogado")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
     
     
     public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteAbogado = conexion.prepareStatement("delete from Abogado where UUID_Abogado = ?");
            deleteAbogado.setString(1, miId);
            deleteAbogado.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error metodo de eliminar" + e);
        }
        
     } 
     
     
     public void cargarDatosTabla(FrmAbogados vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbAbogado.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbAbogado.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbAbogado.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbAbogado.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbAbogado.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbAbogado.getValueAt(filaSeleccionada, 4).toString();


            // Establece los valores en los campos de texto
            vista.txtnombre.setText(NombreDeTB);
            vista.txtedad.setText(EdadDeTb);
            vista.txtpeso.setText(PesoDeTB);
            vista.txtcorreo.setText(CorreoDeTB);
        }
     }
     
     
     public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update Abogado set Nombre_Abogado= ?, Edad_Abogado = ?, Peso_Abogado = ?, Correo_Abogado = ? where UUID_Abogado = ?");

                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setInt(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("Este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("No");
        }
          
     }
     
      public void limpiar(FrmAbogados vista) {
        vista.txtnombre.setText("");
        vista.txtedad.setText("");
        vista.txtpeso.setText("");
        vista.txtcorreo.setText("");
    }
}


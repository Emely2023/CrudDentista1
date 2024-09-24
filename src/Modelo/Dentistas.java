/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.frmDentista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiante
 */
public class Dentistas {
     //1- Parametros
    private String UUID_Dentista;
    private String NombreDentista;
    private String Correo;
    private int EdadDentista;
    private float PesoDentista;
    
    //2- Getters y Setters

    public String getUUID_Dentista() {
        return UUID_Dentista;
    }

    public void setUUID_Dentista(String UUID_Dentista) {
        this.UUID_Dentista = UUID_Dentista;
    }

    public String getNombreDentista() {
        return NombreDentista;
    }

    public void setNombreDentista(String NombreDentista) {
        this.NombreDentista = NombreDentista;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getEdadDentista() {
        return EdadDentista;
    }

    public void setEdadDentista(int EdadDentista) {
        this.EdadDentista = EdadDentista;
    }

    public float getPesoDentista() {
        return PesoDentista;
    }

    public void setPesoDentista(float PesoDentista) {
        this.PesoDentista = PesoDentista;
    }
    
     ////////////////////////3- Métodos 
      public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addDentista = conexion.prepareStatement("INSERT INTO tbDentista(UUID_Dentista, NombreDentista, EdadDentista, PesoDentista, CorreoDentista) VALUES (?, ?, ?, ? , ?)");
            //Establecer valores de la consulta SQL
            addDentista.setString(1, UUID.randomUUID().toString());
            addDentista.setString(2, getNombreDentista());
            addDentista.setInt(3, getEdadDentista());
            addDentista.setFloat(4,getPesoDentista());
            addDentista.setString(5,getCorreo());
 
            //Ejecutar la consulta
            addDentista
                    .executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
   
      public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Dentista", "NombreDentista", "EdadDentista", "PesoDentista", "CorreoDentista"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbDentista");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Dentista"), 
                    rs.getString("NombreDentista"), 
                    rs.getInt("EdadDentista"), 
                    rs.getFloat("PesoDentista"),
                    rs.getString("CorreoDentista")});

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
            PreparedStatement deleteDentista = conexion.prepareStatement("delete from tbDentista where UUID_Dentista = ?");
            deleteDentista.setString(1, miId);
            deleteDentista.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
       
       
       
        public void cargarDatosTabla(frmDentista vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbDentistas.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbDentistas.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbDentistas.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbDentistas.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbDentistas.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbDentistas.getValueAt(filaSeleccionada, 3).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(CorreoDeTB);
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
                PreparedStatement updateUser = conexion.prepareStatement("update tbDentistas set nombre= ?, edad = ?, peso = ?, correo = ? where UUID_paciente = ?");

                updateUser.setString(1, getNombreDentista());
                updateUser.setInt(2,getEdadDentista());
                updateUser.setFloat(3,getPesoDentista());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
        
        
      public void limpiar(frmDentista vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
      
      
      public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Dentista", "Nombre", "Edad", "Peso", "Correo"});
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM tbDentista WHERE Nombre LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Dentista"), 
                    rs.getString("Nombre"), 
                    rs.getInt("Edad"), 
                    rs.getString("Peso"),
                    rs.getString("Correo")});
                
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
 
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
    
}

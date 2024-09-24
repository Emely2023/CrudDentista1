/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Dentistas;
import Vista.frmDentista;
import java.util.HashSet;
/**
 *
 * @author Estudiante
 */
public class ctrlDentista implements MouseListener, KeyListener{

//3- Heredar de la clase que detecta las acciones
    
    //1- Mandar a llamar a las otras capas (modelo y vista)
    private Dentistas Modelo;
    private frmDentista Vista;

            //2- Crear el constructor
    public ctrlDentista(Dentistas modelo, frmDentista vista){
        this.Modelo = modelo;
        this.Vista = vista;

        vista.btnAgregar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbDentistas);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbDentistas.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    
    }

        @Override
        public void keyTyped(KeyEvent e) {
         
        if(e.getSource() == Vista.txtBuscar){
            Modelo.Buscar(Vista.jtbDentistas, Vista.txtBuscar);
        }
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    if(e.getSource() == Vista.btnAgregar){
            Modelo.setNombreDentista(Vista.txtNombre.getText());
            Modelo.setEdadDentista(Integer.parseInt(Vista.txtEdad.getText()));
            Modelo.setPesoDentista(Float.parseFloat(Vista.txtPeso.getText()));
            Modelo.setCorreo(Vista.txtCorreo.getText());
            
            Modelo.Guardar();
            Modelo.Mostrar(Vista.jtbDentistas);
             }
        
        if(e.getSource() == Vista.btnEliminar){
            Modelo.Eliminar(Vista.jtbDentistas);
            Modelo.Mostrar(Vista.jtbDentistas);
        }
        
        if(e.getSource() == Vista.jtbDentistas){
            Modelo.cargarDatosTabla(Vista);
        }
        
        if(e.getSource() == Vista.btnActualizar){
            Modelo.setNombreDentista(Vista.txtNombre.getText());
            Modelo.setEdadDentista(Integer.parseInt(Vista.txtEdad.getText()));
            Modelo.setPesoDentista(Float.parseFloat(Vista.txtPeso.getText()));
            Modelo.setCorreo(Vista.txtCorreo.getText());

          
            Modelo.Actualizar(Vista.jtbDentistas);
            Modelo.Mostrar(Vista.jtbDentistas);
        }
        
        if(e.getSource() == Vista.btnLimpiar){
            Modelo.limpiar(Vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
       
}

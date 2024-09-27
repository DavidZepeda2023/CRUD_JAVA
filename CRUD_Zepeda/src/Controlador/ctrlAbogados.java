package Controlador;

import Modelo.Abogado;
import Vista.FrmAbogados;
import java.awt.event.MouseEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ctrlAbogados implements MouseListener {
    
    private Abogado modelo;
    private FrmAbogados vista;
    
    public ctrlAbogados(Abogado modelo, FrmAbogados vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Registrar el controlador como listener de los eventos de ratón
        vista.btnagregar.addMouseListener(this);   
        vista.btneliminar.addMouseListener(this);
        vista.btnactualizar.addMouseListener(this);
        vista.btnlimpiar.addMouseListener(this);
        vista.jtbAbogado.addMouseListener(this);
        modelo.Mostrar(vista.jtbAbogado);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnagregar) {
            modelo.setNombre(vista.txtnombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtedad.getText()));
            modelo.setPeso(Integer.parseInt(vista.txtpeso.getText()));
            modelo.setCorreo(vista.txtcorreo.getText());
            modelo.Guardar();
            modelo.Mostrar(vista.jtbAbogado);
        }

        if (e.getSource() == vista.btneliminar) {
            modelo.Eliminar(vista.jtbAbogado);
            modelo.Mostrar(vista.jtbAbogado);
        }

        if (e.getSource() == vista.jtbAbogado) {
            modelo.cargarDatosTabla(vista);
        }

        if (e.getSource() == vista.btnactualizar) {
            modelo.setNombre(vista.txtnombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtedad.getText()));
            modelo.setPeso(Integer.parseInt(vista.txtpeso.getText()));
            modelo.setCorreo(vista.txtcorreo.getText());
            modelo.Actualizar(vista.jtbAbogado);
            modelo.Mostrar(vista.jtbAbogado);
        }

        if (e.getSource() == vista.btnlimpiar) {
            modelo.limpiar(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

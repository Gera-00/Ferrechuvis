package org.upemor.ferrechuvis.view.usuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.upemor.ferrechuvis.model.entity.Usuarios;
import org.upemor.ferrechuvis.view.auth.Login;
import org.upemor.ferrechuvis.view.components.ImagenUtils;
import org.upemor.ferrechuvis.view.components.Pantalla;
import org.upemor.ferrechuvis.view.panels.inicio.PanelInicio;
import org.upemor.ferrechuvis.view.panels.productos.PanelProductos;
import org.upemor.ferrechuvis.view.panels.proveedores.PanelProveedores;

public class PrincipalAdministrador extends Pantalla{
    // Componenetes a utilizar
    private JLabel lblLogo, lblTitulo;
    private JButton btnCerrarSesion;
    private JButton btnInicio, btnProductos, btnProveedores, btnPedidos, btnEmpleados;
    JPanel panelCentral;

    Usuarios usuario;


    public PrincipalAdministrador(Usuarios usuario){
        super("Inicio - Ferrechuvis", 1200, 700);
        this.usuario = usuario;
        //Inicializamos en Panel de Inicio por defecto
            PanelInicio pi = new PanelInicio(usuario);
            JPanel panelInicio = pi.crearPanel();
            navegacionUsuario(1);
            cambiarPanel(panelInicio);
    }

    @Override
    protected LayoutManager tipoPanel(){
        return new BorderLayout();
    }

    protected void initSpecificComponents(){
        JPanel header = crearHeader();
        JPanel panelUsuario = crearPanelUsuario();

        panelCentral = new JPanel(new BorderLayout());

        
        panelPrincipal.add(header, BorderLayout.NORTH);
        panelPrincipal.add(panelUsuario, BorderLayout.WEST);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
    }

    
    //METODOS PARA CONSTRUIR TODOS LOS PANELES Y ELEMENTOS QUE LLEVAN EL PANEL PRINCIPAL
    private JPanel crearHeader(){
        JPanel header = new JPanel(new BorderLayout());
            header.setBackground(new Color(0x232322));
        
        lblLogo = new JLabel();
        ImagenUtils.configurarLabelConImagen(lblLogo, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\img\\logo.png", 80, 80);
        
        lblTitulo = new JLabel("Tlapalería Ferrechuvis");
            lblTitulo.setForeground(new Color(0xFF3F0F));
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        btnCerrarSesion = new JButton("Cerrar Sesión");
            btnCerrarSesion.setBackground(new Color(0x303337));
            btnCerrarSesion.setForeground(Color.WHITE);
            btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 12));
            btnCerrarSesion.setFocusPainted(false);
            btnCerrarSesion.setBorderPainted(false);
            btnCerrarSesion.setContentAreaFilled(true);
            btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            // Efectos hover para botón cerrar sesión
            btnCerrarSesion.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btnCerrarSesion.setBackground(new Color(0xFF3F0F)); // Color naranja hover
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    btnCerrarSesion.setBackground(new Color(0x303337)); // Color original
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    btnCerrarSesion.setBackground(new Color(0xCC3300)); // Más oscuro al presionar
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    btnCerrarSesion.setBackground(new Color(0xFF3F0F)); // Volver al hover
                }
            });
            

        header.add(lblLogo, BorderLayout.WEST);
        header.add(lblTitulo, BorderLayout.CENTER);
        header.add(btnCerrarSesion, BorderLayout.EAST);
        
        return header;
    }

    private JPanel crearPanelUsuario(){
        JPanel panelUsuario = new JPanel(new GridBagLayout());
            panelUsuario.setBackground(new Color(0x353535));
            panelUsuario.setPreferredSize(new Dimension(200, 0));
            panelUsuario.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 20, 10, 20);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            btnInicio = crearBoton("Inicio",true);
            btnProductos = crearBoton("Productos",false);
            btnPedidos = crearBoton("Ventas", false);
            btnProveedores = crearBoton("Proveedores", false);
            btnEmpleados = crearBoton("Empleados", false);

            gbc.gridy = 0;
            panelUsuario.add(btnInicio, gbc);
            gbc.gridy = 1;
            panelUsuario.add(btnProductos, gbc);
            gbc.gridy = 2;
            panelUsuario.add(btnPedidos, gbc);
            gbc.gridy = 3;
            panelUsuario.add(btnProveedores, gbc);
            gbc.gridy = 4;
            panelUsuario.add(btnEmpleados, gbc);

            //Espacios para empujar los btn hacia arriba
            gbc.gridy=5;
            gbc.weighty=1.0;
            panelUsuario.add(new JLabel(), gbc);

            return panelUsuario;
        }
        
        private JButton crearBoton(String nombre, boolean seleccionado){
            JButton boton = new JButton(nombre);
            boton.setForeground(Color.WHITE);
            boton.setFont(new Font("Arial", Font.BOLD, 14));
            boton.setHorizontalAlignment(SwingConstants.LEFT);
            boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            boton.setFocusPainted(false);
            boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            if (seleccionado) {
                boton.setBackground(new Color(0xFF3F0F));
            }else{
                boton.setBackground(new Color(0x353535));
            }
            
            // Efectos hover para botones de navegación
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!estaSeleccionado(boton)) {
                        boton.setBackground(new Color(0x555555)); // Gris más claro en hover
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!estaSeleccionado(boton)) {
                        boton.setBackground(new Color(0x353535)); // Volver al color original
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!estaSeleccionado(boton)) {
                        boton.setBackground(new Color(0x666666)); // Más claro al presionar
                    }
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (!estaSeleccionado(boton)) {
                        boton.setBackground(new Color(0x555555)); // Volver al hover
                    }
                }
            });
            
            return boton;
        }
        
        // Método auxiliar para verificar si un botón está seleccionado
        private boolean estaSeleccionado(JButton boton) {
            Color colorSeleccionado = new Color(0xFF3F0F);
            return boton.getBackground().equals(colorSeleccionado);
        }

    protected void setupEventListeners(){
        btnInicio.addActionListener(e->{
            PanelInicio pi = new PanelInicio(usuario);
            JPanel panelInicio = pi.crearPanel();
            navegacionUsuario(1);
            cambiarPanel(panelInicio);
        });
        
        btnProductos.addActionListener(e->{
            PanelProductos pp = new PanelProductos();
            try {
                JPanel panelProductos = pp.crearPanel();
                navegacionUsuario(2);
                cambiarPanel(panelProductos);
            } catch (Exception ex) {
                ex.printStackTrace();
                // Optionally, show an error dialog to the user
            }
        });
        btnPedidos.addActionListener(e->{
            navegacionUsuario(3);
        });
        btnProveedores.addActionListener(e->{
            PanelProveedores pp = new PanelProveedores();
            try {
                JPanel panelProveedores = pp.crearPanel();
                navegacionUsuario(4);
                cambiarPanel(panelProveedores);
            } catch (Exception ex) {
                ex.printStackTrace();
                // Optionally, show an error dialog to the user
            }
        });
        btnEmpleados.addActionListener(e->{navegacionUsuario(5);});
        btnCerrarSesion.addActionListener(e->{
            dispose();
            new Login().setVisible(true);
        });
    }

    private void cambiarPanel(JPanel nuevoPanel){
        panelCentral.removeAll();
        panelCentral.add(nuevoPanel, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void navegacionUsuario(int opc){
        // Resetear todos los botones a color normal primero
        JButton[] botones = {btnInicio, btnProductos, btnPedidos, btnProveedores, btnEmpleados};
        for (JButton boton : botones) {
            boton.setBackground(new Color(0x353535));
        }
        
        // Seleccionar el botón correspondiente
        switch (opc) {
            case 1:
                btnInicio.setBackground(new Color(0xFF3F0F));
                break;
            case 2:
                btnProductos.setBackground(new Color(0xFF3F0F));
                break;
            case 3:
                btnPedidos.setBackground(new Color(0xFF3F0F));
                break;
            case 4:
                btnProveedores.setBackground(new Color(0xFF3F0F));
                break;
            case 5:
                btnEmpleados.setBackground(new Color(0xFF3F0F));
                break;
            default:
                break;
        }
    }

}

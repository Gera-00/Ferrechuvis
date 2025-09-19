package org.upemor.ferrechuvis.view.auth;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.upemor.ferrechuvis.controller.ControllerUsuarios;
import org.upemor.ferrechuvis.model.entity.Usuarios;
import org.upemor.ferrechuvis.view.components.Pantalla;
import org.upemor.ferrechuvis.view.usuarios.PrincipalAdministrador;

public class Login extends Pantalla{

    private JPanel panelGris;
    private JLabel lblTitulo, lblUsuario, lblPassword;
    private JTextField txtUsuario;
    private JPasswordField txtPasswordField;
    private JButton btnIngresar;

    public Login(){
        super("Inicio de Sesión - Ferrechuvis", 600, 500, false);
    }

    @Override
    protected LayoutManager tipoPanel(){
        return new GridBagLayout();
    }

    protected void initSpecificComponents(){
        //Configuracion Panel Principal
        panelPrincipal.setBackground(Color.DARK_GRAY);

        //Configuacion PanelGris
        panelGris = new JPanel(new GridBagLayout());
            panelGris.setBackground(new Color(0x232322));
            panelGris.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
            panelGris.setPreferredSize(new Dimension(350, 400));
        
        //Configuracion de Componentes
        lblTitulo = new JLabel("Ferrechuvis", SwingConstants.CENTER);
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));

        lblUsuario = new JLabel("Usuario");
            lblUsuario.setForeground(Color.WHITE);
            lblUsuario.setFont(new Font("Arial", Font.BOLD,18));
            txtUsuario = new JTextField(15);
            txtUsuario.setFont(new Font("Arial", Font.BOLD,18));
            txtUsuario.setHorizontalAlignment(JTextField.CENTER);
            
            lblPassword = new JLabel("Contraseña");
            lblPassword.setForeground(Color.WHITE);
            lblPassword.setFont(new Font("Arial", Font.BOLD,18));
        txtPasswordField = new JPasswordField(15);
            txtPasswordField.setFont(new Font("Arial", Font.BOLD,18));
            txtPasswordField.setHorizontalAlignment(JTextField.CENTER);

        //Configuración de Botón
        btnIngresar = new JButton("Iniciar Sesión");
            btnIngresar.setBackground(new Color(0x303337));
            //btnIngresar.setBackground(new Color(0x353535));
            btnIngresar.setForeground(Color.WHITE);
            btnIngresar.setFont(new Font("Arial", Font.BOLD, 18));
            btnIngresar.setFocusPainted(false);
            btnIngresar.setBorderPainted(false);
            btnIngresar.setContentAreaFilled(true);

        //Título con espacio superior personalizado
        GridBagConstraints gbcTitulo = crearRestricciones(0, 0, 2, 1);
        gbcTitulo.insets = new Insets(10, 5, 40, 5); // Más espacio inferior para separar del resto
        panelGris.add(lblTitulo, gbcTitulo);
        
        panelGris.add(lblUsuario, crearRestricciones(0,1,1,1));
        panelGris.add(txtUsuario,crearRestricciones(0, 2, 2, 1));
        panelGris.add(lblPassword,crearRestricciones(0, 3, 1, 1));
        panelGris.add(txtPasswordField,crearRestricciones(0, 4, 2, 1));
        
        //Crear restricciones personalizadas para el botón centrado
        GridBagConstraints gbcBoton = crearRestricciones(0, 5, 2, 1);
        gbcBoton.fill = GridBagConstraints.NONE; // No llenar toda la celda
        gbcBoton.anchor = GridBagConstraints.CENTER; // Centrar el botón
        panelGris.add(btnIngresar, gbcBoton);

        //Configuración para centrar el Panel Gris en el Panel Principal
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        panelPrincipal.add(panelGris,gbc);
    }
    
    protected void setupEventListeners(){
        btnIngresar.addActionListener(e->{
            String usuario = txtUsuario.getText();
            String password = new String(txtPasswordField.getPassword());
            try {
                validacion(usuario, password);
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar iniciar sesión.");
            }
        });

        // Evento Enter en campo usuario
        txtUsuario.addActionListener(e->{
            btnIngresar.doClick();
        });
        // Evento Enter en campo contraseña
        txtPasswordField.addActionListener(e->{
            btnIngresar.doClick();
        });
    }


    public void validacion(String usuario, String password) throws Exception {
        ControllerUsuarios cu = new ControllerUsuarios();
        Usuarios user = cu.login(usuario, password);
        if (user != null) {
            // Abrir pantalla principal (ajusta el nombre de la clase según tu proyecto)
            ocultar();
            new PrincipalAdministrador(user).setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }
    

}

package org.upemor.ferrechuvis.view.panels.proveedores;

import org.upemor.ferrechuvis.controller.ControllerProveedores;
import org.upemor.ferrechuvis.model.entity.Proveedores;
import org.upemor.ferrechuvis.view.components.Pantalla;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormularioAgregar extends Pantalla{
    private JLabel lblNombre, lblTelefono, lblDireccion, lblEmail;
    private JTextField txtNombre, txtTelefono, txtEmail;
    private JTextArea txtDireccion;
    private JButton btnAgregar, btnCancelar;
    private ControllerProveedores cp;
    private PanelProveedores panelProveedores;

    public FormularioAgregar(PanelProveedores panelProveedores){
        super("Agregar Administrador - Ferrechuvis", 450, 400,true);
        this.panelProveedores = panelProveedores;
        setLocationRelativeTo(null); // Centrar ventana
        try {
            cp = new ControllerProveedores();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected LayoutManager tipoPanel(){
        return new GridBagLayout();
    }

    protected void initSpecificComponents(){
        panelPrincipal.setBackground(Color.DARK_GRAY);

    lblNombre = new JLabel("Nombre:");
    lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
    lblNombre.setForeground(Color.WHITE);
        txtNombre = new JTextField(15);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNombre.setForeground(Color.BLACK);

    lblTelefono = new JLabel("Teléfono:");
    lblTelefono.setFont(new Font("Arial", Font.BOLD, 16));
    lblTelefono.setForeground(Color.WHITE);
        txtTelefono = new JTextField(15);
        txtTelefono.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTelefono.setForeground(Color.BLACK);

    lblDireccion = new JLabel("Dirección:");
    lblDireccion.setFont(new Font("Arial", Font.BOLD, 16));
    lblDireccion.setForeground(Color.WHITE);
        txtDireccion = new JTextArea(3, 15);
        txtDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDireccion.setForeground(Color.BLACK);
        txtDireccion.setLineWrap(true);
        txtDireccion.setWrapStyleWord(true);

    lblEmail = new JLabel("Email:");
    lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
    lblEmail.setForeground(Color.WHITE);
        txtEmail = new JTextField(15);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEmail.setForeground(Color.BLACK);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.setBackground(new Color(0xFF3F0F));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBackground(new Color(0x888888));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelPrincipal.add(lblTelefono, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelPrincipal.add(lblDireccion, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelPrincipal.add(lblEmail, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnAgregar, gbc);

        gbc.gridy++;
        panelPrincipal.add(btnCancelar, gbc);

        // Acción para cerrar al cancelar
    }
    protected void setupEventListeners(){



        btnAgregar.addActionListener(e->{
            Proveedores proveedor = new Proveedores();
            proveedor.setNombre(txtNombre.getText());
            proveedor.setTelefono(txtTelefono.getText());
            proveedor.setDireccion(txtDireccion.getText());
            proveedor.setEmail(txtEmail.getText());

            try {
                boolean resp = cp.guardar(proveedor);
                if (resp) {
                    JOptionPane.showMessageDialog(this, "Proveedor guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    if (panelProveedores != null) {
                        panelProveedores.actualizarTabla(cp.getAll());
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo guardar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
    
}

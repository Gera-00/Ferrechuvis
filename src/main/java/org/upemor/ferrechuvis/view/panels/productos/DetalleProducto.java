package org.upemor.ferrechuvis.view.panels.productos;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import org.upemor.ferrechuvis.model.entity.Productos;
import org.upemor.ferrechuvis.view.components.ImagenUtils;


public class DetalleProducto extends JDialog {
    public DetalleProducto(Frame parent, Productos producto) {
        super(parent, true);
        setSize(650, 650);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        // Código
        JLabel lblCodigo = new JLabel("Codigo: " + producto.getCodigo(), SwingConstants.CENTER);
        lblCodigo.setFont(new Font("Arial", Font.ITALIC, 15));
        panel.add(lblCodigo, crearRestriccionesDetalle(0, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 2, 0), 0));

        // Nombre
        JLabel lblNombre = new JLabel(producto.getNombre(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(lblNombre, crearRestriccionesDetalle(1, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 10, 0), 0));

        // Imagen (más pequeña)
        JLabel lblImagen = new JLabel();
        ImagenUtils.configurarLabelConImagen(lblImagen, producto.getLink_imagen(), 100, 100);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblImagen, crearRestriccionesDetalle(2, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 10, 0), 0));

        // Precio
        JLabel lblPrecio = new JLabel("$" + String.format("%.0f", producto.getPrecio()), SwingConstants.CENTER);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 26));
        lblPrecio.setForeground(Color.RED);
        panel.add(lblPrecio, crearRestriccionesDetalle(3, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 5, 0), 0));

        // Stock
        JLabel lblStock = new JLabel(producto.getStock() + " piezas Disponibles", SwingConstants.CENTER);
        lblStock.setFont(new Font("Arial", Font.PLAIN, 15));
        lblStock.setForeground(new Color(150, 150, 150));
        panel.add(lblStock, crearRestriccionesDetalle(4, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 10, 0), 0));

        // Descripción en JTextArea con scroll
        JTextArea txtDescripcion = new JTextArea(producto.getDescripcion());
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 15));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(new Color(245, 245, 245));
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setPreferredSize(new Dimension(500, 120));
        panel.add(scrollDesc, crearRestriccionesDetalle(5, GridBagConstraints.BOTH, new Insets(2, 20, 15, 20), 1.0));

        // Botón agregar
        JButton btnAgregar = new JButton("AGREGAR +");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregar.setBackground(new Color(50, 50, 50));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        panel.add(btnAgregar, crearRestriccionesDetalle(6, GridBagConstraints.NONE, new Insets(10, 0, 20, 0), 0));

        setContentPane(panel);
    }

    /**
     * Crea restricciones para los elementos de DetalleProducto.
     * @param y posición vertical (gridy)
     * @param fill tipo de fill (GridBagConstraints.HORIZONTAL, BOTH, NONE...)
     * @param insets márgenes
     * @param weighty peso vertical (para scroll)
     * @return GridBagConstraints configurado
     */
    protected GridBagConstraints crearRestriccionesDetalle(int y, int fill, Insets insets, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = fill;
        gbc.insets = insets;
        gbc.weightx = 1.0;
        gbc.weighty = weighty;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }
}

package org.upemor.ferrechuvis.view.panels.productos;

import org.upemor.ferrechuvis.model.entity.Productos;
import org.upemor.ferrechuvis.controller.ControllerProductos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormularioRegistrarProducto extends JDialog {
    private JTextField txtCodigo, txtNombre, txtCategoriaId, txtUnidadMedida, txtStock, txtPrecio;
    private JTextArea txtDescripcion;
    private JLabel lblImagen;
    private File imagenSeleccionada = null;

    public FormularioRegistrarProducto(Frame parent) {
        super(parent, "Registrar Producto", true);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        panel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(20);
        panel.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        panel.add(new JScrollPane(txtDescripcion), gbc);

    gbc.gridx = 0; gbc.gridy++;
    panel.add(new JLabel("ID Categoría:"), gbc);
    gbc.gridx = 1;
    txtCategoriaId = new JTextField(20);
    panel.add(txtCategoriaId, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Unidad de Medida:"), gbc);
        gbc.gridx = 1;
        txtUnidadMedida = new JTextField(20);
        panel.add(txtUnidadMedida, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1;
        txtStock = new JTextField(20);
        panel.add(txtStock, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        txtPrecio = new JTextField(20);
        panel.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Imagen:"), gbc);
        gbc.gridx = 1;
        JPanel panelImg = new JPanel(new BorderLayout());
        lblImagen = new JLabel("Sin imagen");
        JButton btnSeleccionar = new JButton("Seleccionar...");
        btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();
            }
        });
        panelImg.add(lblImagen, BorderLayout.CENTER);
        panelImg.add(btnSeleccionar, BorderLayout.EAST);
        panel.add(panelImg, gbc);

        // Botón guardar
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
        panel.add(btnGuardar, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void seleccionarImagen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            imagenSeleccionada = chooser.getSelectedFile();
            lblImagen.setText(imagenSeleccionada.getName());
        }
    }

    private void guardarProducto() {
        try {
            // Copiar imagen si se seleccionó
            String rutaDestino = "";
            if (imagenSeleccionada != null) {
                File carpetaDestino = new File("src/main/java/org/upemor/ferrechuvis/resources/img/");
                if (!carpetaDestino.exists()) carpetaDestino.mkdirs();
                rutaDestino = carpetaDestino.getPath() + File.separator + imagenSeleccionada.getName();
                Files.copy(imagenSeleccionada.toPath(), new File(rutaDestino).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            Productos producto = new Productos();
            producto.setCodigo(txtCodigo.getText());
            producto.setNombre(txtNombre.getText());
            producto.setDescripcion(txtDescripcion.getText());
            producto.setId_categoria(Integer.parseInt(txtCategoriaId.getText()));
            producto.setUnidad_medida(txtUnidadMedida.getText());
            producto.setStock(Integer.parseInt(txtStock.getText()));
            producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
            producto.setLink_imagen(rutaDestino);

            ControllerProductos cp = new ControllerProductos();
            cp.guardar(producto);

            JOptionPane.showMessageDialog(this, "Producto registrado correctamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

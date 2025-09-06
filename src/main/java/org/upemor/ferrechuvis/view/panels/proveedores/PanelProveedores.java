package org.upemor.ferrechuvis.view.panels.proveedores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.upemor.ferrechuvis.view.components.ImagenUtils;

public class PanelProveedores {
    
    public JPanel crearPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JPanel header = crearHeader();
        JPanel contenidoTabla = crearPanelProveedores();

        panel.add(header, BorderLayout.NORTH);
        panel.add(contenidoTabla, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearHeader(){
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Botones de acción con efectos hover
        JButton btnAgregar = new JButton("Agregar");
        configurarBotonAccion(btnAgregar, new Color(74, 54, 107));
        
        JButton btnEditar = new JButton("Editar");
        configurarBotonAccion(btnEditar, new Color(255, 153, 51));
        
        JButton btnEliminar = new JButton("Eliminar");
        configurarBotonAccion(btnEliminar, new Color(204, 0, 51));

        // Buscador mejorado
        JTextField buscador = new JTextField(15);
        buscador.setFont(new Font("Arial", Font.PLAIN, 16));
        buscador.setPreferredSize(new Dimension(250, 35));

        JButton btnBuscar = new JButton();
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(true);
        btnBuscar.setBackground(new Color(0xFF3F0F));
        btnBuscar.setPreferredSize(new Dimension(45, 35));
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImagenUtils.configurarButtonConImagen(btnBuscar, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\lupa.png", 17, 17);

        header.add(btnAgregar);
        header.add(btnEditar);
        header.add(btnEliminar);
        header.add(buscador);
        header.add(btnBuscar);

        return header;
    }
    
    private void configurarBotonAccion(JButton boton, Color colorBase) {
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(true);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(100, 35));
        boton.setBackground(colorBase);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Efectos hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorBase.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorBase);
            }
        });
    }

    private JPanel crearPanelProveedores(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));

        // Crear modelo de tabla con las columnas requeridas
        String[] columnas = {"ID", "Nombre", "Teléfono", "Email", "Dirección"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        // Datos de ejemplo para mostrar la funcionalidad
        Object[][] datosEjemplo = {
            {1, "Ferreterías del Norte S.A.", "+52 777 123 4567", "ventas@ferrenorte.mx", "Av. Industrial 123, Cuernavaca"},
            {2, "Distribuidora Herramientas MX", "+52 777 987 6543", "contacto@herramientasmx.com", "Calle Comercio 456, Temixco"},
            {3, "Suministros Industriales López", "+52 777 555 0123", "info@suministroslopez.mx", "Blvd. Universidad 789, Jiutepec"},
            {4, "Tornillería y Herrajes Express", "+52 777 111 2222", "pedidos@tornilleriaexpress.mx", "Zona Industrial Norte 321"},
            {5, "Materiales de Construcción García", "+52 777 333 4444", "garcia@materiales.mx", "Carretera Federal 95, Km 12"}
        };

        // Agregar datos de ejemplo al modelo
        for (Object[] fila : datosEjemplo) {
            modeloTabla.addRow(fila);
        }

        // Crear tabla
        JTable tabla = new JTable(modeloTabla);
        
        // Configurar apariencia de la tabla
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(30);
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setSelectionBackground(new Color(255, 63, 15, 50)); // Color naranja con transparencia
        tabla.setSelectionForeground(Color.BLACK);
        
        // Configurar header de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(74, 54, 107));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));
        
        // Configurar ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nombre
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);  // Teléfono
        tabla.getColumnModel().getColumn(3).setPreferredWidth(180);  // Email
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);  // Dirección

        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

}

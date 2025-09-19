package org.upemor.ferrechuvis.view.panels.proveedores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.upemor.ferrechuvis.controller.ControllerProveedores;
import org.upemor.ferrechuvis.model.entity.Proveedores;
import org.upemor.ferrechuvis.view.components.ImagenUtils;

public class PanelProveedores{
    private ControllerProveedores cp;
    private List<Proveedores> data;
    private DefaultTableModel modeloTabla; // modelo de la tabla para actualizar
    private JTable tabla; // referencia a la tabla
    private JTextField buscador; // campo de búsqueda
    private JButton btnAgregar, btnEditar, btnEliminar;
    private Proveedores proveedor;

    public JPanel crearPanel()throws Exception{
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        cp = new ControllerProveedores();
        data = cp.getAll();

        JPanel header = crearHeader();
        JPanel contenidoTabla = crearPanelProveedores();
        setupEventListeners();

        panel.add(header, BorderLayout.NORTH);
        panel.add(contenidoTabla, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearHeader(){
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Botones de acción con efectos hover
        btnAgregar = new JButton("Agregar");
        configurarBotonAccion(btnAgregar, new Color(74, 54, 107));

        btnEditar = new JButton("Editar");
        configurarBotonAccion(btnEditar, new Color(255, 153, 51));
        
        btnEliminar = new JButton("Eliminar");
        configurarBotonAccion(btnEliminar, new Color(204, 0, 51));

        // Buscador mejorado
        buscador = new JTextField(15);
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


        // Acción de búsqueda (usada por botón y por Enter)
        ActionListener buscarAction = e -> {
            try {
                buscar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        btnBuscar.addActionListener(buscarAction);
        buscador.addActionListener(buscarAction); // Buscar al presionar Enter

        header.add(btnAgregar);
        header.add(btnEditar);
        header.add(btnEliminar);
        header.add(buscador);
        header.add(btnBuscar);

        return header;
    }

    private void actualizarTabla(List<Proveedores> proveedores) {
        modeloTabla.setRowCount(0); // Limpiar la tabla
        for (Proveedores proveedor : proveedores) {
            modeloTabla.addRow(new Object[] {
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getTelefono(),
                proveedor.getEmail(),
                proveedor.getDireccion()
            });
        }
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

    private JPanel crearPanelProveedores()throws Exception{
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));

        // Crear modelo de tabla con las columnas requeridas
        String[] columnas = {"ID", "Nombre", "Teléfono", "Email", "Dirección"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        // Agregar datos reales de la base de datos al modelo
        for (Proveedores proveedor : data) {
            modeloTabla.addRow(new Object[] {
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getTelefono(),
                proveedor.getEmail(),
                proveedor.getDireccion()
            });
        }

        // Crear tabla
        tabla = new JTable(modeloTabla);

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

    public void buscar()throws Exception{
        String texto = buscador.getText().trim();
        List<Proveedores> resultados;
        try {
            if (texto.isEmpty()) {
                resultados = cp.getAll();
            } else {
                resultados = cp.getByName(texto);
            }
            actualizarTabla(resultados);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupEventListeners(){
        btnAgregar.addActionListener(e -> {
            FormularioAgregar formulario = new FormularioAgregar();
            formulario.mostrar();
        });

        btnEditar.addActionListener(e -> {
            proveedor = getProveedorSeleccionado();
            if (proveedor != null) {
                FormularioEditar formulario = new FormularioEditar(proveedor);
                formulario.mostrar();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un proveedor para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e->{
            proveedor = getProveedorSeleccionado();
            if (proveedor != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar al proveedor?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        boolean resp = cp.delete(proveedor.getId());
                        if (resp) {
                            JOptionPane.showMessageDialog(null, "Proveedor eliminado exitosamente.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                            // Actualizar tabla después de eliminar
                            actualizarTabla(cp.getAll());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un proveedor para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

    }
    public Proveedores getProveedorSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) return null;
        Long id = Long.valueOf(modeloTabla.getValueAt(fila, 0).toString());
        String nombre = modeloTabla.getValueAt(fila, 1).toString();
        String telefono = modeloTabla.getValueAt(fila, 2).toString();
        String email = modeloTabla.getValueAt(fila, 3).toString();
        String direccion = modeloTabla.getValueAt(fila, 4).toString();
        Proveedores proveedor = new Proveedores();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);
        proveedor.setDireccion(direccion);
        return proveedor;
    }
    
}


package org.upemor.ferrechuvis.view.panels.productos;

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

import org.upemor.ferrechuvis.controller.ControllerProductos;
import org.upemor.ferrechuvis.model.entity.Productos;
import org.upemor.ferrechuvis.view.components.ImagenUtils;


public class CrudProductos{
    private ControllerProductos cp;
    private List<Productos> data;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField buscador;
    private JButton btnAgregar, btnEditar, btnEliminar, btnRegresar;
    private Productos producto;
    private Runnable onRegresar;

    public void setOnRegresar(Runnable callback) {
        this.onRegresar = callback;
    }

    public JPanel crearPanel()throws Exception{
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        cp = new ControllerProductos();
        data = cp.getAll();

        JPanel header = crearHeader();
        JPanel contenidoTabla = crearPanelProductos();
        setupEventListeners();

        panel.add(header, BorderLayout.NORTH);
        panel.add(contenidoTabla, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearHeader(){
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnAgregar = new JButton("Agregar");
        configurarBotonAccion(btnAgregar, new Color(74, 54, 107));

        btnEditar = new JButton("Editar");
        configurarBotonAccion(btnEditar, new Color(255, 153, 51));

        btnEliminar = new JButton("Eliminar");
        configurarBotonAccion(btnEliminar, new Color(204, 0, 51));

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
        ImagenUtils.configurarButtonConImagen(btnBuscar, "src/main/java/org/upemor/ferrechuvis/resources/icons/lupa.png", 17, 17);

        ActionListener buscarAction = e -> {
            try {
                buscar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        btnBuscar.addActionListener(buscarAction);
        buscador.addActionListener(buscarAction);

        // Botón regresar a la vista de productos (ahora a la izquierda)
        btnRegresar = new JButton();
        btnRegresar.setPreferredSize(new Dimension(40, 40));
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setContentAreaFilled(true);
        btnRegresar.setToolTipText("Regresar a productos");
        btnRegresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImagenUtils.configurarButtonConImagen(btnRegresar, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\flecha_regresar.png", 30, 30);
        
        btnRegresar.addActionListener(e -> {
            if (onRegresar != null) onRegresar.run();
        });

        // Usar BorderLayout para el header
        header.setLayout(new BorderLayout());
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.add(btnAgregar);
        panelCentro.add(btnEditar);
        panelCentro.add(btnEliminar);
        panelCentro.add(buscador);
        panelCentro.add(btnBuscar);

        header.add(btnRegresar, BorderLayout.WEST);
        header.add(panelCentro, BorderLayout.CENTER);

        return header;
    }

    public void actualizarTabla(List<Productos> productos) {
        modeloTabla.setRowCount(0);
        for (Productos producto : productos) {
            modeloTabla.addRow(new Object[] {
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getDescripcion()
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

    private JPanel crearPanelProductos()throws Exception{
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));

        String[] columnas = {"Código", "Nombre", "Precio", "Stock", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Productos producto : data) {
            modeloTabla.addRow(new Object[] {
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getDescripcion()
            });
        }

        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(30);
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setSelectionBackground(new Color(255, 63, 15, 50));
        tabla.setSelectionForeground(Color.BLACK);

    JTableHeader header = tabla.getTableHeader();
    header.setBackground(new Color(230, 230, 230)); // gris claro
    header.setForeground(Color.DARK_GRAY);
    header.setFont(new Font("Arial", Font.BOLD, 14));
    header.setPreferredSize(new Dimension(0, 40));

        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nombre
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);   // Precio
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);   // Stock
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);  // Descripción

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void buscar()throws Exception{
        String texto = buscador.getText().trim();
        List<Productos> resultados;
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
            java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(btnAgregar);
            FormularioRegistrarProducto formulario = new FormularioRegistrarProducto((java.awt.Frame) parent);
            formulario.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            producto = getProductoSeleccionado();
            if (producto != null) {
                // FormularioEditarProducto formulario = new FormularioEditarProducto(producto, this);
                // formulario.mostrar();
                JOptionPane.showMessageDialog(null, "Funcionalidad de editar producto aquí.", "Editar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e->{
            producto = getProductoSeleccionado();
            if (producto != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        boolean resp = cp.delete(producto.getId());
                        if (resp) {
                            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                            actualizarTabla(cp.getAll());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public Productos getProductoSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) return null;
        Long id = Long.valueOf(modeloTabla.getValueAt(fila, 0).toString());
        String nombre = modeloTabla.getValueAt(fila, 1).toString();
        double precio = Double.parseDouble(modeloTabla.getValueAt(fila, 2).toString());
        int stock = Integer.parseInt(modeloTabla.getValueAt(fila, 3).toString());
        String descripcion = modeloTabla.getValueAt(fila, 4).toString();
        Productos producto = new Productos();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setDescripcion(descripcion);
        return producto;
    }
}

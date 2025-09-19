package org.upemor.ferrechuvis.view.panels.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.upemor.ferrechuvis.controller.ControllerProductos;
import org.upemor.ferrechuvis.model.entity.Productos;
import org.upemor.ferrechuvis.view.components.ImagenUtils;


public class PanelProductos{

    private ControllerProductos cp;
    private List<Productos> data;
    private JTextField buscador;
    private JPanel panelPrincipal;

    public JPanel crearPanel()throws Exception{
        cp = new ControllerProductos();
        panelPrincipal = new JPanel(new BorderLayout());

        JPanel header = crearHeader();
        JPanel panelProductos = crearPanelProductos(cp.getAll());

        JScrollPane scrollPane = new JScrollPane(panelProductos);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panelPrincipal.add(header, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        return panelPrincipal;
    }

    private JPanel crearHeader(){
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel panelBuscador = new JPanel();
        panelBuscador.setBackground(Color.WHITE);

        buscador = new JTextField(25);
        buscador.setFont(new Font("Arial", Font.PLAIN, 16));
        buscador.setPreferredSize(new Dimension(300, 35));

        JButton btnBuscar = new JButton();
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(true);
        btnBuscar.setBackground(new Color(0xFF3F0F));
        btnBuscar.setPreferredSize(new Dimension(45, 35));
        ImagenUtils.configurarButtonConImagen(btnBuscar, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\lupa.png", 20, 20);

        btnBuscar.addActionListener(e->{
            try {
                buscar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Evento Enter en campo buscador
        buscador.addActionListener(e->{
            btnBuscar.doClick();
        });

        panelBuscador.add(buscador);
        panelBuscador.add(btnBuscar);

        header.add(panelBuscador, BorderLayout.CENTER);
        return header;
    }

    private JPanel crearPanelProductos(List<Productos> productosData){
        JPanel productos = new JPanel();
        productos.setBackground(Color.WHITE);
        productos.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        int columnas = 3;
        for(int i = 0; i < productosData.size(); i++){
            Productos obj = productosData.get(i);
            gbc.gridx = i % columnas;
            gbc.gridy = i / columnas;
            gbc.insets = new Insets(15, 15, 15, 15);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;

            JPanel tarjeta = crearTarjetaProductos(
                obj.getNombre(),
                "$" + String.format("%.2f", obj.getPrecio()),
                String.valueOf(obj.getStock()), obj.getLink_imagen()
            );

            productos.add(tarjeta, gbc);
        }

        return productos;
    }

    private JPanel crearTarjetaProductos(String nombre, String precio, String stock, String rutaImg){
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        tarjeta.setPreferredSize(new Dimension(200, 250));
        tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Efectos de mouse para interactividad
        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("Producto clickeado: " + nombre);
                // Aquí se puede abrir ventana de detalles del producto
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                tarjeta.setBackground(new Color(248, 249, 250));
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0xFF3F0F), 2),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                tarjeta.setBackground(Color.WHITE);
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
        });

        // Panel superior con imagen del producto
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setPreferredSize(new Dimension(160, 80));
        
        JLabel lblImagen = new JLabel();
        ImagenUtils.configurarLabelConImagen(lblImagen, rutaImg, 60, 60);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelImagen.add(lblImagen);

        // Panel de información del producto
        JPanel panelInfo = new JPanel(new GridBagLayout());
        panelInfo.setBackground(Color.WHITE);
        GridBagConstraints gbcInfo = new GridBagConstraints();
        
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblPrecio = new JLabel(precio);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 16));
        lblPrecio.setForeground(new Color(0xFF3F0F));
        lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblStock = new JLabel("Stock: " + stock);
        lblStock.setFont(new Font("Arial", Font.PLAIN, 12));
        lblStock.setForeground(new Color(100, 100, 100));
        lblStock.setHorizontalAlignment(SwingConstants.CENTER);
        
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        gbcInfo.insets = new Insets(5, 0, 5, 0);
        panelInfo.add(lblNombre, gbcInfo);
        
        gbcInfo.gridy = 1;
        panelInfo.add(lblPrecio, gbcInfo);
        
        gbcInfo.gridy = 2;
        gbcInfo.insets = new Insets(3, 0, 0, 0);
        panelInfo.add(lblStock, gbcInfo);
        
        tarjeta.add(panelImagen, BorderLayout.NORTH);
        tarjeta.add(panelInfo, BorderLayout.CENTER);
        
        return tarjeta;
    }


    public void buscar() throws Exception {
        String texto = buscador.getText().trim();
        List<Productos> resultados;

        if (texto.isEmpty()) {
            resultados = cp.getAll();
        } else {
            // Usamos un Set para evitar duplicados
            Set<Productos> resultadoSet = new HashSet<>();

            // Buscar por nombre
            resultadoSet.addAll(cp.getByName(texto));

            // Buscar por código
            resultadoSet.addAll(cp.getByCodigo(texto));

            // Si es numérico, buscar por ID
            try {
                int id = Integer.parseInt(texto);
                Productos prod = cp.getById(id);
                if (prod != null) {
                    resultadoSet.add(prod);
                }
            } catch (NumberFormatException ex) {
                // No es un número, ignorar búsqueda por ID
            }

            resultados = new ArrayList<>(resultadoSet);
        }

        JPanel panelProductos = crearPanelProductos(resultados);
        JScrollPane scrollPane = new JScrollPane(panelProductos);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panelPrincipal.remove(1); // Elimina el panel anterior (índice 1)
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
    
    protected GridBagConstraints crearRestricciones(int x, int y, int width, int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.weightx = 1.0;
        return gbc;
    }
}
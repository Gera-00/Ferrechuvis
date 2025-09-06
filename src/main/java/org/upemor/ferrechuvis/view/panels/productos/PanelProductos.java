package org.upemor.ferrechuvis.view.panels.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.upemor.ferrechuvis.view.components.ImagenUtils;

public class PanelProductos{
    
    public JPanel crearPanel(){
        JPanel panel =  new JPanel(new BorderLayout());

        JPanel header = crearHeader();
        JPanel panelProductos = crearPanelProductos();

        panel.add(header, BorderLayout.NORTH);
        panel.add(panelProductos, BorderLayout.CENTER);
        return panel;
    }
    

    private JPanel crearHeader(){
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Panel para centrar el buscador
        JPanel panelBuscador = new JPanel();
        panelBuscador.setBackground(Color.WHITE);
        
        JTextField buscador = new JTextField(25);
        buscador.setFont(new Font("Arial", Font.PLAIN, 16));
        buscador.setPreferredSize(new Dimension(300, 35));
        
        JButton btnBuscar = new JButton();
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(true);
        btnBuscar.setBackground(new Color(0xFF3F0F));
        btnBuscar.setPreferredSize(new Dimension(45, 35));
        ImagenUtils.configurarButtonConImagen(btnBuscar, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\lupa.png", 20, 20);
        
        panelBuscador.add(buscador);
        panelBuscador.add(btnBuscar);
        
        header.add(panelBuscador, BorderLayout.CENTER);
        return header;
    }

    private JPanel crearPanelProductos(){
        JPanel productos = new JPanel();
        productos.setBackground(Color.WHITE);
        
        // Crear GridBagLayout para control más preciso
        productos.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Lista de productos de ejemplo
        String[] nombresProductos = {
            "Taladro Percutor", 
            "Destornillador Set",
            "Martillo de Garra",
            "Sierra Circular",
            "Nivel de Burbuja",
            "Alicate Universal"
        };
        
        String[] preciosProductos = {
            "$1,250.00",
            "$450.00", 
            "$380.00",
            "$2,100.00",
            "$220.00",
            "$180.00"
        };
        
        String[] stockProductos = {
            "15",
            "25",
            "18",
            "8",
            "30",
            "22"
        };
        
        // Configurar grid 3x2 (3 columnas, 2 filas)
        int columnas = 3;
        for(int i = 0; i < nombresProductos.length; i++){
            gbc.gridx = i % columnas;  // Columna: 0, 1, 2, 0, 1, 2
            gbc.gridy = i / columnas;  // Fila: 0, 0, 0, 1, 1, 1
            gbc.insets = new Insets(15, 15, 15, 15);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            
            JPanel tarjeta = crearTarjetaProductos(
                nombresProductos[i], 
                preciosProductos[i], 
                stockProductos[i]
            );
            
            productos.add(tarjeta, gbc);
        }
        
        return productos;
    }

    private JPanel crearTarjetaProductos(String nombre, String precio, String stock){
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
        ImagenUtils.configurarLabelConImagen(lblImagen, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\stock.png", 60, 60);
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
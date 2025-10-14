package org.upemor.ferrechuvis.view.panels.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PanelVentas{

    public PanelVentas(){

    }

    public JPanel crearPanel(){
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(header(), BorderLayout.NORTH); 
        panel.add(body(), BorderLayout.CENTER); 

        return panel;
    }

    private JPanel header(){
        JPanel header = new JPanel(new GridLayout(1, 3, 15, 0));
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(20, 20, 20, 20));

        header.add(crearTarjetaEstadistica("TOTAL DE VENTAS", "$1500", new Color(0xE8E8E8)));
        header.add(crearTarjetaEstadistica("N° VENTAS", "4", new Color(0xE8E8E8)));
        header.add(crearTarjetaEstadistica("PEDIDOS PENDIENTES", "1", new Color(0xE8E8E8)));

        return header;
    }

    private JPanel crearTarjetaEstadistica(String titulo, String valor, Color backgroundColor){
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(backgroundColor);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xD0D0D0), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        tarjeta.setPreferredSize(new Dimension(200, 80));

        // Título
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblTitulo.setForeground(new Color(0x666666));
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);

        // Valor
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 24));
        lblValor.setForeground(Color.BLACK);
        lblValor.setHorizontalAlignment(SwingConstants.LEFT);

        tarjeta.add(lblTitulo, BorderLayout.NORTH);
        tarjeta.add(lblValor, BorderLayout.CENTER);

        return tarjeta;
    }
    
    private JPanel body(){
        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Color.WHITE);

        // Panel principal izquierdo con tabla de ventas
        JPanel leftPanel = crearPanelVentas();
        
        // Panel lateral derecho con pedidos
        JPanel rightPanel = crearPanelPedidos();

        body.add(leftPanel, BorderLayout.CENTER);
        body.add(rightPanel, BorderLayout.EAST);

        return body;
    }

    private JPanel crearPanelVentas(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(0, 20, 20, 10));

        // Header con botón Nueva Venta
        JPanel headerVentas = new JPanel(new BorderLayout());
        headerVentas.setBackground(Color.WHITE);
        headerVentas.setBorder(new EmptyBorder(0, 0, 15, 0));

        JButton btnNuevaVenta = new JButton("NUEVA VENTA");
        btnNuevaVenta.setFont(new Font("Arial", Font.BOLD, 12));
        btnNuevaVenta.setBackground(new Color(0xFF5500));
        btnNuevaVenta.setForeground(Color.WHITE);
        btnNuevaVenta.setPreferredSize(new Dimension(120, 35));
        btnNuevaVenta.setFocusPainted(false);
        btnNuevaVenta.setBorderPainted(false);
        btnNuevaVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        headerVentas.add(btnNuevaVenta, BorderLayout.WEST);

        // Título de la tabla
        JLabel lblTitulo = new JLabel("Ventas del día");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setBorder(new EmptyBorder(15, 0, 10, 0));

        // Tabla de ventas
        String[] columnas = {"Hora", "Cliente", "Precio total", "Estado"};
        Object[][] datos = {
            {"10:30", "Carlos", "$150.00", "●"},
            {"11:15", "Ana María", "$89.50", "●"},
            {"12:00", "Roberto", "$245.00", "●"},
            {"14:30", "Laura", "$67.25", "●"}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        JTable tabla = new JTable(model);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabla.setRowHeight(35);
        tabla.setGridColor(new Color(0xE0E0E0));
        tabla.setSelectionBackground(new Color(0xF0F8FF));

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xD0D0D0), 1));

        // Label con total
        JLabel lblTotal = new JLabel("Total: $1644");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTotal.setBorder(new EmptyBorder(10, 0, 0, 0));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(headerVentas, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(lblTitulo, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(lblTotal, BorderLayout.SOUTH);
        
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelPedidos(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F8F8));
        panel.setPreferredSize(new Dimension(250, 0));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título
        JLabel lblTitulo = new JLabel("Pedidos");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Subtítulo
        JLabel lblSubtitulo = new JLabel("1 Pedidos Pendientes");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Ejemplo de pedido
        JPanel pedido = new JPanel();
        pedido.setLayout(new BorderLayout());
        pedido.setBackground(Color.WHITE);
        pedido.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xD0D0D0), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblPedido = new JLabel("<html>Cliente: María García<br/>Fecha: 13/10/2025<br/>Total: $125.00</html>");
        lblPedido.setFont(new Font("SansSerif", Font.PLAIN, 11));

        pedido.add(lblPedido, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0xF8F8F8));
        topPanel.add(lblTitulo, BorderLayout.NORTH);
        topPanel.add(lblSubtitulo, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(pedido, BorderLayout.CENTER);

        return panel;
    }

    
}

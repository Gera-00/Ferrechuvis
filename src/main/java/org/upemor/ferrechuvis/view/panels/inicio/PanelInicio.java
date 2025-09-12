package org.upemor.ferrechuvis.view.panels.inicio;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.upemor.ferrechuvis.controller.ControllerPedidos;
import org.upemor.ferrechuvis.controller.ControllerProductos;
import org.upemor.ferrechuvis.model.entity.Usuarios;
import org.upemor.ferrechuvis.view.components.ImagenUtils;

public class PanelInicio {

    private Usuarios usuario;
    private ControllerProductos controllerProductos;
    private ControllerPedidos controllerPedidos;


    public PanelInicio(Usuarios usuario){
        this.usuario = usuario;
        try {
            controllerProductos = new ControllerProductos();
            controllerPedidos = new ControllerPedidos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public JPanel crearPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel header = crearHeader();
        JPanel estadisticas = crearEstadisticas();
        JPanel footer = crearFooter();

        panel.add(header, BorderLayout.NORTH);
        panel.add(estadisticas, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearHeader(){
        JPanel header = new JPanel(new BorderLayout());
            header.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
            header.setBackground(Color.WHITE);
        JLabel lblUsuario = new JLabel("Bienvenido, "+ usuario.getNombre());
            lblUsuario.setFont(new Font("Arial", Font.BOLD, 24));

        LocalDateTime tiempoActual = LocalDateTime.now();

        String fechaHoraActual = tiempoActual.format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy 'a las' HH:mm"));
        JLabel lblFecha = new JLabel(fechaHoraActual);
            lblFecha.setFont(new Font("Arial", Font.PLAIN, 16));

        header.add(lblUsuario, BorderLayout.WEST);
        header.add(lblFecha, BorderLayout.EAST);

        return header;
    }

    private JPanel crearEstadisticas(){
        JPanel panel = new JPanel(new GridLayout(2,2,20,20));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Obtener datos reales desde el controlador
        String stock = "0";
        String bajoInventario = "0";
        try {
            stock = String.valueOf(controllerProductos.countAll());
            bajoInventario = String.valueOf(controllerProductos.countProductosStockBajo());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ventas = "0";    // Puedes implementar la lógica después
        String pedidos = "0";   // Puedes implementar la lógica después

        JPanel panelStock = crearTarjetaEstadistica("stock.png", stock, "Productos en Stock");
        JPanel panelBajoInventario = crearTarjetaEstadistica("alerta.png", bajoInventario, "Productos bajos en Inventario");
        JPanel panelVentas = crearTarjetaEstadistica("pesos.png", ventas, "Ventas del día");
        JPanel panelPedidos = crearTarjetaEstadistica("pedidos.png", pedidos, "Pedidos pendientes");

        panel.add(panelStock);
        panel.add(panelBajoInventario);
        panel.add(panelVentas);
        panel.add(panelPedidos);

        return panel;
    }

    private JPanel crearTarjetaEstadistica(String nombreImagen, String numero, String texto){
        JPanel tarjeta = new JPanel(new BorderLayout());
            tarjeta.setBackground(new Color(0xF5F5F5));
            tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK,1),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                    )
                );

            JLabel lblIcono = new JLabel();
            ImagenUtils.configurarLabelConImagen(lblIcono, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\"+nombreImagen, 80, 80);

        lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel lblNumero = new JLabel(numero);
        lblNumero.setFont(new Font("Arial", Font.BOLD, 36));
        lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblTexto = new JLabel(texto);
        lblTexto.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTexto.setForeground(Color.GRAY);
        
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setOpaque(false);
        panelInfo.add(lblNumero, BorderLayout.CENTER);
        panelInfo.add(lblTexto, BorderLayout.NORTH);
        
        tarjeta.add(lblIcono, BorderLayout.WEST);
        tarjeta.add(panelInfo, BorderLayout.CENTER);
            
        return tarjeta;
    }

    private JPanel crearFooter(){
        
    JPanel acciones = new JPanel(new BorderLayout());
    acciones.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
    acciones.setBackground(Color.WHITE);

    JPanel botones = new JPanel(new GridLayout(1, 2, 20, 0));
    botones.setBackground(Color.WHITE);

    // Botón 1 - Estilo plano con efectos hover
    JPanel panelBtn1 = new JPanel(new BorderLayout());
    panelBtn1.setBackground(Color.WHITE);
    JButton btnNuevoProducto = new JButton("Registrar nuevo producto");
    btnNuevoProducto.setFont(new Font("Arial", Font.BOLD, 16));
    btnNuevoProducto.setIcon(ImagenUtils.prepararImagen("src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\mas.png", 24, 24));
    
    // Estilo inicial
    btnNuevoProducto.setFocusPainted(false);
    btnNuevoProducto.setBorderPainted(false);
    btnNuevoProducto.setContentAreaFilled(true);
    btnNuevoProducto.setBackground(new Color(0xE0E0E0)); // Gris claro
    btnNuevoProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    // Efectos de mouse para interactividad
    btnNuevoProducto.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            btnNuevoProducto.setBackground(new Color(0xFF3F0F)); // Color naranja de la marca
            btnNuevoProducto.setForeground(Color.WHITE);
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            btnNuevoProducto.setBackground(new Color(0xE0E0E0)); // Volver al gris original
            btnNuevoProducto.setForeground(Color.BLACK);
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            btnNuevoProducto.setBackground(new Color(0xCC3300)); // Más oscuro al presionar
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            btnNuevoProducto.setBackground(new Color(0xFF3F0F)); // Volver al hover
        }
    });
    
    panelBtn1.add(btnNuevoProducto, BorderLayout.CENTER);

    // Botón 2 - Estilo plano con efectos hover
    JPanel panelBtn2 = new JPanel(new BorderLayout());
    panelBtn2.setBackground(Color.WHITE);
    JButton btnReportes = new JButton("Generar reportes de venta");
    btnReportes.setFont(new Font("Arial", Font.BOLD, 16));
    btnReportes.setIcon(ImagenUtils.prepararImagen("src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\reporte.png", 24, 24));
    
    // Estilo inicial
    btnReportes.setFocusPainted(false);
    btnReportes.setBorderPainted(false);
    btnReportes.setContentAreaFilled(true);
    btnReportes.setBackground(new Color(0xE0E0E0)); // Gris claro
    btnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    // Efectos de mouse para interactividad
    btnReportes.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            btnReportes.setBackground(new Color(0xFF3F0F)); // Color naranja de la marca
            btnReportes.setForeground(Color.WHITE);
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            btnReportes.setBackground(new Color(0xE0E0E0)); // Volver al gris original
            btnReportes.setForeground(Color.BLACK);
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            btnReportes.setBackground(new Color(0xCC3300)); // Más oscuro al presionar
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            btnReportes.setBackground(new Color(0xFF3F0F)); // Volver al hover
        }
    });

    panelBtn2.add(btnReportes, BorderLayout.CENTER);

    botones.add(panelBtn1);
    botones.add(panelBtn2);

    acciones.add(botones, BorderLayout.CENTER);

    return acciones;
    }

}

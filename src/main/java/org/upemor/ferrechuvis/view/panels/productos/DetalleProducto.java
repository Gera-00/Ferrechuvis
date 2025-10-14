package org.upemor.ferrechuvis.view.panels.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.upemor.ferrechuvis.controller.ControllerCategorias;
import org.upemor.ferrechuvis.model.entity.Categorias;
import org.upemor.ferrechuvis.model.entity.Productos;
import org.upemor.ferrechuvis.view.components.ImagenUtils;

public class DetalleProducto extends JDialog{

    private Productos producto;

    public DetalleProducto(java.awt.Frame parent, Productos producto){
        super(parent, true); // Modal dialog
        this.producto = producto;
        try {
            initComponents();
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR DETALLE DEL PRODUCTO POR: ");
        }
    }

    /**
     * Método principal para inicializar los componentes del diálogo
     */
    private void initComponents()throws Exception{
        setUndecorated(true); // Quita la barra de título
        setTitle("Detalle del Producto");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 700);
        setLocationRelativeTo(getParent());
        
        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Ensamblar las secciones principales
        mainPanel.add(crearHeader(), BorderLayout.NORTH);
        mainPanel.add(crearBody(), BorderLayout.CENTER);
        mainPanel.add(crearFooter(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    /**
     ************** METODOS DE CREACION DE COMPONENTES PARA EL DIALOGO*************
     */

    /**
     * Crea el header naranja con la categoría del producto
     * @return JPanel con el header estilizado
     */
    private JPanel crearHeader()throws Exception{
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0xFF5500));
        header.setPreferredSize(new Dimension(0, 50));
        header.setBorder(new EmptyBorder(12, 16, 12, 16));


        ControllerCategorias ca = new ControllerCategorias();
        Categorias categoria = null; 
        categoria = ca.getById(producto.getId_categoria());
        
        JLabel lblCategoria = new JLabel(categoria.getNombre()); // Cambiar por producto.getCategoria() si existe
        lblCategoria.setFont(new Font("Arial", Font.BOLD, 20));
        lblCategoria.setForeground(Color.WHITE);
        lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton btnCerrar = new JButton("X");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 25));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCerrar.setHorizontalAlignment(SwingConstants.RIGHT);
        btnCerrar.addActionListener(e->{
            dispose();
        });
        
        // Agregar efecto hover al botón cerrar
        btnCerrar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(0xDC143C)); // Rojo intenso (Crimson)
                btnCerrar.setContentAreaFilled(true);
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                btnCerrar.setContentAreaFilled(false);
            }
        });
        
        header.add(lblCategoria, BorderLayout.CENTER);
        header.add(btnCerrar, BorderLayout.EAST);
        return header;
    }

    /**
     * Crea el cuerpo central con información del producto e imagen
     * @return JPanel con el contenido principal
     */
    private JPanel crearBody(){
        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(16, 20, 16, 20));
        
        // Información superior (código, proveedores, nombre)
        body.add(crearInfoSuperior(), BorderLayout.NORTH);
        // Imagen del producto
        body.add(crearPanelImagen(), BorderLayout.CENTER);
        // Precio y disponibilidad
        body.add(crearInfoPrecio(), BorderLayout.SOUTH);
        
        return body;
    }

    /**
     * Crea el footer con descripción y botón de agregar
     * @return JPanel con el pie del diálogo
     */
    private JPanel crearFooter(){
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
        
        footer.add(crearDescripcion(), BorderLayout.NORTH);
        footer.add(crearBotonAgregar(), BorderLayout.SOUTH);
        
        return footer;
    }

    /**
     * Crea la sección de información superior con distribución horizontal
     * @return JPanel con código (izq), nombre (centro) y proveedores (der) en una línea
     */
    private JPanel crearInfoSuperior(){
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(8, 16, 8, 16));
        infoPanel.setPreferredSize(new Dimension(0, 50)); // Altura fija para una sola línea
        
        // Código a la izquierda
        JLabel lblCodigo = new JLabel("Código: " + producto.getCodigo());
        lblCodigo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCodigo.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Nombre del producto en el centro
        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Botón de proveedores a la derecha
        JButton btnProveedores = new JButton();
        ImagenUtils.configurarButtonConImagen(btnProveedores,"src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\proveedores.png",32,32);
        btnProveedores.setFocusPainted(false);
        btnProveedores.setBorderPainted(false);
        btnProveedores.setContentAreaFilled(false);
        btnProveedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        infoPanel.add(lblCodigo, BorderLayout.WEST);
        infoPanel.add(lblNombre, BorderLayout.CENTER);
        infoPanel.add(btnProveedores, BorderLayout.EAST);
        
        return infoPanel;
    }

    /**
     * Crea el panel de imagen del producto
     * @return JPanel con la imagen centrada
     */
    private JPanel crearPanelImagen(){
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(0, 150));
        imagePanel.setBorder(new EmptyBorder(16, 0, 16, 0));
        
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(120, 120));
        
        if (producto.getLink_imagen() != null) {
            ImagenUtils.configurarLabelConImagen(lblImagen, producto.getLink_imagen(), 120, 120);
        } else {
            lblImagen.setText("Sin imagen");
            lblImagen.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }
        
        imagePanel.add(lblImagen);
        return imagePanel;
    }

    /**
     * Crea la sección de precio y disponibilidad
     * @return JPanel con precio en rojo y stock disponible
     */
    private JPanel crearInfoPrecio(){
        JPanel pricePanel = new JPanel(new GridLayout(2, 1, 0, 4));
        pricePanel.setBackground(Color.WHITE);
        pricePanel.setBorder(new EmptyBorder(8, 0, 16, 0));
        
        JLabel lblPrecio = new JLabel("$" + String.format("%.1f", producto.getPrecio()));
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 24));
        lblPrecio.setForeground(Color.RED);
        lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblDisponibilidad = new JLabel(producto.getStock() + " piezas Disponibles");
        lblDisponibilidad.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDisponibilidad.setForeground(Color.GRAY);
        lblDisponibilidad.setHorizontalAlignment(SwingConstants.CENTER);
        
        pricePanel.add(lblPrecio);
        pricePanel.add(lblDisponibilidad);
        
        return pricePanel;
    }

    /**
     * Crea el área de descripción del producto
     * @return JPanel con textarea de descripción en fondo gris, centrada y con scroll
     */
    private JPanel crearDescripcion(){
        // Panel contenedor principal con márgenes laterales
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setBorder(new EmptyBorder(12, 60, 12, 60)); // Márgenes laterales más amplios
        containerPanel.setPreferredSize(new Dimension(0, 240)); // Altura aumentada aprovechando espacio de barra título
        
        // Panel de descripción con fondo gris y borde
        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.setBackground(new Color(0xF5F5F5));
        // Combinar borde decorativo con padding interno
        descPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xD0D0D0), 1), // Borde gris claro
            new EmptyBorder(16, 20, 16, 20) // Padding interno más generoso
        ));
        
        // Área de texto con mayor tamaño de fuente para mejor legibilidad
        JTextArea txtDescripcion = new JTextArea("Descripción del Producto en General.\n\n"+producto.getDescripcion());
        txtDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Fuente más grande
        txtDescripcion.setBackground(new Color(0xF5F5F5));
        txtDescripcion.setEditable(false);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setBorder(null);
        txtDescripcion.setRows(9); // Más filas visibles aprovechando el espacio extra
        
        // JScrollPane para el área de texto
        javax.swing.JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollDesc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDesc.setBorder(null);
        scrollDesc.setBackground(new Color(0xF5F5F5));
        scrollDesc.getVerticalScrollBar().setUnitIncrement(12);
        
        descPanel.add(scrollDesc, BorderLayout.CENTER);
        containerPanel.add(descPanel, BorderLayout.CENTER);
        
        return containerPanel;
    }

    /**
     * Crea el botón de agregar producto
     * @return JPanel con el botón AGREGAR estilizado
     */
    private JPanel crearBotonAgregar(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(16, 20, 20, 20));
        
        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregar.setBackground(new Color(0xFF5500));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setPreferredSize(new Dimension(200, 40));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para agregar producto
                System.out.println("Producto agregado: " + producto.getNombre());
                dispose(); // Cerrar la ventana
            }
        });
        
        buttonPanel.add(btnAgregar);
        return buttonPanel;
    }
}

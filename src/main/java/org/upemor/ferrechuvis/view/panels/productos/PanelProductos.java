package org.upemor.ferrechuvis.view.panels.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
// import java.awt.List; // Remove this line
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.upemor.ferrechuvis.controller.ControllerProductos;
import org.upemor.ferrechuvis.model.entity.Productos;
import org.upemor.ferrechuvis.model.entity.Usuarios;
import org.upemor.ferrechuvis.view.components.ImagenUtils;

public class PanelProductos {

    private Usuarios usuario;
    private ControllerProductos cp;
    private List<Productos> data;
    // Formato en español: "24 de abril de 2025  11:45:13 AM"
    DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy  hh:mm:ss a     ", Locale.forLanguageTag("es-ES"));

    public PanelProductos(Usuarios usuario)throws Exception{
        this.usuario = usuario;
        cp = new ControllerProductos();
        //setupEventListeners();
    }

    /**
     * Este es el metodo principal de la clase, ya que se encarga de devolver el panel
     * que recibira el panel central en la vista de Usuario
     * 
     * @return Panel en general de Productos el cual se mostrara en la vista Principal
     */
    public JPanel crearPanel()throws Exception{
        JPanel panel = new JPanel(new BorderLayout());
         panel.setBackground(Color.GREEN);

        panel.add(crearHeader(), BorderLayout.NORTH);
        panel.add(crearBody(), BorderLayout.CENTER);

        return panel;
    }

    /**
     ************** METODOS DE CREACION DE COMPONENTES PARA EL PANEL EN GENERAL*************
     */


    /**
     * 
     * @return JPanel de tipo header para agregar al panel General
     */
    private JPanel crearHeader(){
        // Use BorderLayout so we can place the title centered and the date to the right
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(8, 16, 8, 16));
        header.setPreferredSize(new Dimension(0, 64));
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
        header.setMinimumSize(new Dimension(0, 64));

        // Título a la Izquierda
        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuario.getNombre());
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 30));
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

        // Fecha a la derecha: inicializamos con la hora actual
        JLabel lblFechaHora = new JLabel(formatoFechaHora.format(LocalDateTime.now()));
        lblFechaHora.setFont(new Font("Arial", Font.PLAIN, 18));
        lblFechaHora.setHorizontalAlignment(SwingConstants.RIGHT);

        header.add(lblBienvenida, BorderLayout.WEST);
        header.add(lblFechaHora, BorderLayout.EAST);

        // Timer Swing para actualizar la etiqueta cada segundo (ejecuta en EDT)
        Timer t = new Timer(1000, e -> {
            lblFechaHora.setText(formatoFechaHora.format(LocalDateTime.now()));
        });
        t.setRepeats(true);
        t.start();

        return header;
    }
        /**
         * Nota: la actualización periódica de la hora se hace con javax.swing.Timer
         * para no bloquear el EDT. Ya no necesitamos un método que entre en bucle.
         */
    
    /**
     * 
     * @return JPanel con el contenido del Cuerpo o Body que es el apartado de productos
     */
    private JPanel crearBody()throws Exception{
        JPanel body = new JPanel(new BorderLayout());
        /*--------------------PANEL SUPERIOR-------------------------- */
            JPanel panelSuperior = new JPanel();
             panelSuperior.setBackground(Color.WHITE);
             panelSuperior.setBorder(new EmptyBorder(8, 16, 8, 16));
             panelSuperior.setPreferredSize(new Dimension(0, 55));
             panelSuperior.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
             panelSuperior.setMinimumSize(new Dimension(0, 55));
                //Buscador
                JTextField txtBuscador = new JTextField(15);
                txtBuscador.setFont(new Font("Calibri", Font.PLAIN, 19));
                //Boton buscar
                JButton btnBuscar = new JButton();
                ImagenUtils.configurarButtonConImagen(btnBuscar, "src\\main\\java\\org\\upemor\\ferrechuvis\\resources\\icons\\lupa.png", 18, 18);
                btnBuscar.setBackground(new Color(0xFF5500));
                btnBuscar.setForeground(Color.WHITE);
                btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
                btnBuscar.setFocusPainted(false);
                btnBuscar.setBorderPainted(false);
                btnBuscar.setContentAreaFilled(true);
                btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                //Selector de Filtros (estilizado)
                JComboBox<String> cmbFiltros = new JComboBox<String>();
                cmbFiltros.addItem("Todo");
                cmbFiltros.addItem("Bajos en inventario");
                cmbFiltros.addItem("Mayor precio");
                cmbFiltros.addItem("Menor precio");
                
                cmbFiltros.setFont(new Font("Calibri", Font.PLAIN, 14));
                cmbFiltros.setPreferredSize(new Dimension(140, 36));
                cmbFiltros.setBackground(new Color(0xEDEDED));
                cmbFiltros.setOpaque(true);

                // Renderer personalizado para centrar texto y añadir padding
                cmbFiltros.setRenderer(new javax.swing.DefaultListCellRenderer(){
                    @Override
                    public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        javax.swing.JLabel lbl = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        lbl.setBorder(new EmptyBorder(6,12,6,12));
                        return lbl;
                    }
                });

                // Envolver en panel para dar efecto de fondo gris con padding similar a la imagen
                JPanel comboWrapper = new JPanel(new BorderLayout());
                comboWrapper.setBackground(new Color(0xEDEDED));
                comboWrapper.setBorder(BorderFactory.createEmptyBorder(4,8,4,8));
                comboWrapper.add(cmbFiltros, BorderLayout.CENTER);
                
                panelSuperior.add(txtBuscador);
                panelSuperior.add(btnBuscar);
                //panelSuperior.add(comboWrapper);
                /*--------------------PANEL INFERIOR-------------------------- */
                JPanel panelInferior = new JPanel(new BorderLayout());
                panelInferior.setBackground(Color.WHITE);
                panelInferior.setBorder(new EmptyBorder(16,24,16,24)); // Aumentar padding
                panelInferior.setPreferredSize(new Dimension(0,0));
                panelInferior.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
                panelInferior.setMinimumSize(new Dimension(0, 55));
                // panel con grid con 3 columnas para las tarjetas y lo centramos dentro del panelCentradoContenedor
                JPanel panelCentradoContenedor = new JPanel(new GridLayout(0, 3, 24, 24));
                panelCentradoContenedor.setBackground(Color.WHITE);
                panelCentradoContenedor.setBorder(new EmptyBorder(16,16,16,16)); // Padding interno

                actualizarPanel(panelCentradoContenedor, null);

                // Al presionar Enter en el campo de búsqueda ejecutar la búsqueda
                txtBuscador.addActionListener(ev -> {
                    try {
                        buscar(txtBuscador.getText().trim(), panelCentradoContenedor);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                btnBuscar.addActionListener(e->{
                    try {
                        buscar(txtBuscador.getText().trim(), panelCentradoContenedor);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Optionally show an error dialog to the user
                    }
                });
                
                
                // JScrollPane que mostrará panelCentradoContenedor cuando exceda el espacio
                JScrollPane scrollCentro = new JScrollPane(panelCentradoContenedor,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollCentro.setBorder(null);
                scrollCentro.getVerticalScrollBar().setUnitIncrement(16); // velocidad del scroll
                scrollCentro.setBackground(Color.WHITE);
               // scrollCentro.setBorder(BorderFactory.createLineBorder(new Color(0xFF5500),2));
                
                // Personalizar barra de scroll para estilo plano
                JScrollBar verticalBar = scrollCentro.getVerticalScrollBar();
                verticalBar.setBackground(Color.WHITE);
                verticalBar.setOpaque(true);
                verticalBar.setBorder(null);
                verticalBar.setPreferredSize(new Dimension(17, 0)); // ancho más delgado
                
                // Personalizar colores y apariencia plana usando UIManager temporalmente
                UIManager.put("ScrollBar.background", Color.WHITE);
                UIManager.put("ScrollBar.thumb", new Color(0xC0C0C0));
                UIManager.put("ScrollBar.thumbShadow", new Color(0xC0C0C0));
                UIManager.put("ScrollBar.thumbHighlight", new Color(0xE0E0E0));
                UIManager.put("ScrollBar.track", Color.WHITE);
                UIManager.put("ScrollBar.thumbDarkShadow", new Color(0xC0C0C0));
                
            panelInferior.add(scrollCentro, BorderLayout.CENTER);

        body.add(panelSuperior, BorderLayout.NORTH);
        body.add(panelInferior, BorderLayout.CENTER);
        return body;
    }

        /**
         * Metodo auxiliar de crearBody el cual servirar para crear las tarjetas de cada
         * producto del inventario y busquedas
         * @return JPanel con datos de un producto en forma de tarjeta
         */
        private JPanel crearTarjeta(Productos producto){
            JPanel tarjeta = new JPanel(new BorderLayout());
             tarjeta.setBackground(Color.WHITE);
             tarjeta.setBorder(new EmptyBorder(8,8,8,8));
             tarjeta.setPreferredSize(new Dimension(220, 260));
             tarjeta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
                // Codigo arriba
                JLabel lblCodigo = new JLabel(producto.getCodigo());
                lblCodigo.setFont(new Font("SansSerif", Font.PLAIN, 15));
                lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
                tarjeta.add(lblCodigo, BorderLayout.NORTH);

                // Imagen centrada (usamos ImagenUtils para mantener consistencia)
                JLabel lblImagen = new JLabel();
                lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
                // Reservamos espacio para que el diseño no 'salte' si falta la imagen
                lblImagen.setPreferredSize(new Dimension(100, 110));
                if (producto.getLink_imagen() != null) {
                    // ImagenUtils configurará el icono escalado en el label
                    ImagenUtils.configurarLabelConImagen(lblImagen, producto.getLink_imagen(), 110, 100);
                } else {
                    // Opción: dejar vacío o usar un placeholder si lo deseas
                    lblImagen.setText("");
                }
                tarjeta.add(lblImagen, BorderLayout.CENTER);

                // Pie: disponibilidad (pequeño), nombre y precio
                JPanel pie = new JPanel(new BorderLayout());
                pie.setBackground(Color.WHITE);

                JLabel lblDisponibilidad = new JLabel(Integer.toString(producto.getStock())+producto.getUnidad_medida()+" disponibles");
                lblDisponibilidad.setFont(new Font("SansSerif", Font.PLAIN, 13));
                lblDisponibilidad.setForeground(Color.GRAY);
                lblDisponibilidad.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel lblNombre = new JLabel(producto.getNombre());
                lblNombre.setFont(new Font("Arial", Font.BOLD, 24));
                lblNombre.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel lblPrecio = new JLabel("$"+Double.toString(producto.getPrecio()));
                lblPrecio.setFont(new Font("Arial", Font.BOLD, 25));
                lblPrecio.setForeground(Color.RED);
                lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);

            // Orden vertical dentro del pie
            JPanel pieVertical = new JPanel(new GridLayout(0,1));
            pieVertical.setBackground(Color.WHITE);
            pieVertical.add(lblNombre);
            pieVertical.add(lblDisponibilidad);
            pieVertical.add(lblPrecio);

            pie.add(pieVertical, BorderLayout.CENTER);

            tarjeta.add(pie, BorderLayout.SOUTH);

            /* 
            * IMPLEMENTACION PARA HACER LAS TARJETAS CLICKEABLES 
            */    
            tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            /*AQUI IMPLEMENTAR GUARDAR EL OBJETO PARA DESPLEGARLO EN VENTANA DE PRODUCTO SELECCIONABLE */
            tarjeta.putClientProperty("producto", producto);
            tarjeta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    Productos P = (Productos) tarjeta.getClientProperty("producto");
                    //*****************************************DEPLEGAMOS PANTALLA */
                }
            });

            tarjeta.addMouseListener(new MouseAdapter() {
                Color originalBg = tarjeta.getBackground();
                Border originalBorder = tarjeta.getBorder();

                @Override
                public void mouseEntered(MouseEvent e){
                    tarjeta.setBackground(Color.WHITE);
                    tarjeta.setBorder(BorderFactory.createLineBorder(new Color(0xFF88000),2));
                }

                @Override
                public void mouseExited(MouseEvent e){
                    tarjeta.setBackground(originalBg);
                    tarjeta.setBorder(originalBorder);
                    tarjeta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
                }

                @Override
                public void mouseClicked(MouseEvent e){
                    DetalleProducto detalle = new DetalleProducto(null, producto);
                    detalle.setVisible(true);
                }
            
            });

            return tarjeta;
        }

        /**
         * Metodo del boton de Busqueda, el cual hace las busquedas por dos conceptos diferentes
         * nombre y codigo, el cual usa un HashSet para poder hacer las dos busquedas simultaneas y filtrar
         * para que no haya productos duplicados
         * @param busqueda
         * @throws Exception
         */
        private void buscar(String busqueda, JPanel panel)throws Exception{
            List<Productos> resultados;
            
            if (busqueda.isEmpty()) {
                resultados = cp.getAll();
                actualizarPanel(panel, resultados);
            }else{
                Set<Productos> resultadosSet = new HashSet<>();
                resultadosSet.addAll(cp.getByName(busqueda));
                resultadosSet.addAll(cp.getByCodigo(busqueda));

                resultados = new ArrayList<>(resultadosSet);
                actualizarPanel(panel, resultados);
            }
            
        }

        private void actualizarPanel(JPanel panelCentradoContenedor, List<Productos> data)throws Exception{
               panelCentradoContenedor.removeAll();

                if (data==null) {
                    data = cp.getAll();
                }

                // Agregar tarjetas de ejemplo (ajusta datos o usa tu repositorio)
                for (Productos producto : data) {
                    JPanel tarjeta = crearTarjeta(producto);
                    panelCentradoContenedor.add(tarjeta);
                }
            panelCentradoContenedor.revalidate();
            panelCentradoContenedor.repaint();
        }

}

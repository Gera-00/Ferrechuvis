package org.upemor.ferrechuvis.view.panels.configuraciones;

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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PanelConfiguracion {

    public JPanel crearPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        panel.add(crearHeader(), BorderLayout.NORTH);
        panel.add(crearBody(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearHeader(){
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0xF8F8F8));
        header.setBorder(new EmptyBorder(20,16,20,16));

        JLabel titulo = new JLabel("CONFIGURACIÓN", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(Color.BLACK);
        
        header.add(titulo, BorderLayout.CENTER);

        return header;
    }

    private JPanel crearBody(){
        JPanel body = new JPanel(new GridLayout(2, 2, 20, 20));
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(40, 60, 40, 60));
        
        // Crear las 4 tarjetas de configuración
        body.add(crearTarjetaConfiguracion("⚙", "Gestionar", "Productos y categorías"));
        body.add(crearTarjetaConfiguracion("↗", "Entradas/", "Salidas", "Gestión de inventario"));
        body.add(crearTarjetaConfiguracion("📊", "Reportes", "Análisis y estadísticas"));
        body.add(crearTarjetaConfiguracion("👥", "Usuarios", "Administrar accesos"));
        
        return body;
    }

    /**
     * Método auxiliar para crear tarjetas de configuración
     * @param icono Ícono representativo
     * @param titulo Título principal
     * @param subtitulo Descripción opcional
     * @return JButton con estilo de tarjeta
     */
    private JButton crearTarjetaConfiguracion(String icono, String titulo, String subtitulo) {
        JButton tarjeta = new JButton();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBackground(new Color(0xFF5500)); // Color naranja más claro
        tarjeta.setBorder(new EmptyBorder(20, 20, 20, 20));
        tarjeta.setPreferredSize(new Dimension(200, 120));
        tarjeta.setFocusPainted(false);
        
        // Panel para el contenido
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setOpaque(false);
        
        // Ícono (arriba izquierda)
        JLabel labelIcono = new JLabel(icono);
        labelIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        labelIcono.setForeground(Color.WHITE);
        labelIcono.setHorizontalAlignment(SwingConstants.LEFT);
        contenido.add(labelIcono, BorderLayout.NORTH);
        
        // Panel para textos (abajo)
        JPanel textos = new JPanel(new BorderLayout());
        textos.setOpaque(false);
        
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelTitulo.setForeground(Color.WHITE);
        textos.add(labelTitulo, BorderLayout.NORTH);
        
        if (subtitulo != null && !subtitulo.isEmpty()) {
            JLabel labelSubtitulo = new JLabel(subtitulo);
            labelSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
            labelSubtitulo.setForeground(new Color(255, 255, 255, 180));
            textos.add(labelSubtitulo, BorderLayout.CENTER);
        }
        
        contenido.add(textos, BorderLayout.SOUTH);
        tarjeta.add(contenido);
        
        // Configurar cursor como mano para indicar que es clickeable
        tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Efectos de hover
        final Color colorOriginal = new Color(0xFF5500);
        final Color colorHover = new Color(0xFF7722); // Naranja más claro en hover
        final Color colorPressed = new Color(0xDD3300); // Naranja más oscuro al presionar
        
        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tarjeta.setBackground(colorHover);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                tarjeta.setBackground(colorOriginal);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                tarjeta.setBackground(colorPressed);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // Volver al color hover si el mouse sigue encima
                if (tarjeta.contains(e.getPoint())) {
                    tarjeta.setBackground(colorHover);
                } else {
                    tarjeta.setBackground(colorOriginal);
                }
            }
        });
        
        // Efecto de click
        tarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked: " + titulo);
                // Aquí puedes agregar la lógica para cada tarjeta
            }
        });
        
        return tarjeta;
    }

    // Sobrecarga para tarjetas con título de dos líneas
    private JButton crearTarjetaConfiguracion(String icono, String titulo1, String titulo2, String subtitulo) {
        JButton tarjeta = crearTarjetaConfiguracion(icono, titulo1, subtitulo);
        
        // Modificar el contenido para título de dos líneas
        JPanel contenido = (JPanel) tarjeta.getComponent(0);
        JPanel textos = (JPanel) contenido.getComponent(1);
        
        // Reemplazar el título con panel de dos líneas
        textos.remove(0); // quitar título anterior
        
        JPanel tituloPanel = new JPanel(new BorderLayout());
        tituloPanel.setOpaque(false);
        
        JLabel labelTitulo1 = new JLabel(titulo1);
        labelTitulo1.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelTitulo1.setForeground(Color.WHITE);
        
        JLabel labelTitulo2 = new JLabel(titulo2);
        labelTitulo2.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelTitulo2.setForeground(Color.WHITE);
        
        tituloPanel.add(labelTitulo1, BorderLayout.NORTH);
        tituloPanel.add(labelTitulo2, BorderLayout.CENTER);
        
        textos.add(tituloPanel, BorderLayout.NORTH, 0);
        
        return tarjeta;
    }
}

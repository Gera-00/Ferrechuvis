package org.upemor.ferrechuvis.view.panels.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.CookieHandler;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PanelVentas{
    /**
     * Metodo Principal de la clase PanelVentas el cual esta encargado
     * de devolver el panel de Ventas para poderlo cargar en la interfaz de
     * Principal Administrador
     * @return Panel General de Ventas
     */
    public JPanel crearPanel(){
    JPanel panel = new JPanel(new BorderLayout());
     panel.setBackground(Color.WHITE);
        
        
        
        panel.add(crearHeader(), BorderLayout.NORTH);
        panel.add(crearBody(), BorderLayout.CENTER);
        return panel;
    }

    /**************** METODOS DE CREACION PARA PANEL GENERAL*****************/
    
    /**
     * Metodo auxiliar para el Panel General, encargado de construir
     * el header con las tarjertas de estadisticas
     * @return header
     */
    private JPanel crearHeader(){
        // Usar GridLayout para distribuir 3 tarjetas de forma pareja
        JPanel header = new JPanel(new GridLayout(1, 3, 12, 0));
         header.setBackground(new Color(0xF8F8F8));
         header.setBorder(new EmptyBorder(8,16,8,16));
         header.setPreferredSize(new Dimension(0,200));
         header.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
         header.setMinimumSize(new Dimension(0,200));

        // Crear las tres tarjetas con título y valor (ejemplos)
        header.add(crearEstadistica("TOTAL DE VENTAS", "$1500"));
        header.add(crearEstadistica("N° VENTAS", "4"));
        header.add(crearEstadistica("PEDIDOS PENDIENTES", "1"));

        return header;
    }
        /**
         * Metodo auxiliar de crearHeader() para crear las tarjetas de estadisticas
         * recibiendo los parametros
         * @return Tarjerta de Estadistica
         */
        private JPanel crearEstadistica(String titulo, String valor){
            JPanel estadistica = new JPanel(new BorderLayout());
            estadistica.setBackground(new Color(0xE2E2E2));
            estadistica.setBorder(new EmptyBorder(12,12,12,12));

            // Label del título (arriba, centrado)
            JLabel titleLabel = new JLabel(titulo, SwingConstants.CENTER);
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            titleLabel.setForeground(Color.DARK_GRAY);
            estadistica.add(titleLabel, BorderLayout.NORTH);

            // Label del valor (centro, grande y en negrita)
            JLabel valueLabel = new JLabel(valor, SwingConstants.CENTER);
            valueLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
            valueLabel.setForeground(Color.BLACK);
            estadistica.add(valueLabel, BorderLayout.CENTER);

            return estadistica;
        }
    

    private JPanel crearBody(){
        JPanel body = new JPanel(new BorderLayout());
         body.setBackground(Color.WHITE);

        body.add(crearContenidoVentas(), BorderLayout.WEST);
        body.add(crearContenidoPedidos(), BorderLayout.EAST);

        return body;
    }
        /**
         * Metodo Auxiliar de crearBody() para crear seccion con 
         * boton para agregar ventas y tabla de ventas
         * @return Contenido Ventas
         */
        private JPanel crearContenidoVentas(){
            JPanel contenido = new JPanel();
             contenido.setBackground(Color.RED);

            return contenido;
        }

        /**
         * Segundo metodo auxiliar de crearBody() para crear sección
         * para visualizar pedidos pendientes
         * @return Contenido Pendientes
         */
        private JPanel crearContenidoPedidos(){
            JPanel contenido = new JPanel();
             contenido.setBackground(Color.BLUE);
            return contenido;
        }


}

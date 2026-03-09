package org.upemor.ferrechuvis.view.panels.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.Box;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.upemor.ferrechuvis.controller.ControllerMovimientos;
import org.upemor.ferrechuvis.model.entity.Movimientos;

public class PanelVentas{
    // Componentes de la tabla
    private DefaultTableModel modeloTabla;
    private JTable tablaVentas;

    /**
     * Metodo Principal de la clase PanelVentas el cual esta encargado
     * de devolver el panel de Ventas para poderlo cargar en la interfaz de
     * Principal Administrador
     * @return Panel General de Ventas
     */
    public JPanel crearPanel()throws Exception{
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
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.X_AXIS));
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Panel de ventas (lado izquierdo - expansible)
        JPanel ventasPanel = crearContenidoVentas();
        ventasPanel.setMinimumSize(new Dimension(500, 400));

        // Panel de pedidos (lado derecho - tamaño fijo)
        JPanel pedidosPanel = crearContenidoPedidos();
        pedidosPanel.setPreferredSize(new Dimension(350, 500));
        pedidosPanel.setMaximumSize(new Dimension(350, Integer.MAX_VALUE));
        pedidosPanel.setMinimumSize(new Dimension(250, 400));

        // Agregar los paneles con espaciado
        body.add(ventasPanel);
        body.add(Box.createRigidArea(new Dimension(16, 0))); // Espacio entre paneles
        body.add(pedidosPanel);

        return body;
    }
    
        /**
         * Método Auxiliar de crearBody() para crear sección con 
         * botón para agregar ventas y tabla de ventas
         * @return Contenido Ventas
         */
        private JPanel crearContenidoVentas(){
            JPanel contenido = new JPanel(new BorderLayout());

            return contenido;
        }

            /**
             * Segundo método auxiliar de crearBody() para crear sección
             * para visualizar pedidos pendientes
             * @return Contenido Pendientes
             */
            private JPanel crearContenidoPedidos(){
                JPanel contenido = new JPanel(new BorderLayout());
                return contenido;
            }
            
            /**
             * Método para configurar la tabla de ventas
             */
            private void configurarTablaVentas(){
                // Definir columnas de la tabla
                String[] columnas = {"ID Venta", "Cliente", "Fecha y Hora", "Monto Total", "Estado"};
                
                // Crear modelo de tabla
                modeloTabla = new DefaultTableModel(columnas, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Hacer la tabla no editable
                    }
                };
                
                // Crear la tabla
                tablaVentas = new JTable(modeloTabla);
                tablaVentas.setBackground(Color.WHITE);
                tablaVentas.setSelectionBackground(new Color(255, 85, 0, 50)); // Naranja con transparencia
                tablaVentas.setGridColor(new Color(0xE0E0E0));
                tablaVentas.setRowHeight(35);
                tablaVentas.getTableHeader().setBackground(new Color(0xF5F5F5));
                tablaVentas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
                tablaVentas.setFont(new Font("SansSerif", Font.PLAIN, 11));
                
                // Configurar anchos de columnas
                tablaVentas.getColumnModel().getColumn(0).setPreferredWidth(80);
                tablaVentas.getColumnModel().getColumn(1).setPreferredWidth(150);
                tablaVentas.getColumnModel().getColumn(2).setPreferredWidth(130);
                tablaVentas.getColumnModel().getColumn(3).setPreferredWidth(100);
                tablaVentas.getColumnModel().getColumn(4).setPreferredWidth(80);
            }
            
            /**
             * Método público para agregar una venta a la tabla
             */
            public void agregarVentaATabla(String idVenta, String cliente, String fechaHora, String montoTotal, String estado){
                Object[] fila = {idVenta, cliente, fechaHora, montoTotal, estado};
                modeloTabla.addRow(fila);
            }

}

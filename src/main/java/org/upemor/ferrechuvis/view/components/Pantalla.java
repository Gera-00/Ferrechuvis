package org.upemor.ferrechuvis.view.components;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Pantalla extends JFrame{

    protected JPanel panelPrincipal;

    /**
     * la clase Pantalla tiene como proposito, permitir 
     * la reutilizacion de codigo para poder crear JFrames para pantallas
     * del software, las cuales ya traen la configuración Base
     * para un JFrame, ademas de agregarle un panel principal
     * @param titulo
     * @param alto
     * @param ancho
     */
    public Pantalla(String titulo, int ancho, int alto, boolean modal) {
        //panelPrincipal = new JPanel();
        initBaseComponents(titulo,ancho,alto,modal);
    }

    public Pantalla(){

    }

    protected void inicializar(){
        initSpecificComponents();
        setupEventListeners();
    }
    
    protected void initBaseComponents(String titulo, int ancho, int alto, boolean modal) {
        panelPrincipal =  new JPanel(tipoPanel());
        // Configuraciones comunes a todas las pantallas
        this.setContentPane(panelPrincipal);
        this.setTitle(titulo);
        this.setSize(ancho, alto);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        //Diferencias de configuracion entre pantalla modal y no modal
        if (modal) {
            this.setUndecorated(true);
            //Si es modal al dar click en cerrar solo se cierra la pantalla en lugar de detener la ejecución
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }else{
            //En caso de ser pantalla Principal se detiene la ejecución al dar click en cerrar
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
    
    /**
     * tipoPanel es un metodo abstracto el cual permite
     * que cada pantalla heredada de está clase
     * pueda definir su tipo de layout en el panel principal
     * 
     * @return tipo de layout para panel principal
     */
    protected abstract LayoutManager tipoPanel();
    protected abstract void initSpecificComponents();
    protected abstract void setupEventListeners();
    
    public void mostrar() {
        setVisible(true); // Se hace visible cuando se necesite
    }
    public void ocultar(){
        setVisible(false);
    }

    /**
     * Este metodo sirve para crear las restricciones al agregar cualquier
     * elemento a un JPanel con GridBagLayout
     * @param x posición en X
     * @param y posición en Y
     * @param width ancho en celdas
     * @param height alto en celdas
     * @return GridBagConstrains configurado
     */
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
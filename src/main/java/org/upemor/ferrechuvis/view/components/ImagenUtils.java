package org.upemor.ferrechuvis.view.components;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImagenUtils {
    /**
     * Prepara una imagen redimensionada desde una ruta
     * @param rutaImagen Ruta del archivo de imagen
     * @param ancho Ancho deseado
     * @param alto Alto deseado
     * @return ImageIcon redimensionado
     */
    public static ImageIcon prepararImagen(String rutaImagen, int ancho, int alto) {
        try {
            ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
            Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(
                ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenRedimensionada);
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + rutaImagen);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Configura un JLabel con una imagen
     * @param label JLabel a configurar
     * @param rutaImagen Ruta de la imagen
     * @param ancho Ancho deseado
     * @param alto Alto deseado
     */
    public static void configurarLabelConImagen(JLabel label, String rutaImagen, int ancho, int alto) {
        ImageIcon icono = prepararImagen(rutaImagen, ancho, alto);
        if (icono != null) {
            label.setIcon(icono);
        }
    }
    
    /**
     * Configura un JButton con una imagen
     * @param button JButton a configurar
     * @param rutaImagen Ruta de la imagen
     * @param ancho Ancho deseado
     * @param alto Alto deseado
     */
    public static void configurarButtonConImagen(JButton button, String rutaImagen, int ancho, int alto) {
        ImageIcon icono = prepararImagen(rutaImagen, ancho, alto);
        if (icono != null) {
            button.setIcon(icono);
        }
    }
}

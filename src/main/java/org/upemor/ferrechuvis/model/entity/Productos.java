package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Productos extends Entity{
    private String codigo;
    private String nombre;
    private String descripcion;
    private long id_categoria;
    private String unidad_medida;
    private int stock;
    private double precio;
    private int stock_minimo;
    private String link_imagen;

    @Override
    public String toString(){
        return codigo+": "+nombre+" "+id_categoria+" "+stock;
    }

}

package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Productos extends Entity{
    private String codigo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String unidad_medida;
    private int stock;
    private double precio;

    @Override
    public String toString(){
        return codigo+": "+nombre+" "+categoria+" "+stock;
    }

}

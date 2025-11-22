package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

/**@author PC**/

@Getter
@Setter
public class Tipos_Movimientos extends Entity{
    private String nombre;
    private int signo_stock;
    private String descripcion;
    
    @Override
    public String toString(){
        return nombre+" "+descripcion;
    }
}

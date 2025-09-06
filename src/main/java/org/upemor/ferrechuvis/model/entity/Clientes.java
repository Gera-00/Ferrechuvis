package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Clientes extends Entity{
    
    private String nombre;
    private String telefono;
    private String direccion;

    @Override
    public String toString(){
        return nombre+" "+telefono;
    }
    
}

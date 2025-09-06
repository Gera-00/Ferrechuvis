package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Proveedores extends Entity{

    private String nombre;
    private String telefono;
    private String direccion;
    private String email;

    @Override
    public String toString(){
        return "Proveedor: "+nombre+" "+telefono;
    }
}

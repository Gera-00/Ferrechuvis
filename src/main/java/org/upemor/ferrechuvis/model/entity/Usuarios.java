package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Usuarios extends Entity{
    private String nombre; 
    private String a_paterno; 
    private String a_materno; 
    private String usuario; 
    private String password; 
    private String tipo_usuario;

    @Override
    public String toString(){
        return tipo_usuario+": "+nombre+" ";
    }
}

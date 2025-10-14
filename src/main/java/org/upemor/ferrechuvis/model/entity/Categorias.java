package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Categorias extends Entity{
    private String nombre;

    @Override
    public String toString(){
        return nombre;
    }
}

package org.upemor.ferrechuvis.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Pedidos extends Entity{
    
    private long id_cliente;
    private LocalDate fecha;
    private Double total;
    private String estado;

    @Override
    public String toString(){
        return id+" "+fecha;
    }

}

package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Detalles_Pedidos extends Entity{

    private long id_pedido;
    private long id_producto;
    private int cantidad;
    private double precio_unitario;

    @Override
    public String toString(){
        return id_pedido+" "+id_producto;
    }
}

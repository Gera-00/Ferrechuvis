package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Detalles_Movimientos extends Entity{

    private long id_producto;
    private long id_movimiento;
    private int cantidad;
    private int stock_anterior;
    private int stock_actual;
    private String observaciones;
    private double precio_unitario;

    @Override
    public String toString(){
        return id_movimiento+" "+id_producto;
    }
}

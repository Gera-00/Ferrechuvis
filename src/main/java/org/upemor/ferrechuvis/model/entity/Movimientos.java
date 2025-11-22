package org.upemor.ferrechuvis.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Movimientos extends Entity{
    private String codigo;
    private long id_cliente;
    private long id_proveedor;
    private long id_tipo_movimiento;
    private String motivo;
    private Double total;
    private LocalDate fecha;

    @Override
    public String toString(){
        return id+" "+fecha;
    }

}

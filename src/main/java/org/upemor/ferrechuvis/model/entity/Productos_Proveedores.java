package org.upemor.ferrechuvis.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Productos_Proveedores extends Entity{
    
    private long id_producto;
    private long id_proveedor;

    @Override
    public String toString(){
        return "Producto: "+id_producto+" Proveedor: "+id_proveedor;
    }
}

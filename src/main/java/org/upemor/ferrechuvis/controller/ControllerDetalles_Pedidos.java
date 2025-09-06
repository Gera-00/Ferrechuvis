package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Detalles_Pedidos;
import org.upemor.ferrechuvis.model.repository.RepositoryDetalles_Pedidos;

public class ControllerDetalles_Pedidos extends Controller<RepositoryDetalles_Pedidos, Detalles_Pedidos> {
    
    public ControllerDetalles_Pedidos()throws Exception{
        super();
        repository = new RepositoryDetalles_Pedidos();
    }

    protected boolean validate(Detalles_Pedidos obj)throws Exception{

    if(obj.getId()<0)
        throw new Exception("Error: El Id no puede ser negativo");

    if(obj.getId_pedido()<=0)
        throw new Exception("Error: Se Debe especificar un pedido válido");

    if(obj.getId_producto()<=0)
        throw new Exception("Error: Se Debe especificar un producto válido");

    if(obj.getCantidad()<=0)
        throw new Exception("Error: El número de piezas debe ser mayor a 0");

    if(obj.getCantidad() != Math.floor(obj.getCantidad()))
        throw new Exception("Error: La cantidad debe ser un número entero");

    if(obj.getPrecio_unitario()<=0)
        throw new Exception("Error: El Precio Unitario debe ser mayor a 0");
    return true;
    }

}

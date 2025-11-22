package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Detalles_Movimientos;
import org.upemor.ferrechuvis.model.repository.RepositoryDetalles_Movimientos;

public class ControllerDetalles_Movimientos extends Controller<RepositoryDetalles_Movimientos, Detalles_Movimientos> {
    
    public ControllerDetalles_Movimientos()throws Exception{
        super();
        repository = new RepositoryDetalles_Movimientos();
    }

    protected boolean validate(Detalles_Movimientos obj)throws Exception{

    if(obj.getId()<0)
        throw new Exception("Error: El Id no puede ser negativo");

    if(obj.getId_producto()<=0)
        throw new Exception("Error: Se Debe especificar un producto válido");
    
    if(obj.getId_movimiento()<=0)
        throw new Exception("Error: Se Debe especificar un pedido válido");

    if(obj.getCantidad()<=0)
        throw new Exception("Error: El número de piezas debe ser mayor a 0");

    if(obj.getCantidad() != Math.floor(obj.getCantidad()))
        throw new Exception("Error: La cantidad debe ser un número entero");
    
    if(obj.getStock_anterior() <0)
        throw new Exception("Error: El stock Anterior no es valido");
    
    if(obj.getStock_actual() < 0)
        throw new Exception("Error: El stock Actual es valido");
    
    if(obj.getObservaciones() == null || obj.getObservaciones().isEmpty())
        throw new Exception("Error: Debe tener una descripción");
    
    if(obj.getPrecio_unitario()<=0)
        throw new Exception("Error: El Precio Unitario debe ser mayor a 0");
    return true;
    }

}

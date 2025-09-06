package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Pedidos;
import org.upemor.ferrechuvis.model.repository.RepositoryPedidos;

public class ControllerPedidos extends Controller<RepositoryPedidos, Pedidos>{
    
    public ControllerPedidos()throws Exception{
        super();
        repository = new RepositoryPedidos();
    }

    protected boolean validate(Pedidos obj)throws Exception{
        if(obj.getId()<0)
            throw new Exception("Error: El Id no puede ser negativo");

        if(obj.getId_cliente()<=0)
            throw new Exception("Error: Debe especificar un cliente válido");

        if(obj.getFecha()==null)
            throw new Exception("Error: La fecha del pedido es obligatoria");

        if(obj.getTotal()<=0)
            throw new Exception("Error: El Total del pedido debe ser mayor a 0");

        if(obj.getEstado() == null || obj.getEstado().isEmpty())
            throw new Exception("Error: El estado del pedido es obligatorio");
        return true;
    }
}

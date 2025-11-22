package org.upemor.ferrechuvis.controller;

import java.util.LinkedList;
import java.util.List;

import org.upemor.ferrechuvis.model.entity.Movimientos;
import org.upemor.ferrechuvis.model.repository.RepositoryMovimientos;

public class ControllerMovimientos extends Controller<RepositoryMovimientos, Movimientos>{
    
    public ControllerMovimientos()throws Exception{
        super();
        repository = new RepositoryMovimientos();
    }

    protected boolean validate(Movimientos obj)throws Exception{
        if(obj.getId()<0)
            throw new Exception("Error: El Id no puede ser negativo");
        
        if(obj.getCodigo() == null || obj.getCodigo().isEmpty())
            throw new Exception("Error: El codigo del movimiento no puede estar vacío");

        if(obj.getId_cliente()<=0)
            throw new Exception("Error: Debe especificar un cliente válido");
        
        if(obj.getId_proveedor()<=0)
            throw new Exception("Error: Debe especificar un proveedor válido");
        
        if(obj.getId_tipo_movimiento()<=0)
            throw new Exception("Error: Debe especificar un tipo de movimiento válido");

        if(obj.getMotivo() == null || obj.getMotivo().isEmpty())
            throw new Exception("Error: El motivo del movimiento es obligatorio");
        
        //HACER VALIDACION SOLO SI ES VENTA O PEDIDO
        /*if(obj.getTotal()<=0)
            throw new Exception("Error: El Total del movimiento debe ser mayor a 0");*/

        if(obj.getFecha()==null)
            throw new Exception("Error: La fecha del movimiento es obligatoria");

        return true;
    }

    public List<Movimientos> getByCodigo(String prefijo){
        List<Movimientos> movimientos = new LinkedList<>();
        
        try {
            movimientos = repository.obtenerMovimientosPorPrefijo(prefijo);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: getByCodigo ");
            throw e;
        }
        return movimientos;
    }

}

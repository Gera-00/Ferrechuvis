package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Productos_Proveedores;
import org.upemor.ferrechuvis.model.repository.RepositoryProductos_Proveedores;

public class ControllerProductos_Proveedores extends Controller<RepositoryProductos_Proveedores, Productos_Proveedores>{

    public ControllerProductos_Proveedores()throws Exception{
        super();
        repository = new RepositoryProductos_Proveedores();
    }

    protected boolean validate(Productos_Proveedores obj)throws Exception{

        if(obj.getId()<0)
            throw new Exception("Error: El Id debe ser positivo");

        if(obj.getId_producto()<=0)
            throw new Exception("Error: Se Debe especificar un Producto válido");
        
        if(obj.getId_proveedor()<=0)
            throw new Exception("Error: Se Debe especificar un Proveedor válido");

        return true;
    }
    
}

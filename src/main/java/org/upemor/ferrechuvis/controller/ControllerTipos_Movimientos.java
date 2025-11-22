package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Tipos_Movimientos;
import org.upemor.ferrechuvis.model.repository.RepositoryTipos_Movimientos;


public class ControllerTipos_Movimientos extends Controller<RepositoryTipos_Movimientos, Tipos_Movimientos> {

    public ControllerTipos_Movimientos() throws Exception {
        super();
        repository = new RepositoryTipos_Movimientos();
    }

    protected boolean validate(Tipos_Movimientos obj) throws Exception {
        if (obj.getId() < 0)
            throw new Exception("Error: El Id no puede ser negativo");

        if (obj.getNombre() == null || obj.getNombre().trim().isEmpty())
            throw new Exception("Error: El Nombre del tipo de movimiento no puede quedar vacío");
        
        if (obj.getSigno_stock()==0)
            throw new Exception("Error: El tipo de movimiento tiene que hacer alguna operacion");
        
        if (obj.getDescripcion() == null || obj.getDescripcion().isEmpty())
            throw new Exception("Error: El tipo de movimiento tiene que tener una descripción");

        return true;
    }
}

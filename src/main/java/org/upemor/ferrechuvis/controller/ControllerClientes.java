package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Clientes;
import org.upemor.ferrechuvis.model.repository.RepositoryClientes;

public class ControllerClientes extends Controller<RepositoryClientes, Clientes>{
    
    public ControllerClientes()throws Exception{
        super();
        repository = new RepositoryClientes();
    }

    protected boolean validate(Clientes obj)throws Exception{
        if(obj.getId()<0)
            throw new Exception("Error: El Id no puede ser negativo");

        if(obj.getNombre() == null || obj.getNombre().isEmpty())
            throw new Exception("Error: El Nombre no puede quedar vacío");

        if(obj.getTelefono()==null || obj.getTelefono().length()!=10)
            throw new Exception("Error: El teléfono debe contener exactamente 10 dígitos");
            
        return true;
    }

}

package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Proveedores;
import org.upemor.ferrechuvis.model.repository.RepositoryProveedores;

public class ControllerProveedores extends Controller<RepositoryProveedores, Proveedores>{
    public ControllerProveedores()throws Exception{
        super();
        repository = new RepositoryProveedores();
    }

    protected boolean validate(Proveedores obj)throws Exception{
    
    if(obj.getId()<0)
        throw new Exception("Error: El ID no puede ser negativo");

    if(obj.getNombre()==null || obj.getNombre().isEmpty())
            throw new Exception("Error: El nombre no puede estar vacío");
    
    if(obj.getTelefono()==null || obj.getTelefono().length()!=10)
        throw new Exception("Error: El teléfono debe contener exactamente 10 dígitos");
    
    if(obj.getDireccion()==null || obj.getDireccion().isEmpty())
        throw new Exception("Error: La dirección no puede estar vacía");
    
    // Email opcional con validación de formato
    if(obj.getEmail()!=null && !obj.getEmail().isEmpty() && !isValidEmail(obj.getEmail()))
        throw new Exception("Error: El email no tiene un formato válido");
    
    return true;
    }


    private boolean isValidEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

}

package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Usuarios;
import org.upemor.ferrechuvis.model.repository.RepositoryUsuarios;

public class ControllerUsuarios extends Controller<RepositoryUsuarios, Usuarios>{

    public ControllerUsuarios()throws Exception{
        super();
        repository = new RepositoryUsuarios();
    }

    protected boolean validate(Usuarios obj)throws Exception{
        if(obj.getId()<0)
            throw new Exception("Error: El ID no puede ser negativo");

        if(obj.getNombre()==null || obj.getNombre().isEmpty())
            throw new Exception("Error: El Nombre no puede estar vacío");

        if(obj.getA_paterno()==null || obj.getA_paterno().isEmpty())
            throw new Exception("Error: El Apellido Paterno no puede estar vacío");

        if(obj.getUsuario()==null || obj.getUsuario().isEmpty())
            throw new Exception("Error: El Usuario no puede estar vacío");

        if(obj.getPassword()==null || obj.getPassword().isEmpty())
            throw new Exception("Error: La contraseña no puede estar vacia");

        /*
         * Esta condición solo para validar cualquier tipo de error que pueda existir con el Tipo de Usuario, aunque el Tipo de Usuario
         * se manejara con la lógica del Software, es decir, cualquier usuario registrado por el Dueño tendra tipo de Usuario Empleado 
        */
        if(obj.getTipo_usuario()==null || obj.getTipo_usuario().isEmpty())
            throw new Exception("Error: de Tipo es obligatorio");

        return true;
    }

    public Usuarios login(String usuario, String password) throws Exception {
        return repository.login(usuario, password);
    }
}

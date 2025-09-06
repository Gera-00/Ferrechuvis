package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Productos;
import org.upemor.ferrechuvis.model.repository.RepositoryProductos;

public class ControllerProductos extends Controller<RepositoryProductos, Productos>{

    public ControllerProductos()throws Exception{
        super();
        repository = new RepositoryProductos();
    }

    protected boolean validate(Productos obj)throws Exception{
    
        if(obj.getId()<0)
            throw new Exception("Error: El ID no puede ser negativo");

        if(obj.getCodigo()==null||obj.getCodigo().isEmpty())
            throw new Exception("Error: El Codigo del Producto no puede estar vacio");
        
        if(obj.getNombre()==null||obj.getNombre().isEmpty())
            throw new Exception("Error: El Nombre del Producto no puede estar vacio");

        if(obj.getDescripcion()==null||obj.getDescripcion().isEmpty())
            throw new Exception("Error: La Descripcion del Producto no puede estar vacia");

        if(obj.getCategoria()==null||obj.getCategoria().isEmpty())
            throw new Exception("Error: La Categoria del Producto es obligatoria");

        if(obj.getUnidad_medida()==null||obj.getUnidad_medida().isEmpty())
            throw new Exception("Error: La Unidad de Medida del Producto es obligatoria");

        if(obj.getStock()<0)
            throw new Exception("Error: El Stock no puede ser menor a 0");
        
        if(obj.getPrecio()<0)
            throw new Exception("Error: El Precio no puede ser menor a 0 pesos");
            
        return true;
    }
    
}

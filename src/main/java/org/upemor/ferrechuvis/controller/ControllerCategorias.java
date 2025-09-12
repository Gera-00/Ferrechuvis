package org.upemor.ferrechuvis.controller;

import org.upemor.ferrechuvis.model.entity.Categorias;
import org.upemor.ferrechuvis.model.repository.RepositoyCategorias;

public class ControllerCategorias extends Controller<RepositoyCategorias, Categorias> {

    public ControllerCategorias() throws Exception {
        super();
        repository = new RepositoyCategorias();
    }

    protected boolean validate(Categorias obj) throws Exception {
        if (obj.getId() < 0)
            throw new Exception("Error: El Id no puede ser negativo");

        if (obj.getNombre() == null || obj.getNombre().trim().isEmpty())
            throw new Exception("Error: El Nombre de la categoría no puede quedar vacío");

        return true;
    }
}

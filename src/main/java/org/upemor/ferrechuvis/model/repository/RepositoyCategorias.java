package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Categorias;

public class RepositoyCategorias extends Repository<Categorias>{

    public RepositoyCategorias() throws Exception{
        super("Categorias", 2);
    }

    protected Categorias mappingObject(ResultSet rs) throws Exception {
        Categorias categoria = new Categorias();
        categoria.setId(rs.getLong(1));
        categoria.setNombre(rs.getString(2));
        return categoria;
    }

    protected void setStatementParameters(PreparedStatement statement, Categorias obj, boolean objNew) throws Exception {
        int i = 1;
        if (!objNew) statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getNombre());
    }
}

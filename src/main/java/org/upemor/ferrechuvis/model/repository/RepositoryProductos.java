package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Productos;

public class RepositoryProductos extends Repository<Productos>{
    
    public RepositoryProductos()throws Exception{
        super("Productos", 8);
    }

    protected Productos mappingObject(ResultSet rs) throws Exception{
        Productos producto = new Productos();
         producto.setId(rs.getLong(1));
         producto.setCodigo(rs.getString(2));
         producto.setNombre(rs.getString(3));
         producto.setDescripcion(rs.getString(4));
         producto.setCategoria(rs.getString(5));
         producto.setUnidad_medida(rs.getString(6));
         producto.setStock(rs.getInt(7));
         producto.setPrecio(rs.getDouble(8));
        return producto;
    }

    protected void setStatementParameters(PreparedStatement statement, Productos obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getCodigo());
        statement.setString(i++, obj.getNombre());
        statement.setString(i++, obj.getDescripcion());
        statement.setString(i++, obj.getCategoria());
        statement.setString(i++, obj.getUnidad_medida());
        statement.setInt(i++, obj.getStock());
        statement.setDouble(i++, obj.getPrecio());
    }
}

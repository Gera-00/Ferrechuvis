package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Proveedores;

public class RepositoryProveedores extends Repository<Proveedores>{
    public RepositoryProveedores()throws Exception{
        super("Proveedores", 5);
    }

    protected Proveedores mappingObject(ResultSet rs) throws Exception{
        Proveedores proveedor = new Proveedores();
         proveedor.setId(rs.getLong(1));
         proveedor.setNombre(rs.getString(2));
         proveedor.setTelefono(rs.getString(3));
         proveedor.setDireccion(rs.getString(4));
         proveedor.setEmail(rs.getString(5));
         return proveedor;
    }
    
    protected void setStatementParameters(PreparedStatement statement, Proveedores obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getNombre());
        statement.setString(i++, obj.getTelefono());
        statement.setString(i++, obj.getDireccion());
        statement.setString(i++, obj.getEmail());
    }    
}

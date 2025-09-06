package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Productos_Proveedores;

public class RepositoryProductos_Proveedores extends Repository<Productos_Proveedores>{

    public RepositoryProductos_Proveedores()throws Exception{
        super("Productos_Proveedores", 3);
    }

    protected Productos_Proveedores mappingObject(ResultSet rs) throws Exception{
        Productos_Proveedores asignacion = new Productos_Proveedores();
         asignacion.setId(rs.getLong(1));
         asignacion.setId_producto(rs.getLong(2));
         asignacion.setId_proveedor(rs.getLong(3));
        return asignacion;
    }

    protected void setStatementParameters(PreparedStatement statement, Productos_Proveedores obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setLong(i++, obj.getId_producto());
        statement.setLong(i++, obj.getId_proveedor());
    }
}

package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Clientes;

public class RepositoryClientes extends Repository<Clientes>{
    
    public RepositoryClientes()throws Exception{
        super("Clientes",4);
    }

    protected Clientes mappingObject(ResultSet rs) throws Exception{
        Clientes cliente = new Clientes();
         cliente.setId(rs.getLong(1));
         cliente.setNombre(rs.getString(2));
         cliente.setTelefono(rs.getString(3));
         cliente.setDireccion(rs.getString(4));
        return cliente;
    }

    protected void setStatementParameters(PreparedStatement statement, Clientes obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getNombre());
        statement.setString(i++, obj.getTelefono());
        statement.setString(i++, obj.getDireccion());
    }
}

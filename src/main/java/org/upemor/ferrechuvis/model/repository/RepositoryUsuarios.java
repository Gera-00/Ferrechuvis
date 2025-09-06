package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Usuarios;

public class RepositoryUsuarios extends Repository<Usuarios>{

    public RepositoryUsuarios()throws Exception{
        super("Usuarios", 7);
    }

    protected Usuarios mappingObject(ResultSet rs) throws Exception{
        Usuarios usuario = new Usuarios();
         usuario.setId(rs.getLong(1));
         usuario.setNombre(rs.getString(2));
         usuario.setA_paterno(rs.getString((3)));
         usuario.setA_materno(rs.getString((4)));
         usuario.setUsuario(rs.getString((5)));
         usuario.setPassword(rs.getString((6)));
         usuario.setTipo_usuario(rs.getString((7)));
        return usuario;
    }

    protected void setStatementParameters(PreparedStatement statement, Usuarios obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getNombre());
        statement.setString(i++, obj.getA_paterno());
        statement.setString(i++, obj.getA_materno());
        statement.setString(i++, obj.getUsuario());
        statement.setString(i++, obj.getPassword());
        statement.setString(i++, obj.getTipo_usuario());
    }


    
}

package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.upemor.ferrechuvis.model.entity.Tipos_Movimientos;


public class RepositoryTipos_Movimientos extends Repository<Tipos_Movimientos>{
    
    public RepositoryTipos_Movimientos()throws Exception{
        super("Tipos_Movimientos",4);
    }

    protected Tipos_Movimientos mappingObject(ResultSet rs) throws Exception{
        Tipos_Movimientos tipo_movimiento = new Tipos_Movimientos();
         tipo_movimiento.setId(rs.getLong(1));
         tipo_movimiento.setNombre(rs.getString(2));
         tipo_movimiento.setSigno_stock(rs.getInt(3));
         tipo_movimiento.setDescripcion(rs.getString(4));
        return tipo_movimiento;
    }

    protected void setStatementParameters(PreparedStatement statement, Tipos_Movimientos obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getNombre());
        statement.setInt(i++, obj.getSigno_stock());
        statement.setString(i++, obj.getDescripcion());
    }
}

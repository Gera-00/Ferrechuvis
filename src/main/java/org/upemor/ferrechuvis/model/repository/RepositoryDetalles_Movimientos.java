package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Detalles_Movimientos;

public class RepositoryDetalles_Movimientos extends Repository<Detalles_Movimientos>{
    
    public RepositoryDetalles_Movimientos()throws Exception{
        super("Detalles_Movimientos",8);
    }

    protected Detalles_Movimientos mappingObject(ResultSet rs) throws Exception{
        Detalles_Movimientos detalle_movimiento = new Detalles_Movimientos();
         detalle_movimiento.setId(rs.getLong(1));
         detalle_movimiento.setId_producto(rs.getLong(2));
         detalle_movimiento.setId_movimiento(rs.getLong(3));
         detalle_movimiento.setCantidad(rs.getInt(4));
         detalle_movimiento.setStock_anterior(rs.getInt(5));
         detalle_movimiento.setStock_actual(rs.getInt(6));
         detalle_movimiento.setObservaciones(rs.getString(7));
         detalle_movimiento.setPrecio_unitario(rs.getDouble(8));
        return detalle_movimiento;
    }

    protected void setStatementParameters(PreparedStatement statement, Detalles_Movimientos obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setLong(i++, obj.getId_producto());
        statement.setLong(i++, obj.getId_movimiento());
        statement.setLong(i++, obj.getCantidad());
        statement.setLong(i++, obj.getStock_anterior());
        statement.setLong(i++, obj.getStock_actual());
        statement.setString(i++, obj.getObservaciones());
        statement.setDouble(i++, obj.getPrecio_unitario());
    }
}

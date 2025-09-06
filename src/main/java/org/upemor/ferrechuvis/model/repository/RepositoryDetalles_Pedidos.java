package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.upemor.ferrechuvis.model.entity.Detalles_Pedidos;

public class RepositoryDetalles_Pedidos extends Repository<Detalles_Pedidos>{
    
    public RepositoryDetalles_Pedidos()throws Exception{
        super("Detalles_Pedidos",5);
    }

    protected Detalles_Pedidos mappingObject(ResultSet rs) throws Exception{
        Detalles_Pedidos detalle_pedido = new Detalles_Pedidos();
         detalle_pedido.setId(rs.getLong(1));
         detalle_pedido.setId_pedido(rs.getLong(2));
         detalle_pedido.setId_producto(rs.getLong(3));
         detalle_pedido.setCantidad(rs.getInt(4));
         detalle_pedido.setPrecio_unitario(rs.getDouble(5));
        return detalle_pedido;
    }

    protected void setStatementParameters(PreparedStatement statement, Detalles_Pedidos obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setLong(i++, obj.getId_pedido());
        statement.setLong(i++, obj.getId_producto());
        statement.setInt(i++, obj.getCantidad());
        statement.setDouble(i++, obj.getPrecio_unitario());
    }
}

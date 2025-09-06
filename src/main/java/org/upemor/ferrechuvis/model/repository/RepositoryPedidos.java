package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;

import org.upemor.ferrechuvis.model.entity.Pedidos;

public class RepositoryPedidos extends Repository<Pedidos>{
    
    public RepositoryPedidos()throws Exception{
        super("Pedidos",5);
    }

    protected Pedidos mappingObject(ResultSet rs) throws Exception{
        Pedidos pedido = new Pedidos();
         pedido.setId(rs.getLong(1));
         pedido.setId_cliente(rs.getLong(2));
        /*
         * Parcearemos la Fecha
         * Parceada de String a LocalDate, ya que de SQLITE
         * la fecha la recibimos en formato String
        */

        try {
            String fechaString = rs.getString(3);

            if (fechaString!=null && !fechaString.isEmpty()) {
                pedido.setFecha(LocalDate.parse(fechaString));                
            }else{
                pedido.setFecha(null);
                System.out.println("No se ha parseado la fecha || La fecha null");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e+"in"+getClass().getName()
            +"in parseo de Fecha");
        }

         pedido.setTotal(rs.getDouble(4));
         pedido.setEstado(rs.getString(5));
        return pedido;
    }

    protected void setStatementParameters(PreparedStatement statement, Pedidos obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setLong(i++, obj.getId_cliente());
        //Definir Fecha
        if (obj.getFecha() != null) {
            statement.setString(i++, obj.getFecha().toString());
        }else{
            statement.setNull(i++, Types.DATE);
        }
        statement.setDouble(i++, obj.getTotal());
        statement.setString(i++, obj.getEstado());
    }

}

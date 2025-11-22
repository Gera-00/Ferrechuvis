package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.upemor.ferrechuvis.model.entity.Movimientos;

public class RepositoryMovimientos extends Repository<Movimientos>{
    
    public RepositoryMovimientos()throws Exception{
        super("Movimientos",8);
    }

    protected Movimientos mappingObject(ResultSet rs) throws Exception{
        Movimientos movimiento = new Movimientos();
         movimiento.setId(rs.getLong(1));
         movimiento.setCodigo(rs.getString(2));
         movimiento.setId_cliente(rs.getLong(3));
        /*
         * Parcearemos la Fecha
         * Parceada de String a LocalDate, ya que de SQLITE
         * la fecha la recibimos en formato String
        */

        
         movimiento.setId_proveedor(rs.getLong(4));
         movimiento.setId_tipo_movimiento(rs.getLong(5));
         movimiento.setMotivo(rs.getString(6));
         movimiento.setTotal(rs.getDouble(7));

        try {
            String fechaString = rs.getString(8);

            if (fechaString!=null && !fechaString.isEmpty()) {
                movimiento.setFecha(LocalDate.parse(fechaString));                
            }else{
                movimiento.setFecha(null);
                System.out.println("No se ha parseado la fecha || La fecha null");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e+"in"+getClass().getName()
            +"in parseo de Fecha");
        }
        return movimiento;
    }

    protected void setStatementParameters(PreparedStatement statement, Movimientos obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getCodigo());
        statement.setLong(i++, obj.getId_cliente());
        statement.setLong(i++, obj.getId_proveedor());
        statement.setLong(i++, obj.getId_tipo_movimiento());
        statement.setString(i++, obj.getMotivo());
        statement.setDouble(i++, obj.getTotal());
        //Definir Fecha
        if (obj.getFecha() != null) {
            statement.setString(i++, obj.getFecha().toString());
        }else{
            statement.setNull(i++, Types.DATE);
        }
    }
    
    /**
     * Metodo para hacer busquedas por codigo con prefijo,
     * esto para traer todos los resultados de un algun tipo de movimiento
     * por ejemplo: (ventas, entradas, salidas, perdidas, etc)
     * @param prefijo
     * @return List de Movimientos con un tipo de movimiento en especifico
     */
    public List<Movimientos> obtenerMovimientosPorPrefijo(String prefijo){
        List<Movimientos> movimientos = new LinkedList<>();
        String query = "SELECT * FROM Movimientos WHERE codigo LIKE ? ORDER BY codigo DESC";
        try(PreparedStatement statement = myConnection.conectar().prepareStatement(query)){
            statement.setString(1, prefijo+"%");
            ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    movimientos.add(mappingObject(rs));
                }
        }catch (Exception e) {
            System.err.println("Error al obtener movimientos por prefijo '" + prefijo + "': " + e.getMessage());
            e.printStackTrace();
        }
        return movimientos;
    }

}

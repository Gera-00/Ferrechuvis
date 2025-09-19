package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.upemor.ferrechuvis.model.entity.Productos;

public class RepositoryProductos extends Repository<Productos>{
    
    public RepositoryProductos()throws Exception{
        super("Productos", 10);
    }

    protected Productos mappingObject(ResultSet rs) throws Exception{
        Productos producto = new Productos();
         producto.setId(rs.getLong(1));
         producto.setCodigo(rs.getString(2));
         producto.setNombre(rs.getString(3));
         producto.setDescripcion(rs.getString(4));
         producto.setId_categoria(rs.getLong(5));
         producto.setUnidad_medida(rs.getString(6));
         producto.setStock(rs.getInt(7));
         producto.setPrecio(rs.getDouble(8));
         producto.setStock_minimo(rs.getInt(9));
         producto.setLink_imagen(rs.getString(10));
        return producto;
    }

    protected void setStatementParameters(PreparedStatement statement, Productos obj, boolean objNew)throws Exception{
        int i = 1;
        if(!objNew)statement.setLong(i++, obj.getId());
        statement.setString(i++, obj.getCodigo());
        statement.setString(i++, obj.getNombre());
        statement.setString(i++, obj.getDescripcion());
        statement.setLong(i++, obj.getId_categoria());
        statement.setString(i++, obj.getUnidad_medida());
        statement.setInt(i++, obj.getStock());
        statement.setDouble(i++, obj.getPrecio());
        statement.setInt(i++, obj.getStock_minimo());
        statement.setString(i++, obj.getLink_imagen());
    }

    public List<Productos> readByCodigo(String codigo) throws Exception {
        try {
            String queryReadByCodigo = "SELECT * FROM Productos WHERE codigo LIKE ?";
            statment = myConnection.conectar().prepareStatement(queryReadByCodigo);
            statment.setString(1, "%" + codigo + "%"); // Usar LIKE para coincidencias parciales
            ResultSet rs = statment.executeQuery();
            List<Productos> list = new LinkedList<>();
            while (rs.next()) {
                Productos obj = mappingObject(rs);
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()
                    + " in class" + this.getClass().getName()
                    + " in method: readByCodigo");
            throw e;
        }
    }
}

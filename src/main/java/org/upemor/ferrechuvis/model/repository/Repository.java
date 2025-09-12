package org.upemor.ferrechuvis.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;
import org.upemor.ferrechuvis.model.entity.Entity;


/**@author PC**/

public abstract class Repository <T extends Entity> {
    
    protected MiConexion myConnection;
    protected PreparedStatement statment;
    
    
    protected String queryCreate;   // INSERT INTO tabla VALUES(?.....?)
    protected String queryRead;     // SELECT * FROM tabla WHERE in IN (?)
    protected String queryReadByName;     // SELECT * FROM tabla WHERE in IN (?)
    protected String queryUpdate;   // REPLACE INTO tabala VALUES(?....?)
    protected String queryDelete;   // DELETE FROM tabla WHERE id IN (?)
    protected String queryReadAll;   // DELETE FROM tabla WHERE id IN (?)

    
    protected abstract T mappingObject(ResultSet rs) throws Exception;
    protected abstract void setStatementParameters(PreparedStatement statement, T obj, boolean objNew)throws Exception;
    
    public Repository(String table, long parameters) throws Exception{
        try {
            myConnection = MiConexion.getInstancia(); 
            initQueries(table, parameters);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: Repository Constructor");
            throw e;
        }
    }
    
    private void initQueries(String table, long parameters){
        queryCreate = "INSERT INTO "+table+" VALUES(NULL";
            for (int i=1;i<=parameters-1;i++)queryCreate +=",?";
        queryCreate += ")";
        queryRead = "SELECT * FROM "+table+" WHERE id IN (?)";
    queryReadByName = "SELECT * FROM " + table + " WHERE nombre LIKE ?";
        queryReadAll = "SELECT * FROM "+table+"";
        queryUpdate = "REPLACE INTO "+table+" VALUES(";
            for (int i=1;i<=parameters;i++)queryUpdate +=",?";
        queryUpdate += ")";
        queryUpdate = queryUpdate.replace("(,?","(?");
        queryDelete = "DELETE FROM "+table+" WHERE id IN (?)";
    }
    
    public boolean create(T obj) throws Exception{
        try {
            statment = myConnection.conectar().prepareStatement(queryCreate);
            setStatementParameters(statment, obj, true);
            long resultado = statment.executeUpdate();
            return resultado >= 0;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: create");
            throw e;
        }
    }
    
    public T read(long id) throws Exception{
        try {
            statment = myConnection.conectar().prepareStatement(queryRead);
                statment.setLong(1, id);
            ResultSet rs = statment.executeQuery();
            T obj=null;
            while (rs.next()) {
                obj = mappingObject(rs);
            }
            return obj;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: read");
            throw e;
        }
    }
    
    public List<T> readByName(String nombre) throws Exception {
        try {
            statment = myConnection.conectar().prepareStatement(queryReadByName);
            statment.setString(1, "%" + nombre + "%"); // Usar LIKE para coincidencias parciales
            ResultSet rs = statment.executeQuery();
            List<T> list = new LinkedList<>();
            while (rs.next()) {
                T obj = mappingObject(rs);
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()
                    + " in class" + this.getClass().getName()
                    + " in method: readByName");
            throw e;
        }
    }
    

    public List<T> readAll() throws Exception{
        try {
            statment = myConnection.conectar().prepareStatement(queryReadAll);
            ResultSet rs = statment.executeQuery();
            List<T> list = new LinkedList<>();
            T obj=null;
            while (rs.next()) {
                obj = mappingObject(rs);
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: read");
            throw e;
        }
    }
    
    public boolean update(T obj) throws Exception{
        try {
            if(obj.getId() <= 0)throw new Exception("No se puede obtener un id <=0");
            statment = myConnection.conectar().prepareStatement(queryUpdate);
                        setStatementParameters(statment, obj, false);
            long resultado = statment.executeUpdate();
            return resultado >= 0;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: update");
            throw e;
        }
    }
    
    public boolean delete(long id) throws Exception{
        try {          
            statment = myConnection.conectar().prepareStatement(queryDelete);    
            statment.setLong(1, id);
            long resultado = statment.executeUpdate();
            return resultado >= 0;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: delete");
            throw e;
        }
    }
    
}
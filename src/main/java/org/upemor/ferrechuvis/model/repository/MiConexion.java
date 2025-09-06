package org.upemor.ferrechuvis.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;

/**@author PC**/
public class MiConexion {
    public static int SQLITE = 1;
    public static int MARIADB = 2;
    
    private String url;
    private String driverClass;
    private String servidor;
    private long puerto;
    private String baseDeDatos;
    private String usuario;
    private String contrasenia;
    
    private static MiConexion instancia;
    public static MiConexion getInstancia() throws Exception{
        if(instancia == null)
            instancia = new MiConexion(SQLITE);
        return instancia;
    }
    
    private MiConexion(int tipoManejador) throws Exception{
        switch(tipoManejador){
            case 1 -> cargarConfiguracionSqlite();
            case 2 -> cargarConfiguracionMariaDB();
            default -> throw new Exception("El manejador seleccionado no esta configurado");
        }
    }
    
    private void cargarConfiguracionSqlite(){
        driverClass = "org.sqlite.JDBC";
        servidor = "";
        puerto = 0;
        baseDeDatos = "ferrechuvis.db";
        usuario = "";
        contrasenia = "";
        url = "jdbc:sqlite:"+baseDeDatos;
    }
    
    private void cargarConfiguracionMariaDB(){
        driverClass = "org.mariadb.jdbc.Driver";
        servidor = "localhost";
        puerto = 3306;
        baseDeDatos = "ferrechuvisdb";
        usuario = "root";
        contrasenia = "";
        url = "jdbc:mariadb://"+servidor+":"+puerto+"/"+baseDeDatos;
    }
    
    private Connection conexion;
    public Connection conectar() throws Exception{
        try{
            if(conexion == null){
                Class.forName(driverClass);
                conexion = DriverManager.getConnection(url,usuario,contrasenia);
            }
            return conexion;
        }catch(Exception ex){
            System.out.println("Error: "+this.getClass().getName()+" => "+ ex.getMessage());
            throw ex;
        }
    }
    
    public boolean desconectar() throws Exception{
        try{
            if(conexion != null)
                conexion.close();
            return true;
        }catch(Exception ex){
            System.out.println("Error: "+this.getClass().getName()+" => "+ ex.getMessage());
            throw ex;
        }
    }
}

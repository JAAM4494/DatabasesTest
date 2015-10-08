/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasestest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada del manejo y testing del motor de base de datos H2DB.
 * @author jaam
 * @version 2.0
 */
public class H2DBTest {

    private final String DB_DRIVER = "org.h2.Driver";
    private final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private final String DB_USER = "";
    private final String DB_PASSWORD = "";
    
    private Connection connection;
    private Statement stmnt;
    
    /**
     * Constructor de los objetos H2DBTest.
     */
    public H2DBTest()
    {
        
    }
    
    /**
     * Permite ejecutar una sentencia SQL.
     * @param pSQL Sentencia SQL para ser ejecutada.
     */
    public void execute(String pSQL)
    {
        try {
            stmnt.execute(pSQL);
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
    }
    
    /**
     * Permite realizar una consulta SQL.
     * @param pSQL Sentencia de la consulta.
     * @return Retorna un objeto ResultSet con los resultados de la consulta.
     * @see ResultSet
     */
    public ResultSet executeQuery(String pSQL)
    {
        ResultSet outRS = null;
        
        try {
            outRS = stmnt.executeQuery(pSQL);
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
        
        return outRS;
    }
    
    /**
     * Permite inicializar la conexión con una base de datos en memoria
     * utilizando H2DB.
     */
    public void initH2IMDB()
    {
        try {
            connection = getDBConnection();
            connection.setAutoCommit(false);
            stmnt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
    }
    
    /**
     * Cierra la conexion con una base de datos creada.
     */
    public void closeH2IMDB()
    {
        try {
            stmnt.close();
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
    }

    private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
        return dbConnection;
    }
}

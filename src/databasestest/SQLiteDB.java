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
 * Clase encargada del manejo y testing del motor de base de datos SQLite.
 *
 * @author Ed
 * @version 2.0
 */
public class SQLiteDB {

    private final String DB_DRIVER = "org.sqlite.JDBC";
    private Connection connection;
    private Statement stmnt;

    /**
     * Constructor de los objetos SQLite.
     *
     * @param pDBName Nombre de la base de datos.
     * @param pUser Nombre de usuario.
     * @param pPass Contrasena de usuario.
     */
    public SQLiteDB(String pDBName, String pUser, String pPass) {
        try {
            String dbConnection = "jdbc:sqlite:" + pDBName + ".db";
            connection = getDBConnection(dbConnection, pUser, pPass);
            connection.setAutoCommit(false);
            stmnt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
    }

    /**
     * Permite ejecutar una sentencia SQL.
     *
     * @param pSQL Sentencia SQL para ser ejecutada.
     */
    public void execute(String pSQL) {
        try {
            stmnt.execute(pSQL);
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
    }

    /**
     * Permite realizar una consulta SQL.
     *
     * @param pSQL Sentencia de la consulta.
     * @return Retorna un objeto ResultSet con los resultados de la consulta.
     * @see ResultSet
     */
    public ResultSet executeQuery(String pSQL) {
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
     * Cierra la conexion con una base de datos creada.
     */
    public void closeSQLiteDB() {
        try {
            stmnt.close();
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
    }

    private Connection getDBConnection(String pDBConnection, String pUser, String pPass) {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(pDBConnection, pUser, pPass);
            return dbConnection;
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getLocalizedMessage());
        }
        return dbConnection;
    } 
}
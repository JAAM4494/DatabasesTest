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
 * Clase encargada del manejo y testing del motor de base de datos mySQL.
 * @version 2.0
 * @author jaam
 */
public class mySQLDB {
    
     private Connection connection;
     private Statement stmnt;
     
    /**
     * Constructor de los objetos mySQLDB.
     * @param pDBName Nombre de la base de datos.
     * @param pUser Nombre de usuario.
     * @param pPass Contrasena de usuario. 
     */
    public mySQLDB(String pDBName, String pUser,String pPass) {
         try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+pDBName,pUser,pPass);
             stmnt = connection.createStatement();
         } catch (SQLException ex) {
             System.out.println("Exception Message: " + ex.getLocalizedMessage());
         }
     }
     
    /**
     * Permite ejecutar una sentencia SQL.
     * @param pSQL Sentencia SQL para ser ejecutada.
     */
    public void execute(String pSQL) {
         try {
             stmnt.execute(pSQL);
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
    public ResultSet executeQuery(String pSQL) {
        ResultSet outRS = null;
        
         try {
             outRS = stmnt.executeQuery(pSQL);
         } catch (SQLException ex) {
             System.out.println("Exception Message: " + ex.getLocalizedMessage());
         }
        
        return outRS;
     } 
}
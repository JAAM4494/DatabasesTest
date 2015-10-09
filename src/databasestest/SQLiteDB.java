/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package databasestest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Clase encargada del manejo y testing del motor de base de datos SQLite.
 *
 * @author Ed
 * @version 
 */
public class SQLiteDB {


 // public static void main( String args[] )
  //{
     // SQLiteDB mynewDB= new SQLiteDB();
      //mynewDB.createDB("mydb");
      /*String sql = "CREATE TABLE PERSONA " +
                   "(CEDULA INT PRIMARY KEY     NOT NULL," +
                   " NOMBRE VARCHAR(255) )" ;
      mynewDB.createTable("mydb", sql);*/
      
       /*String sql = "INSERT INTO PERSONA (CEDULA,NOMBRE) " +
                   "VALUES (601, 'Ariel' );" ;
      
      mynewDB.insertInto("mydb", sql);
      
       String sql2 = "INSERT INTO PERSONA (CEDULA,NOMBRE) " +
                   "VALUES (602, 'Edwin' );" ;
      
      mynewDB.insertInto("mydb", sql2);
      
       String sql3 = "INSERT INTO PERSONA (CEDULA,NOMBRE) " +
                   "VALUES (603, 'Aaron' );" ;
      
      mynewDB.insertInto("mydb", sql3);*/
      
      
   //   mynewDB.select("mydb", "SELECT * FROM PERSONA;");
      
      
      //mynewDB.update("mydb", "UPDATE PERSONA set CEDULA = 2013115139 where NOMBRE='Edwin';");
      
     // mynewDB.select("mydb", "SELECT * FROM PERSONA;");
      
      //mynewDB.delete("mydb", "DELETE from PERSONA where CEDULA=2013115139;");
      //mynewDB.select("mydb", "SELECT * FROM PERSONA;");
      
      
 // }
  
   /**
     * Permite crear una base de datos SQLite.
     *
     * @param dbName Nombre de la base de datos que se va a crear.
     */
  
  public void createDB(String dbName){
      Connection c = null;
        Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:".concat(dbName).concat(".db"));
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Created database successfully");   
  }
  
    /**
     * Permite realizar la sentencia createTable en la base de datos SQLite.
     *
     * @param dbName Nombre de la base de datos en la cual se va a crear la tabla.
     * @param statement sentencia CREATE TABLE que se va a ejecutar dada por el usuario.
     */
  public void createTable(String dbName, String statement){
        Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:".concat(dbName).concat(".db"));
      System.out.println("Opened database successfully");
      
      stmt = c.createStatement();
      String sql = statement; 
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
  
   /**
     * Permite realizar la sentencia insertInto en la base de datos SQLite.
     *
     * @param dbName Nombre de la base de datos en la cual se va a se va a insertar el nuevo registro.
     * @param statement sentencia INSERT INTO que se va a ejecutar dada por el usuario.
     */
  
  public void insertInto(String dbName, String statement){
     Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:".concat(dbName).concat(".db"));
      c.setAutoCommit(false);
      
      System.out.println("Opened database successfully");
      
      
      stmt = c.createStatement();
      
      String sql = statement;
      
      
      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
  
   /**
     * Permite realizar la sentencia select en la base de datos SQLite.
     *
     * @param dbName Nombre de la base de datos en la cual se va a se va a realizar la operación select.
     * @param statement sentencia SELECT que se va a ejecutar, dada por el usuario.
     */
  public ResultSet select(String dbName, String statement){
      Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:".concat(dbName).concat(".db"));
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( statement);
      
      rs.close();
      stmt.close();
      c.close();
      return rs;

    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
      return null;
  }
  
  /**
     * Permite realizar la sentencia update en la base de datos SQLite.
     *
     * @param dbName Nombre de la base de datos en la cual se va a se va a realizar la operación update.
     * @param statement sentencia UPDATE que se va a ejecutar, dada por el usuario.
     */
  
  public void update (String dBName,String statement){
      Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:".concat(dBName).concat(".db"));
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = statement;
      stmt.executeUpdate(sql);
      c.commit();

      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
  
   /**
     * Permite realizar la sentencia delete en la base de datos SQLite.
     *
     * @param dbName Nombre de la base de datos en la cual se va a se va a realizar la operación delete.
     * @param statement sentencia DELETE que se va a ejecutar, dada por el usuario.
     */
  public void delete (String dBName,String statement){
       Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:".concat(dBName).concat(".db"));
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = statement;
      stmt.executeUpdate(sql);
      c.commit();
      
      
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
      
      
 
  
  
      
      
    
}

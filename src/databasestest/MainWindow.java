/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasestest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaam
 */
public class MainWindow extends javax.swing.JFrame {

    private final SQLiteDB newQuerySQLite = new SQLiteDB("TECDB", "", "");
    private final mySQLDB newQueryMySQL = new mySQLDB("TECDB", "root", "program44");
    private final H2DB newQueryH2DB = new H2DB("TECDB", "", "");
    
    private int iter;
    private long sqLiteDuration;
    private long mySQLDuration;
    private long h2Duration;
    
    private String operation;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.jTextArea1.setEditable(false);

        newQueryH2DB.execute("CREATE TABLE Personas(Cedula int primary key, Nombre varchar(255), Provincia varchar(255))");
        newQueryH2DB.execute("CREATE TABLE Estudiantes(Carne int primary key, Cedula varchar(255), Carrera varchar(255))");
        newQueryH2DB.execute("CREATE TABLE Profesores(ID int primary key, Cedula varchar(255), Escuela varchar(255))");
        
        newQueryH2DB.openServerModeInBrowser("TECDB");
        
        iter = Integer.parseInt(this.jTextField1.getText());
        sqLiteDuration = 0;
        mySQLDuration = 0;
        h2Duration = 0;
        
        operation = "Default"; 
        //newQuerySQLite.execute("CREATE TABLE Personas(Cedula int primary key, Nombre varchar(255), Provincia varchar(255))");
        //newQuerySQLite.execute("CREATE TABLE Estudiantes(Carne int primary key, Cedula varchar(255), Carrera varchar(255))");
        //newQuerySQLite.execute("CREATE TABLE Profesores(ID int primary key, Cedula varchar(255), Escuela varchar(255))");
    }

    private void insertIntoPersonas(int option) {

        int cedula = 600;
        iter = Integer.parseInt(this.jTextField1.getText());

        for (int i = 0; i < iter; i++) {
            String human = "Human" + i;
            if (option == 0) {
                newQuerySQLite.execute("INSERT INTO Personas(Cedula, Nombre) "
                        + "VALUES(" + Integer.toString(cedula + i) + ",'" + human + "')");
            } else if (option == 1) {
                newQueryMySQL.execute("INSERT INTO Personas(Cedula, Nombre) "
                        + "VALUES(" + Integer.toString(cedula + i) + ",'" + human + "')");
            } else {
                newQueryH2DB.execute("INSERT INTO Personas(Cedula, Nombre) "
                        + "VALUES(" + Integer.toString(cedula + i) + ",'" + human + "')");
            }
        }
    }

    private void updatePersonas(int option) {

        iter = Integer.parseInt(this.jTextField1.getText());
        
        for (int i = 0; i < iter; i++) {
            String human = "Human" + i;

            if (option == 0) {
                if (i < 250) {
                    newQuerySQLite.execute("UPDATE Personas SET Provincia =" + "'Alajuela'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 250 & i < 500) {
                    newQuerySQLite.execute("UPDATE Personas SET Provincia =" + "'Cartago'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 500 & i < 750) {
                    newQuerySQLite.execute("UPDATE Personas SET Provincia =" + "'Puntarenas'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 750) {
                    newQuerySQLite.execute("UPDATE Personas SET Provincia =" + "'Guanacaste'" + " WHERE Nombre='" + human + "'");
                }
            } else if (option == 1) {
                if (i < 250) {
                    newQueryMySQL.execute("UPDATE Personas SET Provincia =" + "'Alajuela'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 250 & i < 500) {
                    newQueryMySQL.execute("UPDATE Personas SET Provincia =" + "'Cartago'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 500 & i < 750) {
                    newQueryMySQL.execute("UPDATE Personas SET Provincia =" + "'Puntarenas'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 750) {
                    newQueryMySQL.execute("UPDATE Personas SET Provincia =" + "'Guanacaste'" + " WHERE Nombre='" + human + "'");
                }
            } else {
                if (i < 250) {
                    newQueryH2DB.execute("UPDATE Personas SET Provincia =" + "'Alajuela'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 250 & i < 500) {
                    newQueryH2DB.execute("UPDATE Personas SET Provincia =" + "'Cartago'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 500 & i < 750) {
                    newQueryH2DB.execute("UPDATE Personas SET Provincia =" + "'Puntarenas'" + " WHERE Nombre='" + human + "'");
                }
                if (i >= 750) {
                    newQueryH2DB.execute("UPDATE Personas SET Provincia =" + "'Guanacaste'" + " WHERE Nombre='" + human + "'");
                }
            }
        }
    }

    private void selectPersonas(int option) throws SQLException {

        ResultSet result = null;

        if (option == 0) {
            result = newQuerySQLite.executeQuery("SELECT * FROM Personas;");
        } else if (option == 1) {
            result = newQueryMySQL.executeQuery("SELECT * FROM Personas");
        } else {
            result = newQueryH2DB.executeQuery("SELECT * FROM Personas");
        }

        while (result.next()) {
            this.jTextArea1.append("Cedula: " + result.getString("Cedula") + ", Nombre: " + result.getString("Nombre")
                    + "\nProvincia: " + result.getString("Provincia"));
            this.jTextArea1.append("\n ----------------------------------- \n");
        }
    }

    private void deletePersonas(int option) {

        if (option == 0) {
            newQuerySQLite.execute("DELETE FROM Personas");
        } else if (option == 1) {
            newQueryMySQL.execute("DELETE FROM Personas");
        } else {
            newQueryH2DB.execute("DELETE FROM Personas");
        }
    }

    private void selectPersonasWhere(int option) throws SQLException {
        ResultSet result = null;

        if (option == 0) {
            result = newQuerySQLite.executeQuery("SELECT * FROM Personas WHERE Provincia='Cartago'");
        } else if (option == 1) {
            result = newQueryMySQL.executeQuery("SELECT * FROM Personas WHERE Provincia='Cartago'");
        } else {
            result = newQueryH2DB.executeQuery("SELECT * FROM Personas WHERE Provincia='Cartago'");
        }

        while (result.next()) {
            this.jTextArea1.append("Cedula: " + result.getString("Cedula") + ", Nombre: " + result.getString("Nombre")
                    + "\nProvincia: " + result.getString("Provincia"));
            this.jTextArea1.append("\n ----------------------------------- \n");
        }
    }
    
    private void selectPersonasWhere2(int option) throws SQLException {
        ResultSet result = null;

        if (option == 0) {
            result = newQuerySQLite.executeQuery("SELECT Nombre FROM Personas WHERE Provincia='Guanacaste'");
        } else if (option == 1) {
            result = newQueryMySQL.executeQuery("SELECT Nombre FROM Personas WHERE Provincia='Guanacaste'");
        } else {
            result = newQueryH2DB.executeQuery("SELECT Nombre FROM Personas WHERE Provincia='Guanacaste'");
        }

        while (result.next()) {
            this.jTextArea1.append("Nombre: " + result.getString("Nombre"));
            this.jTextArea1.append("\n ----------------------------------- \n");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        InPerMySQL = new javax.swing.JButton();
        SePerMySQL = new javax.swing.JButton();
        selectWheremySQL = new javax.swing.JButton();
        UpPerMySQL = new javax.swing.JButton();
        dePerMySQL = new javax.swing.JButton();
        selectWhere2mySQL = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        InPerSQLite = new javax.swing.JButton();
        SePerSQLite = new javax.swing.JButton();
        selectWhereQLite = new javax.swing.JButton();
        UpPerSQLite = new javax.swing.JButton();
        dePerSQLite = new javax.swing.JButton();
        selectWhere2QLite = new javax.swing.JButton();
        InPerH2DB = new javax.swing.JButton();
        SePerH2DB = new javax.swing.JButton();
        selectWhereH2 = new javax.swing.JButton();
        UpPerH2DB = new javax.swing.JButton();
        dePerH2DB = new javax.swing.JButton();
        selectWhere2H2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        generateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel1.setText("mySQL");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel2.setText("SQLite");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel3.setText("H2DB");

        InPerMySQL.setText("Insert Personas");
        InPerMySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InPerMySQLMouseClicked(evt);
            }
        });

        SePerMySQL.setText("Select * Personas");
        SePerMySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SePerMySQLMouseClicked(evt);
            }
        });
        SePerMySQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SePerMySQLActionPerformed(evt);
            }
        });

        selectWheremySQL.setText("Select * Personas Where 1");
        selectWheremySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectWheremySQLMouseClicked(evt);
            }
        });

        UpPerMySQL.setText("Update Personas");
        UpPerMySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpPerMySQLMouseClicked(evt);
            }
        });

        dePerMySQL.setText("Delete Personas");
        dePerMySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dePerMySQLMouseClicked(evt);
            }
        });

        selectWhere2mySQL.setText("Select * Personas Where 2");
        selectWhere2mySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectWhere2mySQLMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel4.setText("Output:");

        InPerSQLite.setText("Insert Personas");
        InPerSQLite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InPerSQLiteMouseClicked(evt);
            }
        });

        SePerSQLite.setText("Select * Personas");
        SePerSQLite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SePerSQLiteMouseClicked(evt);
            }
        });

        selectWhereQLite.setText("Select * Personas Where 1");
        selectWhereQLite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectWhereQLiteMouseClicked(evt);
            }
        });
        selectWhereQLite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectWhereQLiteActionPerformed(evt);
            }
        });

        UpPerSQLite.setText("Update Personas");
        UpPerSQLite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpPerSQLiteMouseClicked(evt);
            }
        });

        dePerSQLite.setText("Delete Personas");
        dePerSQLite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dePerSQLiteMouseClicked(evt);
            }
        });

        selectWhere2QLite.setText("Select * Personas Where 2");
        selectWhere2QLite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectWhere2QLiteMouseClicked(evt);
            }
        });

        InPerH2DB.setText("Insert Personas");
        InPerH2DB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InPerH2DBMouseClicked(evt);
            }
        });

        SePerH2DB.setText("Select * Personas");
        SePerH2DB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SePerH2DBMouseClicked(evt);
            }
        });

        selectWhereH2.setText("Select * Personas Where 1");
        selectWhereH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectWhereH2MouseClicked(evt);
            }
        });

        UpPerH2DB.setText("Update Personas");
        UpPerH2DB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpPerH2DBMouseClicked(evt);
            }
        });

        dePerH2DB.setText("Delete Personas");
        dePerH2DB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dePerH2DBMouseClicked(evt);
            }
        });

        selectWhere2H2.setText("Select * Personas Where 2");
        selectWhere2H2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectWhere2H2MouseClicked(evt);
            }
        });

        generateBtn.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        generateBtn.setText("Generate Graph");
        generateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateBtnMouseClicked(evt);
            }
        });

        clearBtn.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        clearBtn.setText("Clear Output");
        clearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearBtnMouseClicked(evt);
            }
        });

        jTextField1.setText("1000");
        jTextField1.setToolTipText("Iterations Number");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(clearBtn))
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(selectWhereH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SePerMySQL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InPerMySQL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectWheremySQL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InPerH2DB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SePerH2DB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(selectWhere2mySQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dePerMySQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UpPerMySQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(UpPerH2DB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dePerH2DB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectWhere2H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(selectWhereQLite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InPerSQLite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SePerSQLite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UpPerSQLite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dePerSQLite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectWhere2QLite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InPerMySQL)
                            .addComponent(UpPerMySQL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SePerMySQL)
                            .addComponent(dePerMySQL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectWheremySQL)
                            .addComponent(selectWhere2mySQL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InPerSQLite)
                            .addComponent(UpPerSQLite))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SePerSQLite)
                            .addComponent(dePerSQLite))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectWhereQLite)
                            .addComponent(selectWhere2QLite))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InPerH2DB)
                            .addComponent(UpPerH2DB))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SePerH2DB)
                            .addComponent(dePerH2DB))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectWhereH2)
                            .addComponent(selectWhere2H2))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generateBtn)
                            .addComponent(clearBtn)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        jTextField1.getAccessibleContext().setAccessibleName("Iterations Number");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InPerMySQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InPerMySQLMouseClicked
        long start = System.currentTimeMillis();
	insertIntoPersonas(1);
	long duracion = System.currentTimeMillis()-start;
        mySQLDuration = duracion;
        this.operation = "Insert INTO Personas";
    }//GEN-LAST:event_InPerMySQLMouseClicked

    private void InPerSQLiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InPerSQLiteMouseClicked
        long start = System.currentTimeMillis();
	insertIntoPersonas(0);
	long duracion = System.currentTimeMillis()-start;
        sqLiteDuration = duracion;
        this.operation = "Insert INTO Personas";
    }//GEN-LAST:event_InPerSQLiteMouseClicked

    private void InPerH2DBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InPerH2DBMouseClicked
        long start = System.currentTimeMillis();
	insertIntoPersonas(2);
	long duracion = System.currentTimeMillis()-start;
        h2Duration = duracion;
        this.operation = "Insert INTO Personas";
    }//GEN-LAST:event_InPerH2DBMouseClicked

    private void UpPerMySQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpPerMySQLMouseClicked
        long start = System.currentTimeMillis();
	updatePersonas(1);
	long duracion = System.currentTimeMillis()-start;
        mySQLDuration = duracion;
        this.operation = "Update Personas";
    }//GEN-LAST:event_UpPerMySQLMouseClicked

    private void UpPerSQLiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpPerSQLiteMouseClicked
        long start = System.currentTimeMillis();
	updatePersonas(0);
	long duracion = System.currentTimeMillis()-start;
        sqLiteDuration = duracion;
        this.operation = "Update Personas";
    }//GEN-LAST:event_UpPerSQLiteMouseClicked

    private void UpPerH2DBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpPerH2DBMouseClicked
        long start = System.currentTimeMillis();
	updatePersonas(2);
	long duracion = System.currentTimeMillis()-start;
        h2Duration = duracion;
        this.operation = "Update Personas";
    }//GEN-LAST:event_UpPerH2DBMouseClicked

    private void SePerMySQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SePerMySQLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SePerMySQLActionPerformed

    private void SePerMySQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SePerMySQLMouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonas(1);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        mySQLDuration = duracion;
        this.operation = "Select * FROM Personas";
    }//GEN-LAST:event_SePerMySQLMouseClicked

    private void SePerSQLiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SePerSQLiteMouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonas(0);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        sqLiteDuration = duracion;
        this.operation = "Select * FROM Personas";
    }//GEN-LAST:event_SePerSQLiteMouseClicked

    private void SePerH2DBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SePerH2DBMouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonas(2);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        h2Duration = duracion;
        this.operation = "Select * FROM Personas";
    }//GEN-LAST:event_SePerH2DBMouseClicked

    private void dePerMySQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dePerMySQLMouseClicked
        long start = System.currentTimeMillis();
	deletePersonas(1);
	long duracion = System.currentTimeMillis()-start;
        mySQLDuration = duracion;
        this.operation = "Delete Personas";
    }//GEN-LAST:event_dePerMySQLMouseClicked

    private void dePerSQLiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dePerSQLiteMouseClicked
        long start = System.currentTimeMillis();
	deletePersonas(0);
	long duracion = System.currentTimeMillis()-start;
        sqLiteDuration = duracion;
        this.operation = "Delete Personas";
    }//GEN-LAST:event_dePerSQLiteMouseClicked

    private void dePerH2DBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dePerH2DBMouseClicked
        long start = System.currentTimeMillis();
	deletePersonas(2);
	long duracion = System.currentTimeMillis()-start;
        h2Duration = duracion;
        this.operation = "Delete Personas";
    }//GEN-LAST:event_dePerH2DBMouseClicked

    private void clearBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBtnMouseClicked
        this.jTextArea1.setText("");
    }//GEN-LAST:event_clearBtnMouseClicked

    private void selectWheremySQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectWheremySQLMouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonasWhere(1);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        mySQLDuration = duracion;
        this.operation = "Select * FROM Personas WHERE Provincia='Cartago'";
    }//GEN-LAST:event_selectWheremySQLMouseClicked

    private void selectWhereQLiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectWhereQLiteMouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonasWhere(0);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        sqLiteDuration = duracion;
        this.operation = "Select * FROM Personas WHERE Provincia='Cartago'";
    }//GEN-LAST:event_selectWhereQLiteMouseClicked

    private void selectWhereH2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectWhereH2MouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonasWhere(2);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        h2Duration = duracion;
        this.operation = "Select * FROM Personas WHERE Provincia='Cartago'";
    }//GEN-LAST:event_selectWhereH2MouseClicked

    private void selectWhere2mySQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectWhere2mySQLMouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonasWhere2(1);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        mySQLDuration = duracion;
        this.operation = "Select Nombre FROM Personas WHERE Provincia='Guanacaste'";
    }//GEN-LAST:event_selectWhere2mySQLMouseClicked

    private void selectWhere2QLiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectWhere2QLiteMouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonasWhere2(0);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        sqLiteDuration = duracion;
        this.operation = "Select Nombre FROM Personas WHERE Provincia='Guanacaste'";
    }//GEN-LAST:event_selectWhere2QLiteMouseClicked

    private void selectWhere2H2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectWhere2H2MouseClicked
        long start = System.currentTimeMillis();
	try {
            selectPersonasWhere2(2);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	long duracion = System.currentTimeMillis()-start;
        h2Duration = duracion;
        this.operation = "Select Nombre FROM Personas WHERE Provincia='Guanacaste'";
    }//GEN-LAST:event_selectWhere2H2MouseClicked

    private void selectWhereQLiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectWhereQLiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectWhereQLiteActionPerformed

    private void generateBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateBtnMouseClicked
        Graficos grafico = new Graficos();
        grafico.graficoBarras(operation, (double)mySQLDuration, (double)sqLiteDuration, (double)h2Duration);
    }//GEN-LAST:event_generateBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InPerH2DB;
    private javax.swing.JButton InPerMySQL;
    private javax.swing.JButton InPerSQLite;
    private javax.swing.JButton SePerH2DB;
    private javax.swing.JButton SePerMySQL;
    private javax.swing.JButton SePerSQLite;
    private javax.swing.JButton UpPerH2DB;
    private javax.swing.JButton UpPerMySQL;
    private javax.swing.JButton UpPerSQLite;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton dePerH2DB;
    private javax.swing.JButton dePerMySQL;
    private javax.swing.JButton dePerSQLite;
    private javax.swing.JButton generateBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton selectWhere2H2;
    private javax.swing.JButton selectWhere2QLite;
    private javax.swing.JButton selectWhere2mySQL;
    private javax.swing.JButton selectWhereH2;
    private javax.swing.JButton selectWhereQLite;
    private javax.swing.JButton selectWheremySQL;
    // End of variables declaration//GEN-END:variables
}

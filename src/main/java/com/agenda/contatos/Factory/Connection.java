package com.agenda.contatos.Factory;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.ConnectionImpl;
// import com.mysql.cj.jdbc.Driver;


public class Connection {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/agenda";
    private static final String USER = "root";
    private static final String PASS = "0904@24m";


    //ConnectionImpl connection = null;

    public static ConnectionImpl getConnection() throws SQLException{
        try {
            Class.forName(DRIVER);
            return (ConnectionImpl) DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro na conexão: ", e);
        }
    }

    public static void closeConnection(ConnectionImpl connection) throws SQLException{
        if(connection != null){
            connection.close();
        }
    }

}

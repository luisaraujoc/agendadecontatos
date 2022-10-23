package com.agenda.contatos.DAO;

import com.agenda.contatos.Factory.Connection;
import com.agenda.contatos.Model.Contatos;
import com.mysql.cj.exceptions.SSLParamsException;
import com.mysql.cj.jdbc.ConnectionImpl;
//import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import java.sql.ResultSet;
// import java.sql.Connection;
// import java.sql.Date;
// import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatosDAO {
    
    public static void salvarContato(Contatos contato) throws SQLException{
        ConnectionImpl conn = Connection.getConnection();
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";
        try {
            ClientPreparedStatement stmt = (ClientPreparedStatement) conn.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.execute();
        } catch (SSLParamsException e) {
            System.out.println("Erro ao salvar contato: " + e);
        }finally{
            Connection.closeConnection(Connection.getConnection());
        }
    }

    
}
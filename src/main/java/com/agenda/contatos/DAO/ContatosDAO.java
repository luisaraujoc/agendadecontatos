package com.agenda.contatos.DAO;

import com.agenda.contatos.Factory.Connection;
import com.agenda.contatos.Model.Contatos;
import com.mysql.cj.exceptions.SSLParamsException;
import com.mysql.cj.jdbc.ConnectionImpl;
//import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public static List<Contatos> getContatos() {
        String sql = "SELECT * FROM contatos";
        List<Contatos> lContatos = new ArrayList<Contatos>();

        ConnectionImpl conn = null;
        ClientPreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = Connection.getConnection();
            stmt = (ClientPreparedStatement) conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                Contatos contato = new Contatos();
                contato.setId(rs.getInt("idContato"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                lContatos.add(contato);
            }
        }catch (SQLException e){
                System.out.println("Erro ao buscar contatos: " + e);
            }finally{
                if(rs != null){
                    try{
                        rs.close();
                    }catch (SQLException e){
                        System.out.println("Erro ao fechar o ResultSet: " + e);
                    }
                }
                if(stmt != null){
                    try{
                        stmt.close();
                    }catch (SQLException e){
                        System.out.println("Erro ao fechar o Statement: " + e);
                    }
                }
                if(conn != null){
                    try{
                        conn.close();
                    }catch (SQLException e){
                        System.out.println("Erro ao fechar a conexão: " + e);
                    }
                }
            }
            return lContatos;
    }

    public static void deletarContato(int id) throws SQLException{
        ConnectionImpl conn = Connection.getConnection();
        String sql = "DELETE FROM contatos WHERE idContato = ?";
        try {
            ClientPreparedStatement stmt = (ClientPreparedStatement) conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SSLParamsException e) {
            System.out.println("Erro ao deletar contato: " + e);
        }finally{
            Connection.closeConnection(Connection.getConnection());
        }
    }

    public static void atualizarContato(Contatos contato) throws SQLException{
        ConnectionImpl conn = Connection.getConnection();
        String sql = "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE idContato = ?";
        try {
            ClientPreparedStatement stmt = (ClientPreparedStatement) conn.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setInt(4, contato.getId());
            stmt.execute();
        } catch (SSLParamsException e) {
            System.out.println("Erro ao atualizar contato: " + e);
        }finally{
            Connection.closeConnection(Connection.getConnection());
        }
    }

    public static Contatos getContatoById(int id) {
        String sql = "SELECT * FROM contatos WHERE idContato = ?";
        Contatos contato = new Contatos();

        ConnectionImpl conn = null;
        ClientPreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = Connection.getConnection();
            stmt = (ClientPreparedStatement) conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while(rs.next()){
                contato.setId(rs.getInt("idContato"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
            }
        }catch (SQLException e){
            System.out.println("Erro ao buscar contato: " + e);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
                    System.out.println("Erro ao fechar o ResultSet: " + e);
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    System.out.println("Erro ao fechar o Statement: " + e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    System.out.println("Erro ao fechar a conexão: " + e);
                }
            }
        }
        return contato;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import kinds.Atendente;
import sistema.ConnectionPooler;

/**
 *
 * @author jonasrg
 */
public class AtendenteDAO {
    public static boolean criaAtendente (Atendente atendente) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("INSERT INTO atendente (cpf, nome, endereco, telefone, email) "
                    + "VALUES (?, ?, ?, ?, ?);");
            stmt.setString(1, atendente.getCpf());
            stmt.setString(2, atendente.getNome());
            stmt.setString(3, atendente.getEndereco());
            stmt.setString(4, atendente.getTelefone());
            stmt.setString(5, atendente.getEmail());
            if (stmt.executeUpdate() < 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionPooler.closeStatement(stmt);
            ConnectionPooler.closeConnection(dbh);
        }
    }
    
    public static ArrayList<Atendente> getAtendente (String cpf, String nome) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cpf = "%" + cpf + "%";
        nome = "%" + nome + "%";
        
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("SELECT * FROM atendente WHERE cpf LIKE ? AND nome LIKE ?;");
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            rs = stmt.executeQuery();
            
            ArrayList<Atendente> atendentes = new ArrayList<Atendente>();
            
            Atendente atendente;
            while (rs.next()) {
                atendente = new Atendente();
                
                atendente.setId(rs.getInt("id"));
                atendente.setCpf(rs.getString("cpf"));
                atendente.setEmail(rs.getString("email"));
                atendente.setEndereco(rs.getString("endereco"));
                atendente.setNome(rs.getString("nome"));
                atendente.setTelefone(rs.getString("telefone"));
                
                atendentes.add(atendente);
            }
            
            return atendentes;
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionPooler.closeResultset(rs);
            ConnectionPooler.closeStatement(stmt);
            ConnectionPooler.closeConnection(dbh);
        }
    }
}

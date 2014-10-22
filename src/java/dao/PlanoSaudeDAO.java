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
import kinds.PlanoSaude;
import sistema.ConnectionPooler;

/**
 *
 * @author jonasrg
 */
public class PlanoSaudeDAO {
    public static boolean criaPlanoSaude (PlanoSaude planoSaude) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("INSERT INTO planoSaude (cnpj, nome, endereco, contato, telefone, email) "
                    + "VALUES (?, ?, ?, ?, ?, ?);");
            stmt.setString(1, planoSaude.getCnpj());
            stmt.setString(2, planoSaude.getNome());
            stmt.setString(3, planoSaude.getEndereco());
            stmt.setString(4, planoSaude.getContato());
            stmt.setString(5, planoSaude.getTelefone());
            stmt.setString(6, planoSaude.getEmail());
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
    
    public static ArrayList<PlanoSaude> getPlanoSaude (String cnpj) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cnpj = "%" + cnpj + "%";
        
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("SELECT * FROM planoSaude WHERE cnpj LIKE ?;");
            stmt.setString(1, cnpj);
            rs = stmt.executeQuery();
            
            ArrayList<PlanoSaude> planosSaude = new ArrayList<PlanoSaude>();
            
            PlanoSaude planoSaude;
            while (rs.next()) {
                planoSaude = new PlanoSaude();
                
                planoSaude.setId(rs.getInt("id"));
                planoSaude.setCnpj(rs.getString("cnpj"));
                planoSaude.setEmail(rs.getString("email"));
                planoSaude.setEndereco(rs.getString("endereco"));
                planoSaude.setNome(rs.getString("nome"));
                planoSaude.setTelefone(rs.getString("telefone"));
                planoSaude.setContato(rs.getString("contato"));
                
                planosSaude.add(planoSaude);
            }
            
            return planosSaude;
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionPooler.closeResultset(rs);
            ConnectionPooler.closeStatement(stmt);
            ConnectionPooler.closeConnection(dbh);
        }
    }
    
    public static PlanoSaude getPlanoSaude (int id) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("SELECT * FROM planoSaude WHERE id = ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            PlanoSaude planoSaude = new PlanoSaude();
            
            if (rs.next()) {
                planoSaude.setId(rs.getInt("id"));
                planoSaude.setCnpj(rs.getString("cnpj"));
                planoSaude.setEmail(rs.getString("email"));
                planoSaude.setEndereco(rs.getString("endereco"));
                planoSaude.setNome(rs.getString("nome"));
                planoSaude.setTelefone(rs.getString("telefone"));
                planoSaude.setContato(rs.getString("contato"));
            }
            
            return planoSaude;
            
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionPooler.closeResultset(rs);
            ConnectionPooler.closeStatement(stmt);
            ConnectionPooler.closeConnection(dbh);
        }
    }
}

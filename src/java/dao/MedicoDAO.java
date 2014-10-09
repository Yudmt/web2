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
import kinds.Medico;
import sistema.ConnectionPooler;

/**
 *
 * @author reddo
 */
public class MedicoDAO {
    public static boolean criaMedico (Medico medico) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("INSERT INTO medico (cpf, nome, endereco, telefone, email, crm, especialidade) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, medico.getCpf());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getEndereco());
            stmt.setString(4, medico.getTelefone());
            stmt.setString(5, medico.getEmail());
            stmt.setString(6, medico.getCrm());
            stmt.setString(7, medico.getEspecialidade());
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
    
    public static ArrayList<Medico> getMedicos (String crm, String especialidade) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        crm = "%" + crm + "%";
        especialidade = "%" + especialidade + "%";
        
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("SELECT * FROM medico WHERE crm LIKE ? AND especialidade LIKE ?;");
            stmt.setString(1, crm);
            stmt.setString(2, especialidade);
            rs = stmt.executeQuery();
            
            ArrayList<Medico> medicos = new ArrayList<Medico>();
            
            Medico medico;
            while (rs.next()) {
                medico = new Medico();
                
                medico.setId(rs.getInt("id"));
                medico.setCpf(rs.getString("cpf"));
                medico.setCrm(rs.getString("crm"));
                medico.setEmail(rs.getString("email"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setNome(rs.getString("nome"));
                medico.setTelefone(rs.getString("telefone"));
                
                medicos.add(medico);
            }
            
            return medicos;
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionPooler.closeResultset(rs);
            ConnectionPooler.closeStatement(stmt);
            ConnectionPooler.closeConnection(dbh);
        }
    }
}

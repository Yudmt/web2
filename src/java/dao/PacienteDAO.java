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
import kinds.Paciente;
import sistema.ConnectionPooler;

/**
 *
 * @author jonasrg
 */
public class PacienteDAO {
    /*
    
    
    @Expose private String cpf;
    @Expose private String nome;
    @Expose private String endereco;
    @Expose private String telefone;
    @Expose private String email;
    @Expose private PlanoSaude planoSaude;
    @Expose private int numPlano;
    @Expose private String tipoPlano;
    @Expose private String dataCadastro;
    
    */
    public static boolean criaPaciente (Paciente paciente) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("INSERT INTO medico (cpf, nome, endereco, telefone, email, planoSaude, numPlano, tipoPlano, dataCadastro) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getEndereco());
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEmail());
            stmt.setInt(6, paciente.getPlanoSaude().getId());
            stmt.setInt(7, paciente.getNumPlano());
            stmt.setString(8, paciente.getTipoPlano());
            stmt.setString(9, paciente.getDataCadastro());
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
    
    public static ArrayList<Paciente> getPacientes (String cpf, String nome) {
        Connection dbh = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cpf = "%" + cpf + "%";
        nome = "%" + nome + "%";
        
        try {
            dbh = ConnectionPooler.getConnection();
            stmt = dbh.prepareStatement("SELECT * FROM paciente WHERE cpf LIKE ? AND nome LIKE ?;");
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            rs = stmt.executeQuery();
            
            ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
            
            Paciente paciente;
            while (rs.next()) {
                paciente = new Paciente();
                
                paciente.setId(rs.getInt("id"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEmail(rs.getString("email"));
                paciente.setNumPlano(rs.getInt("numPlano"));
                paciente.setTipoPlano(rs.getString("tipoPlano"));
                paciente.setDataCadastro(rs.getString("dataCadastro"));
                paciente.setPlanoSaude(PlanoSaudeDAO.getPlanoSaude(rs.getInt("planoSaude")));
                
                pacientes.add(paciente);
            }
            
            return pacientes;
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionPooler.closeResultset(rs);
            ConnectionPooler.closeStatement(stmt);
            ConnectionPooler.closeConnection(dbh);
        }
    }
}

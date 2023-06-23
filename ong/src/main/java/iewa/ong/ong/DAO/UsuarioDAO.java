package iewa.ong.ong.DAO;

import iewa.ong.ong.DTO.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Connection conn;
    PreparedStatement pstm;

    public boolean fazerLogin(String email, String senha) throws SQLException {
        conn = new ConexaoDAO().Connect();
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;

        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, senha);
            resultSet = preparedStmt.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStmt != null) {
                preparedStmt.close();
            }
        }

        return false; 
    }

    public void CriarUsuario(UsuarioDTO user) throws SQLException {
        conn = new ConexaoDAO().Connect();
        String sql = "INSERT INTO usuarios (razaoSocial, email, senha, telefone, CP, endereco) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, user.getRazaoSocial());
            preparedStmt.setString(2, user.getEmail());
            preparedStmt.setString(3, user.getSenha());
            preparedStmt.setString(4, user.getTelefone());
            preparedStmt.setString(5, user.getCP());
            preparedStmt.setString(6, user.getEndereco());
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UsuarioDTO> ListarUsuarios(Connection conn) throws SQLException {
        conn = new ConexaoDAO().Connect();
        List<UsuarioDTO> usuarios = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
        try {
            while (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setId(rs.getInt("id"));
                usuario.setRazaoSocial(rs.getString("razaoSocial"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve users from the database", e);
        }
        return usuarios;
    }

    public void atualizarUsuario(int id, String razaoSocial, String email) throws SQLException {
        conn = new ConexaoDAO().Connect();
        PreparedStatement stmt = conn
                .prepareStatement("UPDATE usuarios SET razaoSocial=?, email=?, telefone=?, CP=?, endereco WHERE id=?");
        try {
            stmt.setString(1, razaoSocial);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user in the database", e);
        }
    }

    public void deletarUsuario(int id) throws SQLException {
        conn = new ConexaoDAO().Connect();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id=?");
        try {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user from the database", e);
        }
    }

}

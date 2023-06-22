package iewa.ong.ong.DAO;

import iewa.ong.ong.DTO.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Connection conn;
    PreparedStatement pstm;

    public void CriarUsuario(UsuarioDTO user) throws SQLException {
        conn = new ConexaoDAO().Connect();
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, user.getNome());
            preparedStmt.setString(2, user.getEmail());
            preparedStmt.setString(3, user.getSenha());
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
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        }catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve users from the database", e);
        }
        return usuarios;
    }

    public void atualizarUsuario(int id, String nome, String email) throws SQLException {
        conn = new ConexaoDAO().Connect();
        PreparedStatement stmt = conn.prepareStatement("UPDATE usuarios SET nome=?, email=? WHERE id=?");
        try {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            stmt.executeUpdate();
            
        }catch (SQLException e) {
            throw new RuntimeException("Failed to update user in the database", e);
        }
    }

    public void deletarUsuario(int id) throws SQLException {
        conn = new ConexaoDAO().Connect();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id=?");
        try {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException("Failed to delete user from the database", e);
        }
    }

}

package iewa.ong.ong.DAO;

import iewa.ong.ong.DTO.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Connection conn;
    PreparedStatement pstm;
    String BD = "ongs";
    String localBD = "localhost";
    String usuario = "root";
    String senha = "";
    String url = "jdbc:mysql://" + localBD + ":3306/" + BD;

    public void CriarUsuario(UsuarioDTO user) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?,?,?)";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = DriverManager.getConnection(url, usuario, senha).prepareStatement(sql);
            preparedStmt.setString(1, user.getNome());
            preparedStmt.setString(2, user.getEmail());
            preparedStmt.setString(3, user.getSenha());
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UsuarioDTO> ListarUsuarios() throws SQLException {
        List<UsuarioDTO> usuarios = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {

            while (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve users from the database", e);
        }
        return usuarios;
    }

}

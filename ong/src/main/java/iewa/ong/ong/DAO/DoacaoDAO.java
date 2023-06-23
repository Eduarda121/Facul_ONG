package iewa.ong.ong.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iewa.ong.ong.DTO.DoacaoDTO;

public class DoacaoDAO {
    Connection conn;
    PreparedStatement pstm;

    public void CriarDoacao(DoacaoDTO doacao) throws SQLException {
        conn = new ConexaoDAO().Connect();
        String sql = "INSERT INTO doacoes (descricao, nomeDoador, quantia, quantidade, data) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, doacao.getDescricao());
            preparedStmt.setString(2, doacao.getNomeDoador());
            preparedStmt.setFloat(3, doacao.getQuantia());
            preparedStmt.setInt(4, doacao.getQuantidade());
            preparedStmt.setDate(5, new java.sql.Date(System.currentTimeMillis())); // Insere a data atual
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DoacaoDTO> ListarDoacaos(Connection conn) throws SQLException {
        conn = new ConexaoDAO().Connect();
        List<DoacaoDTO> doacoes = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM doacoes");
        try {
            while (rs.next()) {
                DoacaoDTO doacao = new DoacaoDTO();
                doacao.setId(rs.getInt("id"));
                doacao.setDescricao(rs.getString("descricao"));
                doacao.setNomeDoador(rs.getString("nomeDoador"));
                doacao.setQuantia(rs.getFloat("quantia"));
                doacao.setQuantidade(rs.getInt("quantidade"));
                doacao.setData(rs.getDate("data"));
                doacoes.add(doacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve doacoes from the database", e);
        }
        return doacoes;
    }

    public void atualizarDoacao(DoacaoDTO doacao, int id) throws SQLException {
        conn = new ConexaoDAO().Connect();
        PreparedStatement stmt = conn.prepareStatement("UPDATE doacoes SET descricao=?, nomeDoador=?, quantia=?, quantidade=? WHERE id=?");
        try {
            stmt.setString(1, doacao.getDescricao());
            stmt.setString(2, doacao.getNomeDoador());
            stmt.setFloat(3, doacao.getQuantia());
            stmt.setInt(4, doacao.getQuantidade());
            stmt.setInt(5, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update doacao in the database", e);
        }
    }

    public void deletarDoacao(int id) throws SQLException {
        conn = new ConexaoDAO().Connect();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM doacoes WHERE id=?");
        try {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete doacao from the database", e);
        }
    }

}

package iewa.ong.ong.DAO;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConexaoDAO {
    public Connection Connect() {
        Connection conn = null;
        String BD = "ongs";
        String localBD = "localhost";
        String usuario = "root";
        Number port = 3307;
        String senha = "";

        try {
            String url = "jdbc:mysql://" + localBD + ":"+ port+ "/" + BD;
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("conexão OK!");

        } catch (SQLException erro) {
            JOptionPane.showConfirmDialog(null, "ConexaoDAO" + erro.getMessage());
        }
        return conn;

    }

}

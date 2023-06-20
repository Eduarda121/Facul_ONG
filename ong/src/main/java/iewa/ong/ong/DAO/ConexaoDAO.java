package iewa.ong.ong.DAO;

import java.sql.*;  

import javax.swing.JOptionPane;

public class ConexaoDAO {
    public Connection connect() {
        try {
            Connection conn;
            String BD = "ongs";
            String localBD = "localhost";
            String usuario = "root";
            String senha = "";
            String url = "jdbc:mysql://" + localBD + ":3306/" + BD;

            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("conexão OK!");

        } catch (SQLException erro) {
            JOptionPane.showConfirmDialog(null, "ConexaoDAO" + erro.getMessage());
        }
        return null;

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexaoDAO {
    public Connection conectaCDB2 () {
       Connection conn = null; 
       
        try {
            String url = "jdbc.mysql://localhost:3306/projetojava";
            conn = DriverManager.getConnection(url);
                    
        } catch (SQLException erro) {
            JOptionPane.showConfirmDialog(null, "ConexaoDAO" + erro.getMessage());
            
        }
        return null;
       
       
    }
    
}

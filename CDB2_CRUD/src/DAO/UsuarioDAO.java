/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.usuarioaDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    Connection conn;

    public ResultSet autenticacaousuario(usuarioaDTO objusuariodto) {
        conn = new ConexaoDAO().conectaCDB2();

        try {
            String sql = "select *from usuario where nome_usuario = ? and senha_usuario = ?";
           
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objusuariodto.getNome_usuario());
            pstm.setString(2, objusuariodto.getSenha_usuario());
            
            ResultSet rs =  pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
            return null;

        }

    }

}

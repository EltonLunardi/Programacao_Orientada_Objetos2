package com.mycompany.bibliofx;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

public class LoginController {
    
    @FXML
    private TextField usuario;
    
    @FXML
    private TextField senha;
    
    @FXML
    private Button botaoLogar;
    
    @FXML
    private Button botaoCadastrar;
    
    @FXML
    private void handleLogar() throws IOException {
        Connection conn = ConectarDB.conn;
        try {
            String user = usuario.getText();
            String pass = senha.getText();
            if (user.equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                return;
            }
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from usuarios  WHERE usuario = '" + user + "';");
            if (user.equals("admin") && pass.equals("admin")) {
                Sessao.sessao = user;
                App.setRoot("mainAdmin");
                return;
            }
            boolean login_correto = false;
            while (result.next()) {
                if (result.getString(2).equals(user) && result.getString(3).equals(pass)) {
                    Sessao.id = Integer.parseInt(result.getString(1));
                    Sessao.sessao = user;
                    login_correto = true;
                    break;
                }
            }
            if (login_correto) {
                App.setRoot("mainUser");
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }
    
    @FXML
    private void handleCadastrar() throws IOException {
        App.setRoot("cadastro");
    }
}

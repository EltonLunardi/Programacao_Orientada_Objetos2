package com.mycompany.bibliofx;

//import com.mycompany.bibliofx.ConectarDB;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

public class CadastroController {
    
    @FXML
    private TextField usuarioCadastro;
    
    @FXML
    private TextField senhaCadastro;
    
    @FXML
    private Button botaoCadastrar;
    
    @FXML
    private Button botaoVoltar;
    
    @FXML
    private void handleCadastrar() throws IOException {
        Connection conn = ConectarDB.conn;
        try {
            String usuario = usuarioCadastro.getText();
            String senha = senhaCadastro.getText();
            if (usuario.equals("") || senha.equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                return;
            }
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT usuario FROM usuarios WHERE usuario = '" + usuario + "'");
            boolean encontrado = false;
            while (result.next()) {
                if (result.getString(1).equals(usuario)) {
                    encontrado = true;
                }
            }
            if (encontrado) {
                JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o usuário. Nome de usuário já existente. Tente outro");
            } else {
                int id = 0;
                result = statement.executeQuery("SELECT id FROM usuarios");
                while (result.next()) {
                    id = Integer.parseInt(result.getString(1));
                }
                id++;
                statement.execute("INSERT INTO usuarios VALUES('" + id + "', " + "'" + usuario + "', " + "'" + senha + "');");
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleVoltar() throws IOException {
        App.setRoot("login");
    }
}
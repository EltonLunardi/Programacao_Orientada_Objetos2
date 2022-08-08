package com.mycompany.bibliofx;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;


public class AdminAdicionaLivroController {
    
    @FXML
    private TextField nomeLivro;
    
    @FXML
    private TextField autorLivro;
    
    @FXML
    private TextField anoLivro;
    
    @FXML
    public void handleVoltar() throws IOException {
        App.setRoot("mainAdmin");
    }
    
    @FXML
    private void handleAdicionarLivro() throws IOException {
        String nome = this.nomeLivro.getText();
        String autor = this.autorLivro.getText();
        String anoString = this.anoLivro.getText();
        if (nome.equals("") || autor.equals("") || anoString.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }
        int ano = Integer.parseInt(anoString);
        Connection conn = ConectarDB.conn;
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from livros");
            int id = 0;
            while (result.next()) {
                id = Integer.parseInt(result.getString(1));
            }
            id++;
            statement.execute("INSERT INTO livros VALUES('" + id + "', '" + nome + "', '" + autor + "', '" + ano + "');");
            JOptionPane.showMessageDialog(null, "Livro inserido com sucesso");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

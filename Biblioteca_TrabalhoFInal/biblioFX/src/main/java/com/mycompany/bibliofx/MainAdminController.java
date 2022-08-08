package com.mycompany.bibliofx;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;


public class MainAdminController {
    @FXML
    private void handleAdicionarLivro() throws IOException {
        App.setRoot("adminAdicionaLivro");
    }
    
    @FXML
    private void handleExcluirLivro() throws IOException {
        App.setRoot("adminExcluiLivro");
    }
    
    @FXML
    private void handleSair() throws IOException {
        Sessao.sessao = "";
        App.setRoot("login");
    }
}

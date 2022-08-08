package com.mycompany.bibliofx;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class MainUserController implements Initializable {
    @FXML
    private Text labelUsuario;
    
    @FXML
    private Button botaoEmprestar;
    
    @FXML
    private Button botaoDevolver;
    
    @FXML
    private Button botaoSair;    
    
    @FXML
    private void handleEmprestarLivro() throws IOException {
        App.setRoot("userEmprestaLivro");
    }
    
    @FXML
    private void handleDevolverLivro() throws IOException {
        App.setRoot("adminExcluiLivro");
    }
    
    @FXML
    private void handleSair() throws IOException {
        Sessao.sessao = "";
        App.setRoot("login");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUsuario.setText("Bem vindo, " + Sessao.sessao);
    }
}

package com.mycompany.bibliofx;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;


public class UserEmprestaLivroController implements Initializable {
    
    @FXML
    private TableView <Livro> tabela;
    
    @FXML
    private TableColumn <Livro, String> colunaNome;
    
    @FXML
    private TableColumn <Livro, String> colunaAutor;
    
    @FXML
    private TableColumn <Livro, Integer> colunaAno;
    
    private ObservableList<Livro> livros = FXCollections.observableArrayList();
    
    public void exibe_livros_emprestar () {
        Connection conn = ConectarDB.conn;
        try {
            Statement statement = conn.createStatement();
            Statement statement_2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet livros = statement.executeQuery("SELECT * FROM livros");
            while (livros.next()) {
                boolean emprestou = false;
                ResultSet usuarios_livros = statement_2.executeQuery("SELECT * FROM usuarios_livros WHERE id_usuario = " + Sessao.id + ";");
                while (usuarios_livros.next()) {
                    //System.out.println("livro id " + usuarios_livros.getString(3));
                    if (Integer.parseInt(livros.getString(1)) == Integer.parseInt(usuarios_livros.getString(3))) {
                        //System.out.println("livro id " + livros.getString(1));
                        emprestou = true;
                    }
                }
                if (!emprestou) {
                    this.livros.add(new Livro(livros.getString(2), livros.getString(3), Integer.parseInt(livros.getString(4))));
                }
                usuarios_livros.first();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        colunaNome.setCellValueFactory(new PropertyValueFactory<Livro, String>("nome"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("ano"));
        
        tabela.setItems(this.livros);
    }
    
    @FXML
    public void handleEmprestarLivro() throws IOException {
        Livro livro = this.tabela.getSelectionModel().getSelectedItem();
        if (livro == null) {
            JOptionPane.showMessageDialog(null, "Selecione um livro para emprestar");
            return;            
        }
        Connection conn = ConectarDB.conn;
        try {
            Statement statement = conn.createStatement();
            Statement statement_2 = conn.createStatement();
            Statement statement_3 = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT id FROM livros WHERE nome = '" + livro.getNome() + "' AND autor = '" + livro.getAutor() + "' AND ano = '" + livro.getAno() + "';");
            int id_livro = 0;
            while (result.next()) {
                id_livro = Integer.parseInt(result.getString(1));
            }
            ResultSet result_2 = statement_2.executeQuery("SELECT id FROM usuarios_livros;");
            int id_usuarios_livros = 0;
            while (result_2.next()) {
                id_usuarios_livros = Integer.parseInt(result_2.getString(1));
            }
            id_usuarios_livros++;
            statement_3.execute("INSERT INTO usuarios_livros VALUES ('" + String.valueOf(id_usuarios_livros) + "','" + String.valueOf(Sessao.id) + "', '" + String.valueOf(id_livro) + "');");
            this.livros.clear();
            exibe_livros_emprestar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Livro emprestado");
    }
    
    
    @FXML
    public void handleVoltar() throws IOException {
        App.setRoot("mainUser");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exibe_livros_emprestar();
    }
}

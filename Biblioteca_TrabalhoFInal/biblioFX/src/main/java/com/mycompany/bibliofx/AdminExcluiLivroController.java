package com.mycompany.bibliofx;

import java.io.IOException;

import java.sql.Connection;
import java.sql.Statement;

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
import java.util.ResourceBundle;


public class AdminExcluiLivroController implements Initializable {
    
    @FXML
    private TableView <Livro> tabela;
    
    @FXML
    private TableColumn <Livro, String> colunaNome;
    
    @FXML
    private TableColumn <Livro, String> colunaAutor;
    
    @FXML
    private TableColumn <Livro, Integer> colunaAno;
    
    private ObservableList<Livro> livros = FXCollections.observableArrayList();
    
    @FXML
    public void handleExcluirLivro() throws IOException {
        Livro livro = this.tabela.getSelectionModel().getSelectedItem();
        if (livro == null) {
            JOptionPane.showMessageDialog(null, "Selecione um livro para excluir");
            return;
        }
        Connection conn = ConectarDB.conn;
        try {
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM livros WHERE nome = '" + livro.getNome() + "' AND autor = '" + livro.getAutor() + "' AND ano = '" + livro.getAno() + "';");
            this.livros.clear();
            ExibirLivros.exibirLivros(this.livros, this.tabela, this.colunaNome, this.colunaAutor, this.colunaAno);
            JOptionPane.showMessageDialog(null, "Livro excluído com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleVoltar() throws IOException {
        App.setRoot("mainAdmin");
    }   
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ExibirLivros.exibirLivros(this.livros, this.tabela, this.colunaNome, this.colunaAutor, this.colunaAno);
    }
}

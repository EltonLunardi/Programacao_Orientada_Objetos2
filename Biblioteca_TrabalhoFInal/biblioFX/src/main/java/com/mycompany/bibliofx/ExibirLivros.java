package com.mycompany.bibliofx;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public final class ExibirLivros {
     
    public static void exibirLivros (ObservableList<Livro> livros, TableView <Livro> tabela, TableColumn <Livro, String> colunaNome, TableColumn <Livro, String> colunaAutor, TableColumn <Livro, Integer> colunaAno) {
        Connection conn = ConectarDB.conn;
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * from livros");
            while (result.next()) {
                livros.add(new Livro(result.getString(2), result.getString(3), Integer.parseInt(result.getString(4))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        colunaNome.setCellValueFactory(new PropertyValueFactory<Livro, String>("nome"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("ano"));
        
        tabela.setItems(livros);
    }
}

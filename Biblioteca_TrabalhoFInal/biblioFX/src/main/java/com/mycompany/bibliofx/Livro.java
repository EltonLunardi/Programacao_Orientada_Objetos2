package com.mycompany.bibliofx;

public class Livro {
    private String nome;
    private String autor;
    private int ano;
    
    Livro (String nome, String autor, int ano) {
        this.nome = nome;
        this.autor = autor;
        this.ano = ano;
    }
    
    public String getNome () {
        return this.nome;
    }
    
    public String getAutor () {
        return this.autor;
    }
    
    public int getAno () {
        return this.ano = ano;
    }
}

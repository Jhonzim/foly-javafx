package com.iff.livraria.model;

public class Livro implements EntidadeInterface{

    
    private int id;
    private String nome;
    private String autor;
    private String descricao;
    private boolean foiLido;
    private String imagem;
    

    public Livro(int id, String nome, String autor,String descricao, boolean foiLido, String imagem) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.descricao = descricao;
        this.foiLido = foiLido;
        this.imagem = imagem;
    }
    
    public Livro(String nome, String autor,String descricao, boolean foiLido, String imagem) {
        this.id = -1;
        this.nome = nome;
        this.autor = autor;
        this.descricao = descricao;
        this.foiLido = foiLido;
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public boolean isFoiLido() {
        return foiLido;
    }

    public void setFoiLido(boolean foiLido) {
        this.foiLido = foiLido;
    }

    @Override
    public int getId() {
        return id;
    }
    
}

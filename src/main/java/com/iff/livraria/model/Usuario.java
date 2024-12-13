/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iff.livraria.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jao
 */
public class Usuario implements EntidadeInterface{
    private final int id;
    private String nome;
    private String nomeDeUsuario;
    private final List<Livro> livros;
    
    public Usuario(int id, String nome, String nomeDeUsuario, List<Livro> livros)
    {
        this.id = id;
        this.nome = nome;
        this.nomeDeUsuario = nomeDeUsuario;
        this.livros = livros;
    }

    public Usuario(int id, String nome, String nomeDeUsuario) {
        this.id = id;
        this.nome = nome;
        this.nomeDeUsuario = nomeDeUsuario;
        this.livros = new ArrayList();
    }
    
    public Usuario(String nome, String nomeDeUsuario){
        this.nome = nome;
        this.nomeDeUsuario = nomeDeUsuario;
        this.id = -1;
        this.livros = null;
    }
    
    public String getNome(){
        return this.nome;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }
    
    @Override
    public int getId(){
        return this.id;
    }
    
    
}

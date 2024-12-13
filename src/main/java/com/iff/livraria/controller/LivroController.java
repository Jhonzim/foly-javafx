/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iff.livraria.controller;

import com.iff.livraria.model.Livro;
import com.iff.livraria.model.Usuario;
import com.iff.livraria.model.dao.DaoFactory;
import com.iff.livraria.model.dao.LivroDaoJDBC;
import java.util.List;

/**
 *
 * @author jao
 */
public class LivroController {
    
    public static void getLivros(Usuario usuario){
        
    }
    
    public static void incluir(Livro livro, Usuario usuario) throws Exception{
        LivroDaoJDBC createBookConn = DaoFactory.getLivroDaoConnection();
        createBookConn.incluir(livro, usuario);
    }
    
    public static List<Livro> buscar(Usuario usuario) throws Exception{
        LivroDaoJDBC getBooksConn = DaoFactory.getLivroDaoConnection();
        return getBooksConn.listarLivros(usuario);
    }
    
    public static void excluir(Livro livro) throws Exception{
        LivroDaoJDBC deleteBookConn = DaoFactory.getLivroDaoConnection();
        deleteBookConn.excluir(livro);
        
        ImagemController.apagarImagem(livro.getImagem());
    }
    
    public static void editar(Livro livro) throws Exception{
        LivroDaoJDBC editBookConn = DaoFactory.getLivroDaoConnection();
        editBookConn.editar(livro);
    }
    
}

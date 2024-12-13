package com.iff.livraria.controller;

import com.iff.livraria.model.Livro;
import com.iff.livraria.model.Usuario;
import com.iff.livraria.model.dao.DaoFactory;
import com.iff.livraria.model.dao.LivroDaoJDBC;
import com.iff.livraria.model.dao.UsuarioDaoJDBC;
import com.iff.livraria.utils.exception.UserNameExistsException;
import com.iff.livraria.utils.exception.UserNotFoundException;
import java.util.List;

public class SecaoController {
    public static Usuario usr;
    public static List<Livro> livros;
    public static int qtdLivros;
    public static int qtdLivrosLidos;
    
    public static void buscar() throws Exception{     
        livros = getLivros(usr);
        
        qtdLivros = livros.size();
        
        qtdLivrosLidos = 0;
        for(Livro l : livros){
            if(l.isFoiLido()) 
                qtdLivrosLidos++;
        }
    }
    
    public static void cadastrarUsuario(String nome, String nomeDeUsuario, String senha) throws UserNameExistsException, Exception{
        nomeDeUsuario = nomeDeUsuario.trim();
        nome = nome.trim();
        senha = senha.trim();
        
        Usuario tempUsr = new Usuario(nome, nomeDeUsuario);
        
        try{
            UsuarioDaoJDBC usrNameExistsConn = DaoFactory.getUsuarioDaoConnection();
            
            if(usrNameExistsConn.existeNomeUsuario(tempUsr.getNomeDeUsuario())) 
                throw new UserNameExistsException("");
            
            UsuarioDaoJDBC createNewUsrConn = DaoFactory.getUsuarioDaoConnection();
            createNewUsrConn.cadastar(tempUsr, senha);
        }
        finally{
            
        }
    }
    
    public static void login(String nomeDeUsuario, String senha) throws UserNotFoundException, Exception{
        nomeDeUsuario = nomeDeUsuario.trim();
        senha = senha.trim();
        
       
        UsuarioDaoJDBC usrLoginConn = DaoFactory.getUsuarioDaoConnection();

        Usuario usuario = usrLoginConn.getUsuarioPorLogin(nomeDeUsuario, senha);

        if(usuario == null) throw new UserNotFoundException("Nome de usuário ou senha incorretos!");

        usr = usuario;
        
    }
    
    
    private static List<Livro> getLivros(Usuario usuario){
        List<Livro> lista = null;
        try{
            LivroDaoJDBC sagaConn = DaoFactory.getLivroDaoConnection();
            lista = sagaConn.listarLivros( usuario);
            
        }finally{
            
            return lista;
        }
    }
}

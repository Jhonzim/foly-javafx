/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iff.livraria.model.dao;

import com.iff.livraria.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jao
 */
public class UsuarioDaoJDBC {
    private Connection conn;
    private final String nomeDaTabela = "usuario";
    
    public UsuarioDaoJDBC() throws Exception{
        this.conn = ConnFactory.getConnection();
    }
    
    public void cadastar(Usuario usuario, String senha) throws Exception{
        
        try{
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO " + nomeDaTabela + " (nome, nome_de_usuario, senha) values (?, ?, ?);");
        
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getNomeDeUsuario());
            ps.setString(3, senha);

            ps.execute();
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
    }
    
    public boolean existeNomeUsuario(String nomeDeUsuario) throws Exception{
         
        boolean existe = false;
        try{
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM " + nomeDaTabela + " WHERE nome_de_usuario = ?");
        
            ps.setString(1, nomeDeUsuario);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) existe = true;
            else          existe =  false;
            
        }
        finally{
            if(conn != null){
                conn.close();
            }
            return existe;
        }
    }
    
    public Usuario getUsuarioPorLogin(String nomeDeUsuario, String senha) throws Exception{
         
        Usuario usuarioEncontrado = null;
        
        try{
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM " + nomeDaTabela + " WHERE nome_de_usuario =? AND senha =?");
        
            ps.setString(1, nomeDeUsuario);
            ps.setString(2, senha);
           
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                usuarioEncontrado =  new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("nome_de_usuario")      
                );
            }
            
        }
        finally{
            if(conn != null){
                conn.close();
            }
            return usuarioEncontrado;
        }
        
    }
    
    public void delete(Usuario usuario)throws Exception{
        String nomeDoCampo = usuario.getId() > 0 
                ? "id"
                : "nome_de_usuario";
        try{
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM " + nomeDaTabela + " WHERE "+ nomeDoCampo + "=?");
        
            if(usuario.getId() <= -1){
               ps.setString(1, usuario.getNomeDeUsuario());
            }
            else{
               ps.setInt(1, usuario.getId());
            }
           
            
            ps.execute();
            
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
    }
}


package com.iff.livraria.model.dao;

import com.iff.livraria.model.Livro;
import com.iff.livraria.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class LivroDaoJDBC {

    private Connection conn;
    private final String nomeDaTabela = "livro";

    public LivroDaoJDBC() throws Exception{
       this.conn = ConnFactory.getConnection();
    }
    
    public void incluir(Livro livro, Usuario usuario) throws Exception {
    
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO "+ nomeDaTabela+ " (nome, autor, descricao, foi_lido, imagem, usuario) VALUES(?, ?, ?, ?, ?, ?)");
            
            ps.setString (1, livro.getNome());
            ps.setString (2, livro.getAutor());
            ps.setString (3, livro.getDescricao());
            ps.setBoolean(4, livro.isFoiLido());
            ps.setString (5, livro.getImagem());
            ps.setInt    (6, usuario.getId());
            
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void editar(Livro entidade) throws Exception {
    
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE "+ nomeDaTabela + " SET nome=?, autor=?, descricao=?, imagem=?, foi_lido=? WHERE id=?");
            
            ps.setString (1, entidade.getNome());
            ps.setString (2, entidade.getAutor());
            ps.setString (3, entidade.getDescricao());
            ps.setString    (4, entidade.getImagem());
            ps.setBoolean(5, entidade.isFoiLido());
            ps.setInt    (6, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void excluir(Livro entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM " + nomeDaTabela + " WHERE id=?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Livro> listarLivros(Usuario usuario) throws Exception {
        List<Livro> lista = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + nomeDaTabela + " WHERE usuario=?");
            ps.setInt(1, usuario.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Livro c = new Livro(
                        rs.getInt    ("id"),
                        rs.getString ("nome"),
                        rs.getString ("autor"),
                        rs.getString ("descricao"),
                        rs.getBoolean("foi_lido"),
                        rs.getString ("imagem")
                );
                
                lista.add(c);
            }
            
        } finally {
            if (conn != null) {
                conn.close();
            }
            return lista;
        }


    }
}

package com.iff.livraria;

import com.iff.livraria.controller.LivroController;
import com.iff.livraria.controller.SecaoController;
import com.iff.livraria.controller.UsuarioController;
import com.iff.livraria.model.Livro;
import com.iff.livraria.model.Usuario;

import static com.iff.livraria.controller.SecaoController.usr;

public class FakeMain {
    
    public static Livro   livroTeste = new Livro("Livro Teste", "Autor Teste", "Descrição de Teste", true, "imagens/91e2d41f-5f5e-47fe-9fe2-5d740d24e26d.png");
    public static Usuario usuarioTeste = new Usuario("nomeTeste", "nomeDeUsuarioTeste");
    public static String  senhaTeste = "senha_teste";
    
    public static void main(String args[]){
        try{
            iniciarTeste();
            System.out.println(SecaoController.livros.size());
            
            
 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void iniciarTeste() throws Exception{
        limparTestes();
        
        SecaoController.cadastrarUsuario(usuarioTeste.getNome(), usuarioTeste.getNomeDeUsuario(), senhaTeste);
        SecaoController.login(usuarioTeste.getNomeDeUsuario(), senhaTeste);
        for(int i = 0; i < 10; i++){
            String nomeLivro = "Livro Teste" + i;
            livroTeste.setNome(nomeLivro);
            livroTeste.setFoiLido(Math.random() < 0.5);
            LivroController.incluir(livroTeste, usr);
        }
        
        SecaoController.buscar();
    }
    
    public static void limparTestes() throws Exception{
        UsuarioController.delete(usuarioTeste);
    }
}

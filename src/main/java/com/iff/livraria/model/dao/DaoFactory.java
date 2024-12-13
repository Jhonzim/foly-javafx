package com.iff.livraria.model.dao;

public class DaoFactory {
    public static LivroDaoJDBC getLivroDaoConnection() throws Exception{
        return new LivroDaoJDBC();
    }
    
    public static UsuarioDaoJDBC getUsuarioDaoConnection() throws Exception{
        return new UsuarioDaoJDBC();
    }
}

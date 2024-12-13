
package com.iff.livraria.model.dao;


import java.util.List;

public interface InterfaceDao<T> {
    public List<T> listar() throws Exception;
}

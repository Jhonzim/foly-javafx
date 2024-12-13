/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iff.livraria.controller;

import com.iff.livraria.model.Usuario;
import com.iff.livraria.model.dao.DaoFactory;
import com.iff.livraria.model.dao.UsuarioDaoJDBC;
import com.iff.livraria.utils.exception.UserNameExistsException;
import com.iff.livraria.utils.exception.UserNotFoundException;

/**
 *
 * @author jao
 */
public class UsuarioController {
    
    
    public static void delete(Usuario usuario) throws UserNotFoundException, Exception{
        
        UsuarioDaoJDBC usrDeleteConn = DaoFactory.getUsuarioDaoConnection();

        usrDeleteConn.delete(usuario);
        
    }
}

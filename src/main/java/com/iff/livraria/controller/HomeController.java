package com.iff.livraria.controller;

import com.iff.livraria.App;
import com.iff.livraria.controller.SecaoController;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class HomeController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSenha;
    @FXML
    private Hyperlink linkCadastrar;
    @FXML
    private Button btnIr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActionbtnLogin(ActionEvent event) throws IOException, Exception {
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        if (email.isEmpty() || senha.isEmpty()) {
          throw new IllegalArgumentException("Todos os campos devem ser preenchidos!!");
        }
        SecaoController.login(email,senha);
        SecaoController.buscar();
        App.setRoot("view/Menu");
        
    }

    @FXML
    private void OnActionlinkCadastrar(ActionEvent event) throws IOException {  
        App.setRoot("view/Cadastro");
    }

    @FXML
    private void OnActionbtnIr(ActionEvent event) throws IOException {
        App.setRoot("view/Menu");
    }

    
}

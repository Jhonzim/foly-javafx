
package com.iff.livraria.controller;

import com.iff.livraria.App;
import com.iff.livraria.controller.SecaoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController implements Initializable {

    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txtNome;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActionbtnSalvar(ActionEvent event) throws Exception {
    String nome = txtNome.getText().trim();
    String email = txtEmail.getText().trim();
    String senha = txtSenha.getText().trim();
    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
        throw new IllegalArgumentException("Todos os campos devem ser preenchidos!!");
    }
    SecaoController.cadastrarUsuario(nome,email,senha);
    SecaoController.login(email, senha);
        App.setRoot("view/Menu");
    }

    @FXML
    private void OnActionbtnVoltar(ActionEvent event) throws IOException {
        App.setRoot("view/Home");
    }
    
}

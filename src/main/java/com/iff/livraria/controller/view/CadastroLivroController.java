/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iff.livraria.controller.view;

import com.iff.livraria.controller.ImagemController;
import com.iff.livraria.utils.exception.ImageError;
import com.iff.livraria.utils.exception.NoChooseImageError;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author jao
 */
public class CadastroLivroController implements Initializable {

    @FXML
    private ImageView imageV;
    
    private ImagemController img;
    
    @FXML
    private HBox hBoxImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hBoxImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("textfield clicked");
                pegarImagem(new ActionEvent());
            }
        });
    }    

    @FXML
    private void pegarImagem(ActionEvent event) {
        
        try{
            FileChooser fs = new FileChooser();
            File file =  fs.showOpenDialog(null);

            if (!file.exists()) {
                throw new FileNotFoundException("O arquivo não foi encontrado ou não existe");
            }
            
            img = new ImagemController(file);
            
            
            imageV.setImage( img.converterFileParaJFXImage() );
            
            imageV.setFitWidth(150);
            imageV.setFitHeight(300);
            imageV.setPreserveRatio(true);
        
        }catch(FileNotFoundException e){
            e.printStackTrace();
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }catch(ImageError e){
            System.out.println(e.getMessage());;
        }
        
        
    }
    
    

    @FXML
    private void salvarImagem(ActionEvent event) {
        
        try{
            if(img == null){
                throw new NoChooseImageError("Selecione uma imagem para salvar");
            }
           
            
            //O caminho retornado pela função deve ser colocado no banco de dados para encontar a imagem
            String caminhoS = img.salvarImagem(); 
            System.out.println(caminhoS);
            
        }catch(NoChooseImageError e){
            System.out.println(e.getMessage());
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
}

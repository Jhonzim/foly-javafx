/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iff.livraria.controller.view;

import com.iff.livraria.controller.SecaoController;
import com.iff.livraria.model.Livro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jao
 */
public class TesteexibirlivrosController implements Initializable {

    @FXML
    private TableView<Livro> tabelaDeLivros;
    @FXML
    private TableColumn<Livro, String> nomeDoLivro;
    @FXML
    private TableColumn<Livro, String> autor;
    @FXML
    private TableColumn<Livro, Boolean> lido;
    
    private ObservableList<Livro> listaLivrosObservable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.carregarLivros();
    }    
    
    public void carregarLivros(){
        try{
            nomeDoLivro.setCellValueFactory(new PropertyValueFactory<>("nome"));
            autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            
//            lido.setCellValueFactory(new PropertyValueFactory<>("lido"));
//            lido.setCellFactory(CheckBoxTableCell.forTableColumn(lido));
    
            
            lido.setCellValueFactory(cellData -> {
                return new SimpleBooleanProperty(cellData.getValue().isFoiLido());
            });

            lido.setCellFactory(col -> {
                CheckBoxTableCell<Livro, Boolean> cell = new CheckBoxTableCell<>();
                cell.setOnMouseClicked(event -> {
                    if (cell.isSelected()) {
                        cell.getTableRow().getItem().setFoiLido(true);
                    } else {
                        cell.getTableRow().getItem().setFoiLido(false);
                    }
                });
                return cell;
            });

           

            listaLivrosObservable = FXCollections.observableArrayList(SecaoController.livros);
            tabelaDeLivros.setItems(listaLivrosObservable);
        }catch(Exception e){
            System.out.println("erro");
        }
        
    }

    @FXML
    private void onClickFunction(MouseEvent event) {
        if (event.getClickCount() == 2) { // Quando clicar duas vezes no livro, ele vai fazer isso aqui;
        Livro livro = tabelaDeLivros.getSelectionModel().getSelectedItem();
        if (livro != null) {
            
            System.out.println("Livro clicado: " + livro.getNome());
            
        }
    }
    }
}

package com.iff.livraria.controller;

import com.iff.livraria.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class GraficoController implements Initializable {

    @FXML
    private PieChart graficopizza;
    @FXML
    private Button btnVoltar;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int n;
        // TODO            
        // Criando os dados do gráfico
            PieChart.Data slice1 = new PieChart.Data("Lidos", SecaoController.qtdLivrosLidos);
            n=SecaoController.qtdLivrosLidos - SecaoController.qtdLivros;
            PieChart.Data slice2 = new PieChart.Data("Não-Lidos",n);

            // Adicionando os dados ao gráfico
            graficopizza.getData().addAll(slice1, slice2);

            // Personalizando o gráfico
            graficopizza.setTitle("Categorias de Livros");
            
            // Estilizando o título do gráfico
            graficopizza.lookup(".chart-title").setStyle(
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: white;");
    }

    @FXML
    private void OnActionbtnVoltar(ActionEvent event) throws IOException {
        App.setRoot("view/Menu");
    }

}    

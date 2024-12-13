package com.iff.livraria;

import com.iff.livraria.model.Usuario;
import com.iff.livraria.model.dao.DaoFactory;
import com.iff.livraria.model.dao.UsuarioDaoJDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view/Home"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        var url = App.class.getResource(fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        //!!! tirar depois !!!
        FakeMain.main(new String[]{});
        launch();
    }

}
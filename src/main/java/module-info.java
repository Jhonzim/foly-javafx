module Livraria {
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires com.google.protobuf;
    requires java.desktop;
    requires java.sql;
    requires java.base;
    
    
    opens com.iff.livraria to javafx.fxml;
    exports com.iff.livraria;
    
    opens com.iff.livraria.view to javafx.fxml;
    exports com.iff.livraria.view;
    
    opens com.iff.livraria.styles to javafx.fxml;
    exports com.iff.livraria.styles;
    
    exports com.iff.livraria.model;
    exports com.iff.livraria.model.dao;
    
    opens com.iff.livraria.controller to javafx.fxml;
    exports com.iff.livraria.controller;
    
    opens com.iff.livraria.controller.view to javafx.fxml;
    exports com.iff.livraria.controller.view;
    
    exports com.iff.livraria.utils.exception;
    
    
    
}

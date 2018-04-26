/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DBUtility.DBStartup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class MaquinaDispensadora extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //todo lo que esta en este bloque de página es para llamar la página principal.
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/paginaPrincipal.fxml"));
        Scene scene = new Scene(root);
        
        stage.setTitle("Maquina Dispensadora");
        stage.getIcons().add(new Image("Utility/vending_machine.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getStylesheets().add("/Styles/paginaadmin.css");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBStartup p=new DBStartup();
        p.createTables();
        launch(args);
    }
    
}

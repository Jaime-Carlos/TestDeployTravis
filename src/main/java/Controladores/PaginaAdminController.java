/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class PaginaAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void crudProducto(ActionEvent e) throws IOException{
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Vista/adminProducto.fxml"));
        Scene home_scene_page = new Scene(home_page_parent);
        Stage app_stage = new Stage();
        Stage main_app = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //(Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene_page);
        app_stage.getIcons().add(new Image("Utility/vending_machine.png"));
        app_stage.initOwner(main_app);
        app_stage.setResizable(false);
         app_stage.setTitle("Operaciones de Producto");
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();
    }
    
    @FXML
    private void crudTransaccion(ActionEvent e) throws IOException{
     Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Vista/paginaTransacciones.fxml"));
        Scene home_scene_page = new Scene(home_page_parent);
        Stage app_stage = new Stage();
        Stage main_app = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //(Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene_page);
        app_stage.getIcons().add(new Image("Utility/vending_machine.png"));
        app_stage.initOwner(main_app);
        app_stage.setResizable(false);
         app_stage.setTitle("Transacciones");
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();
    }
    @FXML
    private void crudCasilla(ActionEvent e) throws IOException{
    Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Vista/adminCasilla.fxml"));
        Scene home_scene_page = new Scene(home_page_parent);
        Stage app_stage = new Stage();
        Stage main_app = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //(Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene_page);
        app_stage.getIcons().add(new Image("Utility/vending_machine.png"));
        app_stage.initOwner(main_app);
        app_stage.setResizable(false);
         app_stage.setTitle("Casillas");
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();
    }
    @FXML
    private void crudCaja(ActionEvent e) throws IOException{
             Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Vista/paginaCaja.fxml"));
        Scene home_scene_page = new Scene(home_page_parent);
        Stage app_stage = new Stage();
        Stage main_app = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //(Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene_page);
        app_stage.getIcons().add(new Image("Utility/Admin-icon.png"));
        app_stage.initOwner(main_app);
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();
               
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}

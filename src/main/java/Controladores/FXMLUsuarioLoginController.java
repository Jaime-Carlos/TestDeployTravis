/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAOS.DAOAdministrador;
import Modelo.Administrador;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author james
 */
public class FXMLUsuarioLoginController implements Initializable {

    @FXML
    private Button boton;

    @FXML
    private TextField campitoUsuario;

    @FXML
    private TextField campitoContrasena;

    @FXML
    private JFXButton boton1;

    @FXML
    private void ingresar(ActionEvent e) throws IOException {
        DAOAdministrador daoa = new DAOAdministrador();
        Administrador a = daoa.listAdmin();
        System.out.println(campitoContrasena.getText() + "" + campitoUsuario.getText());
        if (campitoUsuario.getText().equals(a.getUsuario()) && campitoContrasena.getText().equals(a.getContraseña())) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Vista/paginaAdmin.fxml"));
            Scene home_scene_page = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            app_stage.setScene(home_scene_page);
            app_stage.getIcons().add(new Image("Utility/vending_machine.png"));
            app_stage.setResizable(false);
            app_stage.setTitle("Panel de Control");
            app_stage.show();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inicio de Sesion");
            alert.setHeaderText(null);
            alert.setContentText("Datos Erroneos");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("You clicked me!");

    }

}

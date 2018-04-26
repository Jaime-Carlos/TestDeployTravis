/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Caja;
import Servicios.ServicioCaja;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class PaginaCajaController implements Initializable {
   @FXML
    Button boton1;

    @FXML
    Button boton2;

    @FXML
    Button boton3;
    
        @FXML
    private ListView list;
        @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServicioCaja sp = new ServicioCaja();
        List<Caja> listaCasi = sp.listAll();
        for (int i = 0; i < listaCasi.size(); i++) {
            list.getItems().add("Denominacion: " + listaCasi.get(i).getDenominacion()
                    + " Cantidad: " + listaCasi.get(i).getCantidad());
        }
       
    }
    
    
        @FXML
    private void agregar(ActionEvent e) {
        // Create the custom dialog.
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Denominación");
        dialog.setHeaderText("Cantidad");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Ingresar Denominación", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        Insets inset = new Insets(20, 150, 10, 10);
        grid.setPadding(inset);

        TextField denominacion = new TextField();
        denominacion.setPromptText("Denominación");
        TextField cantidad = new TextField();
        cantidad.setPromptText("Cantidad");

        grid.add(new Label("Denominación:"), 0, 0);
        grid.add(denominacion, 1, 0);
        grid.add(new Label("Cantidad:"), 0, 1);
        grid.add(cantidad, 1, 1);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        denominacion.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> denominacion.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(denominacion.getText(), cantidad.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(producto -> {
            ServicioCaja sc = new ServicioCaja();
            sc.crearCaja(new Caja(Integer.valueOf(denominacion.getText()), Integer.valueOf(cantidad.getText())));
            List<Caja> listaCasi = sc.listAll();
            list.getItems().clear();
            for (int i = 0; i < listaCasi.size(); i++) {
                list.getItems().add("Denominación: " + listaCasi.get(i).getDenominacion()
                        + " Cantidad: " + listaCasi.get(i).getCantidad());
            }
        });
    }
        
           @FXML
    private void borrar(ActionEvent e) {
        if (list.getSelectionModel().getSelectedItems().size() == 1) {
            ServicioCaja sc = new ServicioCaja();
            String l[] = list.getSelectionModel().getSelectedItem().toString().split(" ");
            sc.eliminar(l[1]);
            List<Caja> listaCasi = sc.listAll();
            list.getItems().clear();
            for (int i = 0; i < listaCasi.size(); i++) {
                list.getItems().add("Denominación: " + listaCasi.get(i).getDenominacion()
                        + " Cantidad: " + listaCasi.get(i).getCantidad());
            }
        }
    } 
    
       @FXML
    private void editar(ActionEvent e) {
        if (list.getSelectionModel().getSelectedItems().size() == 1) {
            // Create the custom dialog.
            // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Denominación");
            dialog.setHeaderText("Cantidad");

// Set the button types.
            ButtonType loginButtonType = new ButtonType("Denominación", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            Insets inset = new Insets(20, 150, 10, 10);
            grid.setPadding(inset);

            TextField denominacion = new TextField();
            denominacion.setPromptText("Denominación");
            TextField cantidad = new TextField();
            cantidad.setPromptText("Cantidad");
            String l[] = list.getSelectionModel().getSelectedItem().toString().split(" ");
            denominacion.setText(l[1]);
            cantidad.setText(l[3]);
            grid.add(new Label("Denominación:"), 0, 0);
            grid.add(denominacion, 1, 0);
            grid.add(new Label("Cantidad:"), 0, 1);
            grid.add(cantidad, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(false);

// Do some validation (using the Java 8 lambda syntax).
            denominacion.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
            
// Request focus on the username field by default.
        Platform.runLater(() -> denominacion.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(denominacion.getText(), cantidad.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();


            result.ifPresent(caja -> {
                ServicioCaja sp = new ServicioCaja();
                sp.ActualizarCaja(new Caja(Integer.valueOf(denominacion.getText()), Integer.valueOf(cantidad.getText())));
                List<Caja> listaCasi = sp.listAll();
                list.getItems().clear();
                for (int i = 0; i < listaCasi.size(); i++) {
                    list.getItems().add("Denominacion: " + listaCasi.get(i).getDenominacion()
                            + " Cantidad: " + listaCasi.get(i).getCantidad());
                }
            });
        }
    }

    
   
    

    
    
   

    
    
    
    
        
}

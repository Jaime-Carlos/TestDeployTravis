/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Producto;
import Servicios.ServicioProducto;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author james
 */
public class AdminProductoController implements Initializable {

    @FXML
    Button agregarProducto;

    @FXML
    Button borrarProducto;

    @FXML
    Button editarProducto;
    @FXML
    private ListView listaCrudProductos;

    @FXML
    private void agregar(ActionEvent e) {
        // Create the custom dialog.
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Nuevo Producto");
        dialog.setHeaderText("Características del nuevo Producto");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Ingresar Producto", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        Insets inset = new Insets(20, 150, 10, 10);
        grid.setPadding(inset);

        TextField nombreProducto = new TextField();
        nombreProducto.setPromptText("Nombre del Producto");
        TextField precioProducto = new TextField();
        precioProducto.setPromptText("Precio del Producto");

        grid.add(new Label("Nombre Producto:"), 0, 0);
        grid.add(nombreProducto, 1, 0);
        grid.add(new Label("Precio Producto:"), 0, 1);
        grid.add(precioProducto, 1, 1);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        nombreProducto.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> nombreProducto.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(nombreProducto.getText(), precioProducto.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(producto -> {
            ServicioProducto sp = new ServicioProducto();
            sp.crearProd(new Producto(nombreProducto.getText(), Integer.valueOf(precioProducto.getText())));
            List<Producto> listaCasi = sp.listarProductos();
            listaCrudProductos.getItems().clear();
            for (int i = 0; i < listaCasi.size(); i++) {
                listaCrudProductos.getItems().add("Producto: " + listaCasi.get(i).getNombre()
                        + " Precio: " + listaCasi.get(i).getPrecio());
            }
        });
    }

    @FXML
    private void borrar(ActionEvent e) {
        if (listaCrudProductos.getSelectionModel().getSelectedItems().size() == 1) {
            ServicioProducto sp = new ServicioProducto();
            String l[] = listaCrudProductos.getSelectionModel().getSelectedItem().toString().split(" ");
            sp.eliminarproducto(l[1]);
            List<Producto> listaCasi = sp.listarProductos();
            listaCrudProductos.getItems().clear();
            for (int i = 0; i < listaCasi.size(); i++) {
                listaCrudProductos.getItems().add("Producto: " + listaCasi.get(i).getNombre()
                        + " Precio: " + listaCasi.get(i).getPrecio());
            }
        }
    }

    @FXML
    private void editar(ActionEvent e) {
        if (listaCrudProductos.getSelectionModel().getSelectedItems().size() == 1) {
            // Create the custom dialog.
            // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Nuevo Producto");
            dialog.setHeaderText("Características del nuevo Producto");

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("Actualizar Producto", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            Insets inset = new Insets(20, 150, 10, 10);
            grid.setPadding(inset);

            TextField nombreProducto = new TextField();
            nombreProducto.setPromptText("Nombre del Producto");
            TextField precioProducto = new TextField();
            precioProducto.setPromptText("Precio del Producto");
            String l[] = listaCrudProductos.getSelectionModel().getSelectedItem().toString().split(" ");
            nombreProducto.setText(l[1]);
            precioProducto.setText(l[3]);
            grid.add(new Label("Nuevo Nombre Producto:"), 0, 0);
            grid.add(nombreProducto, 1, 0);
            grid.add(new Label("Nuevo Precio Producto:"), 0, 1);
            grid.add(precioProducto, 1, 1);

                     // Enable/Disable login button depending on whether a username was entered.
            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(false);

                    // Do some validation (using the Java 8 lambda syntax).
            nombreProducto.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(grid);

                    // Request focus on the username field by default.
            
                    // Request focus on the username field by default.
        Platform.runLater(() -> nombreProducto.requestFocus());

                    // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(nombreProducto.getText(), precioProducto.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();


            result.ifPresent(producto -> {
                ServicioProducto sp = new ServicioProducto();
                sp.ActualizarProducto(new Producto(nombreProducto.getText(), Integer.valueOf(precioProducto.getText())));
                List<Producto> listaCasi = sp.listarProductos();
                listaCrudProductos.getItems().clear();
                for (int i = 0; i < listaCasi.size(); i++) {
                    listaCrudProductos.getItems().add("Producto: " + listaCasi.get(i).getNombre()
                            + " Precio: " + listaCasi.get(i).getPrecio());
                }
            });
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServicioProducto sp = new ServicioProducto();
        List<Producto> listaCasi = sp.listarProductos();
        for (int i = 0; i < listaCasi.size(); i++) {
            listaCrudProductos.getItems().add("Producto: " + listaCasi.get(i).getNombre()
                    + " Precio: " + listaCasi.get(i).getPrecio());
        }
    }

}

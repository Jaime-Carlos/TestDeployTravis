/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates asdas
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Casilla;
import Modelo.Producto;
import Servicios.ServicioCasilla;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author james
 */
public class AdminCasillaController implements Initializable {

    @FXML
    ListView list;

    @FXML
    private void agregar(ActionEvent e) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Vista/agregarCasilla.fxml"));
        Scene home_scene_page = new Scene(home_page_parent);
        Stage app_stage = new Stage();
        Stage main_app = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //(Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene_page);
        app_stage.getIcons().add(new Image("Utility/vending_machine.png"));
        app_stage.initOwner(main_app);
        app_stage.setResizable(false);
        app_stage.setTitle("Agregar Casilla");
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();
        ServicioCasilla sc = new ServicioCasilla();
        List<Casilla> listaCasi = sc.listarCasilla();
        list.getItems().clear();
        for (int i = 0; i < listaCasi.size(); i++) {
            list.getItems().add("Codigo: " + listaCasi.get(i).getCodigo()
                    + " Cantidad: " + listaCasi.get(i).getNumeroDeProductos()
                    + " Producto: " + listaCasi.get(i).getProducto().getNombre());
        }
    }

    @FXML
    private void borrar(ActionEvent e) {
        if (list.getSelectionModel().getSelectedItems().size() == 1) {
            ServicioCasilla sc = new ServicioCasilla();
            String l[] = list.getSelectionModel().getSelectedItem().toString().split(" ");
            System.out.println(l[1]);
            sc.eliminarCasilla(l[1]);
            List<Casilla> listaCasi = sc.listarCasilla();
            list.getItems().clear();
            for (int i = 0; i < listaCasi.size(); i++) {
                list.getItems().add("Codigo: " + listaCasi.get(i).getCodigo()
                        + " Cantidad: " + listaCasi.get(i).getNumeroDeProductos()
                        + " Producto: " + listaCasi.get(i).getProducto().getNombre());
            }
        }
    }

    @FXML
    private void editar(ActionEvent e) throws IOException {
        if (list.getSelectionModel().getSelectedItems().size() == 1) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Actualizar Casilla");
            dialog.setHeaderText("Digite la informacion");

// Set the button types.
            ButtonType loginButtonType = new ButtonType("Actualizar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            Insets inset = new Insets(20, 150, 10, 10);
            grid.setPadding(inset);

            TextField denominacion = new TextField();
            denominacion.setPromptText("Actualizar Casilla");
            TextField cantidad = new TextField();
            cantidad.setPromptText("Digite la informacion");
            String l[] = list.getSelectionModel().getSelectedItem().toString().split(" ");
            denominacion.setText(l[3]);
            cantidad.setText(l[5]);
            grid.add(new Label("Cantidad de Productos:"), 0, 0);
            grid.add(denominacion, 1, 0);
            grid.add(new Label("Nombre Producto:"), 0, 1);
            grid.add(cantidad, 1, 1);
            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(false);

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
                ServicioCasilla sc = new ServicioCasilla();
                sc.ActualizarCasilla(new Casilla(l[1], Integer.valueOf(denominacion.getText()), new Producto(cantidad.getText(), 0)));
                List<Casilla> listaCasi = sc.listarCasilla();
                list.getItems().clear();
                for (int i = 0; i < listaCasi.size(); i++) {
                    list.getItems().add("Codigo: " + listaCasi.get(i).getCodigo()
                            + " Cantidad: " + listaCasi.get(i).getNumeroDeProductos()
                            + " Producto: " + listaCasi.get(i).getProducto().getNombre());
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
        ServicioCasilla sc = new ServicioCasilla();

        List<Casilla> listaCasi = sc.listarCasilla();

        for (int i = 0; i < listaCasi.size(); i++) {
            list.getItems().add("Codigo: " + listaCasi.get(i).getCodigo()
                    + " Cantidad: " + listaCasi.get(i).getNumeroDeProductos()
                    + " Producto: " + listaCasi.get(i).getProducto().getNombre());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Casilla;
import Modelo.Producto;
import Servicios.ServicioCasilla;
import Servicios.ServicioProducto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author james
 */
public class EditarCasillaController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    Spinner<Integer> spinner;
    @FXML
    ChoiceBox producto;
    @FXML
    Button editarProducto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServicioProducto sp = new ServicioProducto();
        List<Producto> l = sp.listarProductos();
        ArrayList<String> p = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            p.add(l.get(i).getNombre());
        }
        producto.setItems(FXCollections.observableArrayList(p));
        final int initialValue = 0;

        SpinnerValueFactory<Integer> valueFactory
                = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5000, initialValue);
        spinner.setValueFactory(valueFactory);
        spinner.setEditable(true);
    }    
    
    private void editar(ActionEvent event) {
        ServicioCasilla sc = new ServicioCasilla();
        Producto p = new Producto(producto.getValue().toString(), 0);
        String codigo = new String();
        List<Casilla> listc = sc.listarCasilla();
        int num = 1;
        for (int i = 1; i < listc.size(); i++) {
            if (!String.valueOf(i).equals(listc.get(i).getCodigo())) {
                sc.crearCasilla(new Casilla(codigo, i, p));
                break;
            }
            num = i;
        }
        sc.ActualizarCasilla(new Casilla(String.valueOf(num + 1), spinner.getValue(), p));
        Stage stage = (Stage) editarProducto.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
}

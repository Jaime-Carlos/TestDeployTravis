 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

 import DAOS.DAOSesion;
 import Modelo.Caja;
 import Modelo.Casilla;
 import Modelo.Producto;
 import Modelo.Sesion;
 import Servicios.ServicioCaja;
 import Servicios.ServicioCasilla;
 import Servicios.ServicioTransaccion;
 import com.jfoenix.controls.JFXButton;
 import javafx.animation.KeyFrame;
 import javafx.animation.KeyValue;
 import javafx.animation.Timeline;
 import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.fxml.Initializable;
 import javafx.scene.Node;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.ChoiceBox;
 import javafx.scene.control.Label;
 import javafx.scene.control.ListView;
 import javafx.scene.image.Image;
 import javafx.stage.Modality;
 import javafx.stage.Stage;

 import java.io.IOException;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.ResourceBundle;

/**
 *
 * @author Carlos
 */
public class FXMLDocumentController implements Initializable {

    private ObservableList<String> lista;

    @FXML
    private Button insertar;

    @FXML
    private Label dineroingresado;

    @FXML
    private ChoiceBox box;

    @FXML
    private Label status;

    @FXML
    private ListView list;

    @FXML
    private JFXButton adminbutton;

    @FXML
    private Label cambio;

    @FXML
    private void adminMode(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Vista/FXMLUsuarioLogin.fxml"));
        Scene home_scene_page = new Scene(home_page_parent);
        Stage app_stage = new Stage();
        Stage main_app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //(Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene_page);
        app_stage.getIcons().add(new Image("Utility/vending_machine.png"));
        app_stage.initOwner(main_app);
        app_stage.setResizable(false);
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.setTitle("Login");
        app_stage.showAndWait();
        ServicioCasilla servcasilla = new ServicioCasilla();
        List<Casilla> listaCasi = servcasilla.listarCasilla();
        list.getItems().clear();
        for (int i = 0; i < listaCasi.size(); i++) {
            list.getItems().add("# " + listaCasi.get(i).getCodigo()
                    + " Producto: " + listaCasi.get(i).getProducto().getNombre()
                    + " Disponible: " + listaCasi.get(i).getNumeroDeProductos()
                    + " Precio: " + listaCasi.get(i).getProducto().getPrecio());
        }
        ServicioCaja sc = new ServicioCaja();
        List<Caja> l = sc.listAll();
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            al.add(String.valueOf(l.get(i).getDenominacion()));
        }
        lista = FXCollections.observableArrayList(al);
        box.setItems(lista);
    }

    @FXML
    private void insertarDinero(ActionEvent event) {
        if (box.getValue() != null) {
            int dinero = Integer.valueOf(box.getValue().toString()) + Integer.valueOf(dineroingresado.getText());
            DAOSesion daos = new DAOSesion();
            daos.update(new Sesion(dinero, true));
            ServicioCaja sc = new ServicioCaja();
            sc.insertarMonedas(new Caja(Integer.valueOf(box.getValue().toString()), 1));
            dineroingresado.setText(String.valueOf(dinero));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DAOSesion daos = new DAOSesion();
        int val = daos.listSesion().getDineroIngresado();
        dineroingresado.setText(String.valueOf(val));
        ServicioCasilla servcasilla = new ServicioCasilla();
        List<Casilla> listaCasi = servcasilla.listarCasilla();
        for (int i = 0; i < listaCasi.size(); i++) {
            list.getItems().add("# " + listaCasi.get(i).getCodigo()
                    + " Producto: " + listaCasi.get(i).getProducto().getNombre()
                    + " Disponible: " + listaCasi.get(i).getNumeroDeProductos()
                    + " Precio: " + listaCasi.get(i).getProducto().getPrecio());
        }
        ServicioCaja sc = new ServicioCaja();
        List<Caja> l = sc.listAll();
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            al.add(String.valueOf(l.get(i).getDenominacion()));
        }
        lista = FXCollections.observableArrayList(al);
        box.setItems(lista);
    }

    public void selecionarProducto(ActionEvent event) {
        Timeline timeline = new Timeline();
        if (list.getSelectionModel().getSelectedItems().size() == 1) {

            System.out.println(list.getSelectionModel().getSelectedItem());
            ServicioTransaccion st = new ServicioTransaccion();
            String p[] = list.getSelectionModel().getSelectedItem().toString().split(" ");
            if (Integer.valueOf(p[5]) == 0) {
                timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(0.1), new KeyValue(status.textProperty(), "No hay productos disponibles")));
                timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(5), new KeyValue(status.textProperty(), "Bienvenido a la Maquina Dispensadora")));
                timeline.play();
                return;
            }
            DAOSesion daos = new DAOSesion();
            int retorno = st.calcularRetorno(new Casilla(p[1], Integer.valueOf(p[5]), new Producto(p[3], Integer.valueOf(p[7]))));
            System.out.println(retorno);
            if (retorno >= 0) {
                dineroingresado.setText("0");
                daos.update(new Sesion(0, false));
                ServicioCasilla servcasilla = new ServicioCasilla();
                List<Casilla> lista1 = servcasilla.listarCasilla();
                list.getItems().clear();
                for (int i = 0; i < lista1.size(); i++) {
                    list.getItems().add("# " + lista1.get(i).getCodigo()
                            + " Producto: " + lista1.get(i).getProducto().getNombre()
                            + " Disponible: " + lista1.get(i).getNumeroDeProductos()
                            + " Precio: " + lista1.get(i).getProducto().getPrecio());
                }
                cambio.setText(String.valueOf(retorno));
                timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(0.1), new KeyValue(status.textProperty(), "Gracias por su Compra")));
                timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(5), new KeyValue(status.textProperty(), "Bienvenido a la Maquina Dispensadora")));
                timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(5), new KeyValue(cambio.textProperty(), "-")));
                timeline.play();
            } else {
                if (Integer.valueOf(p[7]) > Integer.valueOf(dineroingresado.getText())) {
                    timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(0.1), new KeyValue(status.textProperty(), "No le alcanza")));
                    timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(5), new KeyValue(status.textProperty(), "Bienvenido a la Maquina Dispensadora")));
                    timeline.play();
                } else {
                    timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(0.1), new KeyValue(status.textProperty(), "No hay suficientes Monedas")));
                    timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(5), new KeyValue(status.textProperty(), "Bienvenido a la Maquina Dispensadora")));
                    timeline.play();
                }
                //statu.setText(String.valueOf("No se pudo Realizar la compra"));

            }
            //detener la animacion con estop pero como esperar

            status.setText("Bienvenido a la Maquina Dispensadora");
        }
    }

    @FXML
    public void cancelarT(ActionEvent event) {
        ServicioTransaccion st = new ServicioTransaccion();
        st.calcularposibilidadDeVueltas(Integer.valueOf(dineroingresado.getText()));
        DAOSesion daos = new DAOSesion();
        daos.update(new Sesion(0, false));
        dineroingresado.setText("0");
    }
}

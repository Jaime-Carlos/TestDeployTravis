/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAOS.DAOTransaccion;
import Modelo.Transaccion;
import Servicios.ServicioTransaccion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class PaginaTransaccionesController implements Initializable {

      @FXML
    private ListView list;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DAOTransaccion DAOT =new DAOTransaccion();
        ServicioTransaccion srvtr = new ServicioTransaccion();
        List<Transaccion> lista = DAOT.listALL();
        
        for (int i = 0; i < lista.size(); i++) {
            list.getItems().add("Nombre Producto:" + lista.get(i).getNombreProducto()
                    + " Valor Recibido: " + lista.get(i).getValorRecibido()
                    + " Valor Transacción: " + lista.get(i).getValorTransaccion()
                    + " Valor Retorno: " + lista.get(i).getValordeRetorno());
        }
        
    }    
    
}

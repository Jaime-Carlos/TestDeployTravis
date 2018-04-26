/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administradores;

import DAOS.DAOTransaccion;
import Modelo.Transaccion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class AdministradorTransaccion {

    List<Transaccion> listTransacciones = new ArrayList<>();

    public AdministradorTransaccion() {
    }

    public void crear(Transaccion transaccion) {
        DAOTransaccion daot = new DAOTransaccion();
        daot.create(transaccion);
        listTransacciones.add(transaccion);
    }

    public List<Transaccion> listar() {
        DAOTransaccion daot = new DAOTransaccion();
        listTransacciones=daot.listALL();
        return listTransacciones;
    }
;

}

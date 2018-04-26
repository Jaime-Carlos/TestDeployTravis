/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
 */
package Servicios;

import Administradores.AdministradorCaja;
import Modelo.Caja;

import java.util.List;

/**
 *
 * @author Carlos
 */
public class ServicioCaja {
    public void insertarMonedas(Caja c){
        AdministradorCaja ac=new AdministradorCaja();
        List<Caja> listaCajas=ac.listarCajas();
        for (int i = 0; i < listaCajas.size(); i++) {
            if (listaCajas.get(i).getDenominacion()==c.getDenominacion()) {
                c.setCantidad(listaCajas.get(i).getCantidad()+c.getCantidad());
                ac.actualizar(c);
                break;
            }
        }
        
    }

    public List<Caja> listAll() {
        AdministradorCaja ac=new AdministradorCaja();
        return  ac.listarCajas();
    }

    public void eliminar(String denominacion) {
        AdministradorCaja ac=new AdministradorCaja();
        ac.eliminar(Integer.valueOf(denominacion));
    }

   

    public void ActualizarCaja(Caja c) {
        AdministradorCaja ac=new AdministradorCaja();
        ac.actualizar(c);
    }



    public void crearCaja(Caja c) {
        AdministradorCaja ac=new AdministradorCaja();
        ac.crear(c);
    }
}

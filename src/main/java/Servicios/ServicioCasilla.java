/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Administradores.AdministradorCasilla;
import Modelo.Casilla;

import java.util.List;

/**
 *
 * @author Carlos
 */
public class ServicioCasilla {
    public List<Casilla> listarCasilla(){
        AdministradorCasilla ac=new AdministradorCasilla();
        return ac.listarCasillas();
    }
    public void eliminarCasilla(String nombre) {
        AdministradorCasilla ac=new AdministradorCasilla();
        ac.eliminar(nombre);
    }

    public void crearCasilla(Casilla casilla) {
        AdministradorCasilla ac=new AdministradorCasilla();
        ac.crear(casilla);
    }

    public void ActualizarCasilla(Casilla casilla) {
        AdministradorCasilla ac=new AdministradorCasilla();
        ac.actualizar(casilla);
    }
}

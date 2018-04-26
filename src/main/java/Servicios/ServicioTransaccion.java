/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Administradores.AdministradorCaja;
import Administradores.AdministradorCasilla;
import Administradores.AdministradorTransaccion;
import DAOS.DAOSesion;
import Modelo.Caja;
import Modelo.Casilla;
import Modelo.Transaccion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ServicioTransaccion {

    public int calcularRetorno(Casilla c) {
        c.setNumeroDeProductos(c.getNumeroDeProductos() - 1);
        AdministradorCasilla ac = new AdministradorCasilla();
        DAOSesion daos = new DAOSesion();
        int valorRecibido = daos.listSesion().getDineroIngresado();
        int valorProducto = c.getProducto().getPrecio();
        int retorno = valorRecibido - valorProducto;
        if (valorRecibido > valorProducto) {
            boolean f = calcularposibilidadDeVueltas(retorno);
            System.out.println(f);
            AdministradorTransaccion aT = new AdministradorTransaccion();
            aT.crear(new Transaccion(valorProducto, valorRecibido, retorno, c.getProducto().getNombre()));
            ac.actualizar(c);
            if (f) {
                return retorno;
            }
        }
        if (valorProducto == valorRecibido) {
            AdministradorTransaccion aT = new AdministradorTransaccion();
            aT.crear(new Transaccion(valorProducto, valorRecibido, retorno, c.getProducto().getNombre()));
            ac.actualizar(c);
            return 0;
        }
        return -1;
    }

    public boolean calcularposibilidadDeVueltas(int retorno) {
        int num = retorno;
        AdministradorCaja ac = new AdministradorCaja();
        List<Caja> bDList = ac.listarCajas();
        List<Caja> vueltas = new ArrayList<>();
        List<Integer> lugares = new ArrayList<>();
        for (int i = bDList.size() - 1; 0 <= i; i--) {
            int cantmon = retorno / bDList.get(i).getDenominacion();
            if (cantmon != 0 && bDList.get(i).getCantidad() > cantmon) {
                vueltas.add(new Caja(bDList.get(i).getDenominacion(), cantmon));
                retorno = retorno - cantmon * bDList.get(i).getDenominacion();
                lugares.add(i);
            }
        }
        int suma = 0;
        for (int i = 0; i < vueltas.size(); i++) {
            suma = vueltas.get(i).getDenominacion() * vueltas.get(i).getCantidad() + suma;
        }
        if (num != suma) {
            return false;
        }

        for (int i = 0; i < vueltas.size(); i++) {
            int x = bDList.get(lugares.get(i)).getCantidad();
            int y = vueltas.get(i).getCantidad();
            int cantidad = x - y;
            System.out.println(bDList.get(i).getDenominacion() + "/" + bDList.get(i).getCantidad());
            System.out.println(cantidad);
            ac.actualizar(new Caja(vueltas.get(i).getDenominacion(), cantidad));
        }

        return true;
    }
}

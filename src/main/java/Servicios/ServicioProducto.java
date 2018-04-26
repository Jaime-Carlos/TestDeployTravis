/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Administradores.AdministradorProducto;
import Modelo.Producto;

import java.util.List;

/**
 *
 * @author carlo
 */
public class ServicioProducto {
    public List<Producto> listarProductos(){
        AdministradorProducto administradorProducto=new AdministradorProducto();
        return administradorProducto.listarProductoss();
    }

    public void eliminarproducto(String nombre) {
        AdministradorProducto ap=new AdministradorProducto();
        ap.eliminar(nombre);
    }

    public void crearProd(Producto producto) {
        AdministradorProducto ap=new AdministradorProducto();
        ap.agregar(producto);
    }

    public void ActualizarProducto(Producto producto) {
        AdministradorProducto ap=new AdministradorProducto();
        ap.actualizar(producto);
    }
}

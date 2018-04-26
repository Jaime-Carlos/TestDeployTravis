/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administradores;

import DAOS.DAOProducto;
import Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class AdministradorProducto {

    List<Producto> listProducto = new ArrayList<>();

    public AdministradorProducto() {
    }

    public void agregar(Producto producto) {
        DAOProducto daop = new DAOProducto();
        listProducto.add(producto);
        daop.create(producto);
    }

    public void eliminar(String nombre) {
        DAOProducto daop = new DAOProducto();
        for (int i = 0; i < listProducto.size(); i++) {
            if (listProducto.get(i).getNombre().equals(nombre)) {
                listProducto.remove(i);
                break;
            }
        }
        daop.delete(nombre);
    }

    public void actualizar(Producto producto) {
        DAOProducto daop = new DAOProducto();
        daop.update(producto);
        for (int i = 0; i < listProducto.size(); i++) {
            if (listProducto.get(i).getNombre().equals(producto.getNombre())) {
                listProducto.get(i).setNombre(producto.getNombre());
                listProducto.get(i).setPrecio(producto.getPrecio());
                break;
            }
        }

    }

    public List<Producto> listarProductoss() {
        DAOProducto daop = new DAOProducto();
        listProducto = daop.listALL();
        return listProducto;
    }

}

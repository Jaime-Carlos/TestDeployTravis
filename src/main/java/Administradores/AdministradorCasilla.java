/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administradores;

import DAOS.DAOCasilla;
import Modelo.Casilla;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class AdministradorCasilla {

    List<Casilla> listCasillas = new ArrayList<>();

    public AdministradorCasilla() {
    }

    public void crear(Casilla casilla) {
        DAOCasilla daoc = new DAOCasilla();
        daoc.create(casilla);
        listCasillas.add(casilla);
    }
    public void eliminar(String codigo){
        DAOCasilla daoc=new DAOCasilla();
        daoc.delete(codigo);
        for (int i = 0; i < listCasillas.size(); i++) {
            if(listCasillas.get(i).getCodigo().equals(codigo)){
                listCasillas.remove(i);
                break;
            }
        }
    }
    public void actualizar(Casilla casilla){
        DAOCasilla daoc=new DAOCasilla();
        daoc.update(casilla);
        listCasillas=daoc.listALL();
        
    }
    public List<Casilla> listarCasillas(){
        DAOCasilla daoc=new DAOCasilla();
        listCasillas=daoc.listALL();
        return listCasillas;
    }
}

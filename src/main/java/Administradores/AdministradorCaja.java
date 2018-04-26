/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administradores;

import DAOS.DAOCaja;
import Modelo.Caja;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos le encanta la posha alemana y el lo sabe(Hasta qye funcione esta mondá)
 * que vergas
 * paso 
 * ASwqeqe           ___
 * wqe             /    \
 * wq             |
 * ewq  /---------
 *     |
 *      \---------       \
 *                  -----
 * 
 */
public class AdministradorCaja {
    List<Caja> listCajas = new ArrayList<>();

    public AdministradorCaja() {
    }

    public void crear(Caja caja) {
        DAOCaja daoc = new DAOCaja();
        daoc.create(caja);
        listCajas.add(caja);
    }
    public void eliminar(int denominacion){
        DAOCaja daoc=new DAOCaja();
        daoc.delete(denominacion);
        for (int i = 0; i < listCajas.size(); i++) {
            if(listCajas.get(i).getDenominacion()==(denominacion)){
                listCajas.remove(i);
                break;
            }
        }
    }
    public void actualizar(Caja caja){
        DAOCaja daoc=new DAOCaja();
        daoc.update(caja);
        listCajas=daoc.listALL();
        
    }

    public List<Caja> listarCajas(){
        DAOCaja daoc=new DAOCaja();
        listCajas=daoc.listALL();
        return listCajas;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author carlo
 */
public class Sesion {
    private int dineroIngresado;
    private boolean isActive;

    public Sesion(int dineroIngresado, boolean isActive) {
        this.dineroIngresado = dineroIngresado;
        this.isActive = isActive;
    }

    public int getDineroIngresado() {
        return dineroIngresado;
    }

    public void setDineroIngresado(int dineroIngresado) {
        this.dineroIngresado = dineroIngresado;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}
    
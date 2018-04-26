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
public class Transaccion {

    private int valorTransaccion;
    private int valorRecibido;
    private int valordeRetorno;
    private String nombreProducto;

    public Transaccion(int valorTransaccion, int valorRecibido, int valordeRetorno, String nombreProducto) {
        this.valorTransaccion = valorTransaccion;
        this.valorRecibido = valorRecibido;
        this.valordeRetorno = valordeRetorno;
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(int valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public int getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(int valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public int getValordeRetorno() {
        return valordeRetorno;
    }

    public void setValordeRetorno(int valordeRetorno) {
        this.valordeRetorno = valordeRetorno;
    }

}

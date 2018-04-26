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
public class Casilla {
    private String codigo;
    private int numeroDeProductos;
    private Producto producto;

    public Casilla(String codigo, int numeroDeProductos, Producto producto) {
        this.codigo = codigo;
        this.numeroDeProductos = numeroDeProductos;
        this.producto = producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNumeroDeProductos() {
        return numeroDeProductos;
    }

    public void setNumeroDeProductos(int numeroDeProductos) {
        this.numeroDeProductos = numeroDeProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}

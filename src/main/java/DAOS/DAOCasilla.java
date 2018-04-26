/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. holaaaa
 */
package DAOS;

import Modelo.Casilla;
import Modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/** hashajshjash
 *
 * @author carlo
 */
public class DAOCasilla {
    public boolean create(Casilla casilla) {
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:DBmaquina";
            Properties props = new Properties();
            props.setProperty("user", "root");
            props.setProperty("password", "root");
            props.setProperty("useSSL", "false");
            props.setProperty("useUnicode","true");
            props.setProperty("useJDBCCompliantTimezoneShift","true");
            props.setProperty("useLegacyDatetimeCode","false");
            props.setProperty("serverTimezone","UTC");
            Connection conn = DriverManager.getConnection(url, props);
            stmt = conn.createStatement();
            String sql = "INSERT INTO casilla (codigo,numerodeproductos,nombreproducto) "
                    + "VALUES (?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, casilla.getCodigo());
            preparedStatement.setInt(2, casilla.getNumeroDeProductos());
            preparedStatement.setString(3, casilla.getProducto().getNombre());
            preparedStatement.execute();
            preparedStatement.close();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }
    public boolean update(Casilla casilla) {
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:DBmaquina";
            Properties props = new Properties();
            props.setProperty("user", "root");
            props.setProperty("password", "root");
            props.setProperty("useSSL", "false");
            props.setProperty("useUnicode","true");
            props.setProperty("useJDBCCompliantTimezoneShift","true");
            props.setProperty("useLegacyDatetimeCode","false");
            props.setProperty("serverTimezone","UTC");
            Connection conn = DriverManager.getConnection(url, props);
            stmt = conn.createStatement();
            String sql = "UPDATE casilla set numerodeproductos = ?,nombreproducto=? where codigo= ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,casilla.getNumeroDeProductos());
            preparedStatement.setString(2, casilla.getProducto().getNombre());
            preparedStatement.setString(3, casilla.getCodigo());
            preparedStatement.execute();
            preparedStatement.close();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }
    public boolean delete(String codigo) {
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:DBmaquina";
            Properties props = new Properties();
            props.setProperty("user", "root");
            props.setProperty("password", "root");
            props.setProperty("useSSL", "false");
            props.setProperty("useUnicode","true");
            props.setProperty("useJDBCCompliantTimezoneShift","true");
            props.setProperty("useLegacyDatetimeCode","false");
            props.setProperty("serverTimezone","UTC");
            Connection conn = DriverManager.getConnection(url, props);
            stmt = conn.createStatement();
            String sql = "DELETE from casilla where codigo= ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, codigo);
            preparedStatement.execute();
            preparedStatement.close();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }
    public List<Casilla> listALL() {
        Connection c = null;
        Statement stmt = null;
        List<Casilla> lisaCasilla = new ArrayList();
        try {

            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:DBmaquina";
            Properties props = new Properties();
            props.setProperty("user", "root");
            props.setProperty("password", "root");
            props.setProperty("useSSL", "false");
            props.setProperty("useUnicode","true");
            props.setProperty("useJDBCCompliantTimezoneShift","true");
            props.setProperty("useLegacyDatetimeCode","false");
            props.setProperty("serverTimezone","UTC");
            Connection conn = DriverManager.getConnection(url, props);
            stmt = conn.createStatement();
            String sql = "SELECT codigo,numerodeproductos,nombreproducto,precio from casilla JOIN producto on (casilla.nombreproducto=producto.nombre);";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                int numerodeproductos = rs.getInt("numerodeproductos");
                String nombreproducto = rs.getString("nombreproducto");
                int precio = rs.getInt("precio");
                Producto prod=new Producto(nombreproducto, precio);
                Casilla cas=new Casilla(codigo, numerodeproductos, prod);
                lisaCasilla.add(cas);
            }
            rs.close();
            stmt.close();
            conn.close();
            return lisaCasilla;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}

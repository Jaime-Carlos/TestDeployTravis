/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Modelo.Transaccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author carlo
 */
public class DAOTransaccion {

    public boolean create(Transaccion t) {
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
            String sql = "INSERT INTO transaccion (valortransaccion,valorrecibido,valorderetorno,nombreProducto) "
                    + "VALUES (?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, t.getValorTransaccion());
            preparedStatement.setInt(2, t.getValorRecibido());
            preparedStatement.setInt(3, t.getValordeRetorno());
            preparedStatement.setString(4, t.getNombreProducto());
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

    public List<Transaccion> listALL() {
        Connection c = null;
        Statement stmt = null;
        List<Transaccion> listTransacciones = new ArrayList();
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
            String sql = "select * from transaccion;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int valorTransaccion = rs.getInt("valortransaccion");
                int valorRecibido = rs.getInt("valorrecibido");
                int valordeRetorno = rs.getInt("valorderetorno");
                String nombreProducto = rs.getString("nombreproducto");
                Transaccion t = new Transaccion(valorTransaccion, valorRecibido, valordeRetorno, nombreProducto);
                listTransacciones.add(t);
            }
            rs.close();
            stmt.close();
            conn.close();
            return listTransacciones;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}

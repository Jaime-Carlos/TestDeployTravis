/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Modelo.Caja;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author carlo
 */
public class DAOCaja {
    public boolean create(Caja c) {
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
            String sql = "INSERT INTO caja (denominacion,cantidad) "
                    + "VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, c.getDenominacion());
            preparedStatement.setInt(2, c.getCantidad());
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
    public boolean update(Caja c) {
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
            String sql = "UPDATE caja set cantidad = ? where denominacion= ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, c.getCantidad());
            preparedStatement.setInt(2, c.getDenominacion());
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
    public boolean delete(int denominacion) {
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
            String sql = "DELETE from caja where denominacion= ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, denominacion);
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
    public List<Caja> listALL() {
        Connection c = null;
        Statement stmt = null;
        List<Caja> lisaCajas = new ArrayList();
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
            String sql = "select * from caja ORDER BY denominacion asc;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int denominacion = rs.getInt("denominacion");
                int cantidad = rs.getInt("cantidad");
                Caja ca = new Caja(denominacion, cantidad);
                lisaCajas.add(ca);
            }
            rs.close();
            stmt.close();
            conn.close();
            return lisaCajas;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}

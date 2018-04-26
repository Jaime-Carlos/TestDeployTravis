/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Modelo.Sesion;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author carlo
 */
public class DAOSesion {
    public boolean update(Sesion sesion) {
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
            String sql = "UPDATE sesion set dineroingresado = ?,isactive= ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, sesion.getDineroIngresado());
            preparedStatement.setBoolean(2, sesion.isIsActive());
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
     public Sesion listSesion() {
        Connection c = null;
        Statement stmt = null;
        Sesion sesion = null;
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
            String sql = "select * from sesion;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int dineroingresado = rs.getInt("dineroingresado");
                boolean isActive = rs.getBoolean("isActive");
                sesion=new Sesion(dineroingresado, isActive);
            }
            rs.close();
            stmt.close();
            conn.close();
            return sesion;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}

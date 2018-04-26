/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Modelo.Administrador;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author carlo
 */
public class DAOAdministrador {

    public boolean create(Administrador admin) {
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
            String sql = "INSERT INTO administrador (usuario,contrasena) "
                    + "VALUES ('admin','admin');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            System.out.println("DAOS.DAOAdministrador.create()");
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean update(Administrador admin) {
        Connection c = null;
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
            String sql = "UPDATE administrador set contrasena = ? where usuario= ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, admin.getContraseña());
            preparedStatement.setString(2, admin.getUsuario());
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

    public Administrador listAdmin() {
        Connection c = null;
        Statement stmt = null;
        Administrador admin = null;
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
            String sql = "select * from administrador;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                admin = new Administrador(usuario, contrasena);
            }
            rs.close();
            stmt.close();
            conn.close();
            return admin;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}

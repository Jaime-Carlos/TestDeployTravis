package DBUtility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author carlo
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
public class DBStartup {

    public boolean createTables() {
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
            String[] types = {"TABLE"};
            ResultSet rs = conn.getMetaData().getTables(null, null, "%", types);
            HashMap p = new HashMap<String, Boolean>();
            p.put("administrador", false);
            p.put("caja", false);
            p.put("casilla", false);
            p.put("producto", false);
            p.put("sesion", false);
            p.put("transaccion", false);
            int a = 0;
            while (rs.next()) {
                if (p.containsKey(rs.getString("TABLE_NAME"))) {
                    a++;
                }
            }
            System.out.println(a);
            if (a == 6) {
                System.out.println("Ya fueron Creadas Las Tablas");
                return true;
            }
            stmt = conn.createStatement();
            String sql = "CREATE TABLE administrador "
                    + "(usuario VARCHAR(50) PRIMARY KEY     NOT NULL,"
                    + " contrasena  VARCHAR(50)    NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE caja "
                    + "(denominacion INT PRIMARY KEY     NOT NULL,"
                    + " cantidad            INT     NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE producto "
                    + "(nombre VARCHAR(50) PRIMARY KEY     NOT NULL,"
                    + " precio            INT     NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE casilla "
                    + "(codigo VARCHAR(50) PRIMARY KEY     NOT NULL,"
                    + " numerodeproductos            INT     NOT NULL,"
                    + "nombreproducto VARCHAR(50) references producto(nombre))";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE sesion "
                    + "(dineroIngresado INT     NOT NULL,"
                    + " isActive            boolean     NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE transaccion "
                    + "(valorTransaccion INT     NOT NULL,"
                    + "valorRecibido            INT     NOT NULL,"
                    + "valordeRetorno INT NOT NULL,"
                    + "nombreProducto VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(sql);
            sql="INSERT INTO administrador (usuario,contrasena) VALUES (admin,admin)";
            stmt.executeUpdate(sql);
            sql="INSERT INTO sesion (dineroIngresado,isActive) VALUES (0,flase)";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return true;
    }
}

package com.mycompany.bibliofx;
import java.sql.*;

public final class ConectarDB {
    public static Connection conn;
       
    public static Connection conectar () {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/biblioFX";
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Não foi possível conectar ao banco de dados");
        return null;
    }
}

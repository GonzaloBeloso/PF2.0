package SQL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    java.sql.Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String host = "127.0.0.1";
    String user = "root";
    String pass = "";
    String dtbs = "pf";

    public Boolean realizarConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs
                    + "?" + "user=" + user + "&password=" + pass + "&useSSL=false";
            conn = (java.sql.Connection) DriverManager.getConnection(newConnectionURL);
            return true;
        }
        catch(SQLException | ClassNotFoundException ex) {
            System.out.println("Error ");
            ex.printStackTrace();
            return false;
        }
    }

    // Abrir
    public java.sql.Connection getConnection(){
        return conn;
    }

    // Cerrar
    public void closeConnection(){
        conn = null;
    }
}
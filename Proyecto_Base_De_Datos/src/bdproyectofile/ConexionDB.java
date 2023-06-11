/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Jose
 */
public class ConexionDB {
    
    private static String urlConnect = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static String username ;
    private static String password ;
    private Connection connect = null;
    public ConexionDB(){
        this.username= null;
        this.password = null;
    }
    
    public void SetConetionSucessfull(String username,String password){
        try{
            this.username = username;
            this.password = password;
            connect = DriverManager.getConnection(urlConnect, this.username, this.password);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public ConexionDB(String username,String password){
        SetConetionSucessfull(username,password);
    }
    
    public void setData(String username,String password) {
        SetConetionSucessfull(username,password);
    }

    public Connection getConnection() throws SQLException {
        return connect;
    }
    
}

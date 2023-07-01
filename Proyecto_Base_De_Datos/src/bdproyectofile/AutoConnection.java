/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;
import java.sql.Connection;
/**
 *
 * @author Jose
 */
public class AutoConnection {
    private Connection cnn = null;
    
    public AutoConnection(String user,String password){
        ConexionDB connect_dat= new ConexionDB();
        connect_dat.setData(user,password);
        try{
            this.cnn = connect_dat.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return this.cnn;
    }
}

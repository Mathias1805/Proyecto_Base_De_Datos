/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author Jose
 */
public class getDataPacientFromDB {
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    public getDataPacientFromDB(){
    }
    public getDataPacientFromDB(Connection myCon){
        try{
            this.myCon = myCon;
            st = myCon.createStatement();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public void setPerson(Paciente p,String dni){
        if (p == null){
                p = new Paciente();
            }
        try{
            String query = "SELECT * FROM PACIENTE WHERE DNI = "+"'" +dni+"'";
            rs = st.executeQuery(query);
            if (rs.next()){
                p.setDNI(rs.getString(1));
                p.setName(rs.getString(2));
                p.setSecondName(rs.getString(3));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
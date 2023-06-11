/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author Jose
 */

public class GetAllPcts {
    private List<Paciente> allpcts = new ArrayList<Paciente>();
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    public GetAllPcts(){}
    public GetAllPcts(Connection myCon){
        try{
            this.myCon = myCon;
            st = myCon.createStatement();
            this.setListPcts();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private void setListPcts(){
        try{
            String query = "SELECT * FROM PACIENTE";
            rs = st.executeQuery(query);
            
            while(rs.next()){
                Paciente p = new Paciente();
                p.setDNI(rs.getString(1));
                p.setName(rs.getString(2));
                p.setSecondName(rs.getString(3));
                allpcts.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Paciente> GetPctsList(){
        
        return this.allpcts;
        
    }
}

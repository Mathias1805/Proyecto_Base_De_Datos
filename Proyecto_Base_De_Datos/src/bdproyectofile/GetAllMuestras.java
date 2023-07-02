/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
/**
 *
 * @author Jose
 */
public class GetAllMuestras {
    private List<Muestra> allmts = new ArrayList<Muestra>();
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    public GetAllMuestras(){}
    public GetAllMuestras(Connection myCon){
        try{
            this.myCon = myCon;
            st = myCon.createStatement();
            this.setListMstPcts();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private void setListMstPcts(){
        try{
            String query = "SELECT * FROM muestrasangre";
            rs = st.executeQuery(query);
            
            while(rs.next()){
                Muestra mt = new Muestra();
                mt.setDNI(rs.getString(1));
                mt.setIdtiposange(rs.getString(2));
                mt.setEstado(rs.getString(3));
                mt.setFecha(rs.getDate(4));
                allmts.add(mt);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Muestra> GetMuestrasList(){
        
        return this.allmts;
        
    }  
}

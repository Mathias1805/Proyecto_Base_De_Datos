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

/**
 *
 * @author Jose
 */
public class GetAllMuestraPctsBD {
    private List<Paciente_muestra> allpctsmt = new ArrayList<Paciente_muestra>();
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    public GetAllMuestraPctsBD(){}
    public GetAllMuestraPctsBD(Connection myCon){
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
            String query = "SELECT * FROM paciente_muestra_vista";
            rs = st.executeQuery(query);
            
            while(rs.next()){
                Paciente_muestra pm = new Paciente_muestra();
                pm.setDNI(rs.getString(1));
                pm.setName(rs.getString(2));
                pm.setSecondName(rs.getString(3));
                pm.muestra.setIdtiposange(rs.getString(4));
                pm.muestra.setEstado(rs.getString(5));
                allpctsmt.add(pm);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Paciente_muestra> GetPctsMstList(){
        
        return this.allpctsmt;
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jose
 */
public class OneConsult {
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    public OneConsult(){
    }
    public OneConsult(Connection myCon){
        try{
            this.myCon = myCon;
            st = myCon.createStatement();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public void setQuery(String query){
        try{
            rs = st.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public ResultSet getResponse(){
        return rs;
    }
        
}

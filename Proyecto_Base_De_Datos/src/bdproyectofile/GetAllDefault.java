/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class GetAllDefault {

    /**
     * @param myCon the myCon to set
     */
    public void setMyCon(Connection myCon) {
        this.myCon = myCon;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    private List<?> objList = new ArrayList<Object>();
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    public GetAllDefault(Connection myCon){
        
    }
    public List<?> GetAll(){
        return this.objList;
        
    }
}

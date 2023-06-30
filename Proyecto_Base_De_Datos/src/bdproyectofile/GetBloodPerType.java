/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jose
 */

public class GetBloodPerType {
    private List<blood_total> type_total = new ArrayList<blood_total>();
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    public GetBloodPerType(){}
    public GetBloodPerType(Connection myCon){
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
            String query = "SELECT * FROM vistal_tipo_total";
            rs = st.executeQuery(query);
            
            while(rs.next()){
                blood_total b = new blood_total();
                b.total = rs.getDouble(2);
                b.type_b = rs.getString(1);
                type_total.add(b);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<blood_total> GetTotalBloodList(){
        
        return this.type_total;
        
    }
}

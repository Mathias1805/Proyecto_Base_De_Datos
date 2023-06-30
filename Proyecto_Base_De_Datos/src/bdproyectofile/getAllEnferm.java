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
public class getAllEnferm {
    private List<Enfermera> allenferm = new ArrayList<Enfermera>();
    private Connection myCon = null;
    private Statement st = null;
    private ResultSet rs = null;
    public getAllEnferm(){}
    public getAllEnferm(Connection myCon){
        try{
            this.myCon = myCon;
            st = myCon.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private void setListPcts(String gmail,String password){
        try{
            String query = "SELECT * FROM ENFERMERA WHERE CORREO = '"+gmail+"' AND CONTRASEÑA = '"+password+"'";
            rs = st.executeQuery(query);
            
            while(rs.next()){
                Enfermera p = new Enfermera();
                p.setIdEnfermera(rs.getLong(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setCorreo(rs.getString(5));
                p.setPassword(rs.getString(6));
                allenferm.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Enfermera> GetEfrmList(String gmail,String password){
        this.setListPcts(gmail, password);
        return this.allenferm;
    }
    
}

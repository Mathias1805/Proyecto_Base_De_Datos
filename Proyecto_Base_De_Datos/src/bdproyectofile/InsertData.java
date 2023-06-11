/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Jose
 */
public class InsertData {
    private Connection myCon = null;
    public InsertData(){
        
    }
    public InsertData(Connection myCon){
        this.myCon = myCon;
    }
    public void SetConection(Connection myCon){
        this.myCon = myCon;
    }
    public void InsertInTable(Paciente p){
        try{
            String ins= "INSERT INTO PACIENTE (DNI,NOMBRE,APELLIDO) VALUES(?,?,?)";
            PreparedStatement statement = myCon.prepareStatement(ins);
            statement.setString(1,p.getDNI());
            statement.setString(2,p.getName());
            statement.setString(3,p.getSecondName());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        };
    }
    public void InsertInTable(Enfermera p){
        try{   
            String ins= "INSERT INTO ENFERMERA (idenfermera,nombre,apellido,correo,contraseña) VALUES(?,?,?,?,?)";
            PreparedStatement statement = myCon.prepareStatement(ins);
            statement.setLong(1,p.getIdEnfermera());
            statement.setString(2,p.getNombre());
            statement.setString(3,p.getApellido());
            statement.setString(4,p.getCorreo());
            statement.setString(5,p.getPassword());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        };
    }
}

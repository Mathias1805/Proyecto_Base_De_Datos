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
    public void InsertInTable(Cita c){
        try{   
            String ins= "INSERT INTO CITA (dni,idenfermera,nrolaboratorio,cantidadlt,tipocita) VALUES(?,?,?,?,?)";
            PreparedStatement statement = myCon.prepareStatement(ins);
            statement.setString(1,c.getDNI());
            statement.setLong(2, c.getIdEnfermera());
            statement.setInt(3, c.getNumLab());
            statement.setDouble(4,c.getCantidadLt());
            statement.setString(5,c.getTipocita());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        };
    }
    public void InsertInTableCt(Cita c){
        try{   
            String ins= "INSERT INTO CITA (dni,idenfermera,nrolaboratorio,tipocita) VALUES(?,?,?,?)";
            PreparedStatement statement = myCon.prepareStatement(ins);
            statement.setString(1,c.getDNI());
            statement.setLong(2, c.getIdEnfermera());
            statement.setInt(3, c.getNumLab());
            statement.setString(4,c.getTipocita());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        };
    }
        public void InsertInTable(Muestra c){
        try{   
            String ins= "UPDATE MUESTRASANGRE SET ESTADO = ? , IDTIPOSANGRE = ? WHERE DNI = '"+c.getDNI()+"'";
            PreparedStatement statement = myCon.prepareStatement(ins);
            statement.setString(1,c.getEstado());
            statement.setString(2, c.getIdtiposange());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        };
    }
}

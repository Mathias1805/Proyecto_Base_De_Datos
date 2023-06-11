/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bdproyectofile;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Jose
 */
public class BDproyectofile {

    /**
     * @param args the command line arguments
     */
    public static boolean isIlist(List<Paciente> mylist,String p){
         for (var i : mylist){
             if (i.getDNI().equals(p)){
                 return true;
             }
         }
         return false;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ConexionDB connect_dat= new ConexionDB();
        Connection test = null;
        connect_dat.setData("sqlproject_","Oracle19c");
        try{
            test = connect_dat.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        /*
        getDataPacientFromDB pData = new getDataPacientFromDB(test);
        Paciente pct_T =new Paciente();
        pData.setPerson(pct_T, "15999601");
        System.out.println("DNI :" + pct_T.getDNI()+"\nNombre :"+pct_T.getName()+"\nApellido :"+pct_T.getSecondName());
        */
        //PRELOAD
        GetAllPcts getpcts = new GetAllPcts(test);
        getAllEnferm getenfrm = new getAllEnferm(test);
        //testeos
        /*
        for (var i :getenfrm.GetEfrmList()){
            System.out.println(i);
        }
        System.out.println("----------------------------------------------------------");
        for (var i : getpcts.GetPctsList()){
            System.out.println(i);
            
        }
        */
        Enfermera p = new Enfermera();
        System.out.println("Ingrese el iddel enfermera a registrar : ");
        Long id = input.nextLong();
        
        System.out.println("Ingrese el nombre del enfermera a registrar : ");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("Ingrese el Apellido del enfermera a registrar : ");
        String appellido = input.nextLine();
        System.out.println("Ingrese la correo de la enf : ");
        String Corr = input.nextLine();
        System.out.println("Ingrese la contrasña de la enf : ");
        String dni = input.nextLine();
        p.setIdEnfermera(id);
        p.setNombre(name);
        p.setApellido(appellido);
        p.setCorreo(Corr);
        p.setPassword(dni);
        InsertData ins = new InsertData();
        ins.SetConection(test);
        ins.InsertInTable(p);
        
    }
    
}

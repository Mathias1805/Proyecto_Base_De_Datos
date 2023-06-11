/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;

/**
 *
 * @author Jose
 */
public class Paciente {
    private String DNI;
    private String name;
    private String secondName;

    
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    @Override
    public String toString(){
        return "DNI : "+this.getDNI()+"\nNOMBRE : "+this.getName()+"\nAPELLIDO : "+this.getSecondName();
    }
    
}

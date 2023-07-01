/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bdproyectofile;
import java.time.LocalDate;
/**
 *
 * @author Jose
 */


public class Cita {

    /**
     * @return the cantidadLt
     */
    
    private long idCita;
    private String DNI;
    private long idEnfermera;
    private int numLab;
    private double cantidadLt;
    private LocalDate fecha_cita;
    private String tipocita;
    
    public double getCantidadLt() {
        return cantidadLt;
    }

    /**
     * @param cantidadLt the cantidadLt to set
     */
    public void setCantidadLt(double cantidadLt) {
        this.cantidadLt = cantidadLt;
    }

    /**
     * @return the tipocita
     */
    public String getTipocita() {
        return tipocita;
    }

    /**
     * @param tipocita the tipocita to set
     */
    public void setTipocita(String tipocita) {
        this.tipocita = tipocita;
    }

    
    public long getIdCita() {
        return idCita;
    }

   
    public void setIdCita(long idCita) {
        this.idCita = idCita;
    }

   
    public String getDNI() {
        return DNI;
    }

    
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public long getIdEnfermera() {
        return idEnfermera;
    }

    public void setIdEnfermera(long idEnfermera) {
        this.idEnfermera = idEnfermera;
    }

   
    public LocalDate getFecha_cita() {
        return fecha_cita;
    }

   
    public void setFecha_cita(LocalDate fecha_cita) {
        this.fecha_cita = fecha_cita;
    }
     
    public int getNumLab() {
        return numLab;
    }

    public void setNumLab(int numLab) {
        this.numLab = numLab;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;
import java.sql.Date;
/**
 *
 * @author Jose
 */
public class Muestra extends Paciente {

    /**
     * @return the idtiposange
     */
    public String getIdtiposange() {
        return idtiposange;
    }

    /**
     * @param idtiposange the idtiposange to set
     */
    public void setIdtiposange(String idtiposange) {
        this.idtiposange = idtiposange;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    private String idtiposange;
    private String Estado;
    private Date fecha;
}

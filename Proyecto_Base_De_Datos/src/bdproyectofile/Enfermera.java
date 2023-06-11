/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproyectofile;

/**
 *
 * @author Jose
 */


public class Enfermera {
    private long idEnfermera;
    private String nombre;
    private String apellido;
    private long cantidadLT;
    private String correo;
    private String password;
    private byte[] foto;
    
    public long getIdEnfermera() {
        return idEnfermera;
    }

    public void setIdEnfermera(long idEnfermera) {
        this.idEnfermera = idEnfermera;
    }
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

   
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getCantidadLT() {
        return cantidadLT;
    }

    public void setCantidadLT(long cantidadLT) {
        this.cantidadLT = cantidadLT;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public String toString(){
        return "ID :"+this.getIdEnfermera()+"\nNombre : "
                +this.getNombre()+"\nApellido :"+this.getApellido()+"\nCorreo : "+this.getCorreo()+
                "\nPassword : "+this.getPassword();
    }

}

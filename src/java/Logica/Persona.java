package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected long id_persona;
    @Basic
    protected String dni;
    protected String nombre;
    protected String apellido;
    protected String direccion;
    @Temporal (TemporalType.DATE)
    protected Date fecha_nac;

    public Persona() {
    }

    public Persona(long id_persona, String dni, String nombre, String apellido, String direccion, Date fecha_nac) {
        this.id_persona = id_persona;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fecha_nac = fecha_nac;
    }

    public long getId_persona() {
        return id_persona;
    }

    public void setId_persona(long id_persona) {
        this.id_persona = id_persona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

   
    
}

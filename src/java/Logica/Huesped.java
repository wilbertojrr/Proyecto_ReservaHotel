package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Huesped extends Persona implements Serializable {
    @Basic
    String profesion;
    @OneToMany
    List<Reserva> listRes;

    public Huesped() {
    }

    public Huesped(String profesion, List<Reserva> listRes, long id_persona, String dni, String nombre, String apellido, String direccion, Date fecha_nac) {
        super(id_persona, dni, nombre, apellido, direccion, fecha_nac);
        this.profesion = profesion;
        this.listRes = listRes;
    }

    

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List<Reserva> getListRes() {
        return listRes;
    }

    public void setListRes(List<Reserva> listRes) {
        this.listRes = listRes;
    }
    
    
    
}

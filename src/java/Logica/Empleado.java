package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Empleado extends Persona implements Serializable{
    @Basic
    String cargo;
    @OneToOne
    Usuario usu;
    @OneToMany
    List<Reserva> listRes;

    public Empleado() {
    }

    public Empleado(String cargo, Usuario usu, List<Reserva> listRes, long id_persona, String dni, String nombre, String apellido, String direccion, Date fecha_nac) {
        super(id_persona, dni, nombre, apellido, direccion, fecha_nac);
        this.cargo = cargo;
        this.usu = usu;
        this.listRes = listRes;
    }

   

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<Reserva> getListRes() {
        return listRes;
    }

    public void setListRes(List<Reserva> listRes) {
        this.listRes = listRes;
    }
    
    
}

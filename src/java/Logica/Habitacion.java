package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Habitacion implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    long id_habitacion;
    @Basic
    String nombre;
    int piso;
    String tipo;
    int cantPersona;
    double precio;
    @OneToMany
    List<Reserva> listRes;

    public Habitacion() {
    }

    public Habitacion(long id_habitacion, String nombre, int piso, String tipo, int cantPersona, double precio, List<Reserva> listRes) {
        this.id_habitacion = id_habitacion;
        this.nombre = nombre;
        this.piso = piso;
        this.tipo = tipo;
        this.cantPersona = cantPersona;
        this.precio = precio;
        this.listRes = listRes;
    }

    public long getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(long id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantPersona() {
        return cantPersona;
    }

    public void setCantPersona(int cantPersona) {
        this.cantPersona = cantPersona;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Reserva> getListRes() {
        return listRes;
    }

    public void setListRes(List<Reserva> listRes) {
        this.listRes = listRes;
    }
    
}

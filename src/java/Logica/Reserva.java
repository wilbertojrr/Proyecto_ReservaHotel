package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    long id_reserva;
    @Basic
    int cant_dias;
    int cant_personas;
    double costo_estadia;
    @Temporal (TemporalType.DATE)
    Date fecha_reserva;
    @Temporal (TemporalType.DATE)
    Date checkIn;
    @Temporal (TemporalType.DATE)
    Date checkOut;
    @ManyToOne
    Huesped hues;
    @ManyToOne
    Empleado emple;
    @ManyToOne
    Habitacion habi;
    
    public Reserva() {
    }

    public Reserva(long id_reserva, int cant_dias, int cant_personas, double costo_estadia, Date fecha_reserva, Date checkIn, Date checkOut, Huesped hues, Empleado emple, Habitacion habi) {
        this.id_reserva = id_reserva;
        this.cant_dias = cant_dias;
        this.cant_personas = cant_personas;
        this.costo_estadia = costo_estadia;
        this.fecha_reserva = fecha_reserva;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hues = hues;
        this.emple = emple;
        this.habi = habi;
    }

    public long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getCant_dias() {
        return cant_dias;
    }

    public void setCant_dias(int cant_dias) {
        this.cant_dias = cant_dias;
    }

    public int getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(int cant_personas) {
        this.cant_personas = cant_personas;
    }

    public double getCosto_estadia() {
        return costo_estadia;
    }

    public void setCosto_estadia(double costo_estadia) {
        this.costo_estadia = costo_estadia;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Huesped getHues() {
        return hues;
    }

    public void setHues(Huesped hues) {
        this.hues = hues;
    }

    public Empleado getEmple() {
        return emple;
    }

    public void setEmple(Empleado emple) {
        this.emple = emple;
    }

    public Habitacion getHabi() {
        return habi;
    }

    public void setHabi(Habitacion habi) {
        this.habi = habi;
    }

       
}

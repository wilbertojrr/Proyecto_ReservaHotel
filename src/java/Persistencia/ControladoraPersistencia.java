package Persistencia;

import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Reserva;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    EmpleadoJpaController empleJpa = new EmpleadoJpaController();
    HabitacionJpaController habiJpa = new HabitacionJpaController();
    HuespedJpaController huesJpa = new HuespedJpaController();
    PersonaJpaController persoJpa = new PersonaJpaController();
    ReservaJpaController reserJpa = new ReservaJpaController();
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    
    public void altaEmpleado (Empleado emple ) {
        
        
        empleJpa.create(emple);
        
    }
    
    
    public void altaUsuario (Usuario usu ) {
        
        
        try {
            usuJpa.create(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Usuario> traerUsuarios () {
    
        return usuJpa.findUsuarioEntities();
    }
   
    public void altaHabitacion (Habitacion habi){
    
        habiJpa.create(habi);
    } 
    
    public void altaHuesped (Huesped hues){
        
        huesJpa.create(hues);
    }
    
    
    public List<Huesped> traerHuespedes(){
    
        return huesJpa.findHuespedEntities();
    }
    public List<Habitacion> traerHabitacion(){
    
        return habiJpa.findHabitacionEntities();
    }
    public Huesped buscarHuesped(long id){
    
        return huesJpa.findHuesped(id);
    }
     public Habitacion buscarHabitacion(long id){
    
        return habiJpa.findHabitacion(id);
    }
     public Empleado buscarEmpleado(long id){
    
        return empleJpa.findEmpleado(id);
    }
    public Reserva buscarReserva(long id){
    
        return reserJpa.findReserva(id);
    }
    
    
    public List<Empleado> traerEmpleado(){
    
        return empleJpa.findEmpleadoEntities();
    }
    public List<Reserva> traerReserva(){
    
        return reserJpa.findReservaEntities();
    }
    
    public void eliminarHuesped (long id){
        try {
            huesJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarHuesped (Huesped id) {
    
        try {
            huesJpa.edit(id);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void altaReserva (Reserva reser ) {
        
        
        reserJpa.create(reser);
        
    }
}

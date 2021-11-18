 package Logica;

import Persistencia.ControladoraPersistencia;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
       
   
    public void altaEmple(String dni, String nombre, String apellido, Date fecha_nac,String usuario, String clave, String direccion, String cargo)  {
        
        Usuario usu = new Usuario();
        
        usu.setUsuario(usuario);
        usu.setClave(clave);
        
        controlPersis.altaUsuario(usu);

        Empleado emple = new Empleado();
        
        emple.setDni(dni);
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setFecha_nac(fecha_nac);
        emple.setDireccion(direccion);
        emple.setCargo(cargo);
        emple.setUsu(usu);
        
        controlPersis.altaEmpleado(emple);
        
    }
    
    public boolean verificarUsuario (String usuario, String clave) {
        List<Usuario> listaUsuario = controlPersis.traerUsuarios();
        
        if (listaUsuario != null) {
            
            for ( Usuario usu : listaUsuario){
            
                if (usu.getUsuario().equals(usuario) && usu.getClave().equals(clave)) {
                    return true;
                }
            }
       }
       return false;
    } 
    
    public void altaHabitacion (String nombre, double precio, int piso, String tipo, int cantPersona) {
    
        Habitacion habi = new Habitacion();
        
        habi.setNombre(nombre);
        habi.setPrecio(precio);
        habi.setPiso(piso);
        habi.setTipo(tipo);
        habi.setCantPersona(cantPersona);
        
        controlPersis.altaHabitacion(habi);
    }
    
    public void altaHuesped(String dni, String nombre, String apellido, Date fecha_nac,String direccion, String profesion)  {

        Huesped hues = new Huesped();
    
        hues.setDni(dni);
        hues.setNombre(nombre);
        hues.setApellido(apellido);
        hues.setFecha_nac(fecha_nac);
        hues.setDireccion(direccion);
        hues.setProfesion(profesion);
        
        controlPersis.altaHuesped(hues);
    }
    
    public List<Huesped> traerHuespedes (){
    
    return controlPersis.traerHuespedes();
    }
    
    public Huesped buscarHuesped (long id){
    
    return controlPersis.buscarHuesped(id);
    }
    
    public List<Empleado> traerEmpleado (){
    
        return controlPersis.traerEmpleado();
    }
    
    public List<Habitacion> traerHabitacion (){
    
        return controlPersis.traerHabitacion();
    }
    
    public List<Reserva> traerReserva (){
    
        return controlPersis.traerReserva();
    }
    public void eliminarHuesped (Long id){
    
        controlPersis.eliminarHuesped(id);
    }
    
    public void modificarHuesped (Huesped hues){
        
        controlPersis.modificarHuesped(hues);
    }
    
    public void hacerReserva (Reserva reser){
           controlPersis.altaReserva(reser);
    }
    
    public Reserva confirmarPedido (long idHuesped, long idEmpleado, long idHabitacion, int cant_personas, Date checkIn, Date checkOut) {
        
        Reserva reser = new Reserva();
        
        reser.setCant_personas(cant_personas);
        reser.setCheckIn(checkIn);
        reser.setCheckOut(checkOut);
        
        int milisecondsByDay = 86400000;
        int dias = (int) ((checkOut.getTime()-checkIn.getTime()) / milisecondsByDay);
        
        reser.setCant_dias(dias);
        
        Habitacion habi = controlPersis.buscarHabitacion(idHabitacion);
        double precioHabitacion = habi.getPrecio();
        
        double costoEstadia = precioHabitacion*dias;
        
        reser.setCosto_estadia(costoEstadia);
        reser.setFecha_reserva(new Date());
        
        Empleado emple = controlPersis.buscarEmpleado(idEmpleado);
        Huesped hues = controlPersis.buscarHuesped(idHuesped);
        
        reser.setEmple(emple);
        reser.setHues(hues);
        reser.setHabi(habi);
        
        Reserva nueva = reser;
        return nueva;
    
    }
    
    public boolean verificarCantPersonas (long idHabitacion, int cant_personas){
        
        Habitacion habi = controlPersis.buscarHabitacion(idHabitacion);
        
        if (cant_personas > habi.getCantPersona()) {
            return true;
        } else {
            return false;
        }
    
    
    
    }
    
    public boolean verificarDisponible (Date checkIn, Date checkOut, long idHabitacion) {
        Habitacion habi = controlPersis.buscarHabitacion(idHabitacion);
        
        List <Reserva> listaReserva = habi.getListRes();
        
        if (listaReserva != null) {
            
            for ( Reserva reser : listaReserva){
            
                if (checkIn.equals(reser.getCheckIn()) || checkOut.equals(reser.getCheckOut()) ||checkIn.after(reser.getCheckIn()) && checkIn.before(reser.getCheckOut()) || checkOut.after(reser.getCheckIn()) && checkOut.before(reser.getCheckOut())) {
                 return true;
                }
            }
       }
       return false;
    } 
    
    public List<Reserva> reservaPorDia (Date fecha){
    
       List<Reserva> listaReserva = controlPersis.traerReserva();
       
       return listaReserva.stream().filter(x -> x.fecha_reserva.equals(fecha)).collect(Collectors.toList());
    }

}

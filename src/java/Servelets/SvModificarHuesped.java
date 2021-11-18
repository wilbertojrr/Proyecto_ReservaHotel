package Servelets;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wilbe
 */
@WebServlet(name = "SvModificarHuesped", urlPatterns = {"/SvModificarHuesped"})
public class SvModificarHuesped extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        Date fecha_nac = Date.valueOf(request.getParameter("fecha_nac"));
        String direccion = request.getParameter("direccion");
        String profesion = request.getParameter("profesion");
        
        
        Controladora control = new Controladora();
        Huesped hues = control.buscarHuesped(id);
        hues.setDni(dni);
        hues.setNombre(nombre);
        hues.setApellido(apellido);
        hues.setFecha_nac(fecha_nac);
        hues.setDireccion(direccion);
        hues.setProfesion(profesion);
        
        control.modificarHuesped(hues);
        request.getSession().setAttribute("listaHuesped", control.traerHuespedes());
        response.sendRedirect("VerHuesped.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("id"));
        Controladora control = new Controladora();
        Huesped hues = control.buscarHuesped(id);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("huesped", hues);
        response.sendRedirect("ModificarHuesped.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

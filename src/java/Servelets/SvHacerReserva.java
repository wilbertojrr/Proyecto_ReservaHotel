/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelets;

import Logica.Controladora;
import Logica.Reserva;
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
@WebServlet(name = "SvHacerReserva", urlPatterns = {"/SvHacerReserva"})
public class SvHacerReserva extends HttpServlet {

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
        long idHuesped = Long.parseLong(request.getParameter("huesped"));
        long idEmpleado = Long.parseLong(request.getParameter("empleado"));
        long idHabitacion = Long.parseLong(request.getParameter("habita"));
        int cant_personas = Integer.parseInt(request.getParameter("cant_personas"));
        Date checkIn = Date.valueOf(request.getParameter("checkIn"));
        Date checkOut = Date.valueOf(request.getParameter("checkOut"));
        
        
        request.getSession().setAttribute("idHuesped", idHuesped);
        request.getSession().setAttribute("idEmpleado", idEmpleado);
        request.getSession().setAttribute("idHabitacion", idHabitacion);
        request.getSession().setAttribute("cant_personas", cant_personas);
        request.getSession().setAttribute("checkIn", checkIn);
        request.getSession().setAttribute("checkOut", checkOut);
        Controladora control = new Controladora();
        boolean verificarFecha = control.verificarDisponible(checkIn, checkOut, idHabitacion);
        boolean vericarCant = control.verificarCantPersonas(idHabitacion, cant_personas);
        
        if (verificarFecha == false) {
        
            if (vericarCant == false) {
                Reserva nueva = control.confirmarPedido(idHuesped, idEmpleado, idHabitacion, cant_personas, checkIn, checkOut);
                HttpSession misesion = request.getSession();
                misesion.setAttribute("nueva", nueva);
                response.sendRedirect("ConfirmarReserva.jsp");
            } else {
                response.sendRedirect("HacerReserva.jsp");
            }
        }else{
            response.sendRedirect("HacerReserva.jsp");
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

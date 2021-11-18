package Servelets;

import Logica.Controladora;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAltaEmple", urlPatterns = {"/SvAltaEmple"})
public class SvAltaEmple extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        Date fecha_nac = Date.valueOf(request.getParameter("fecha_nac"));
        String direccion = request.getParameter("direccion");
        String cargo = request.getParameter("cargo");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        request.getSession().setAttribute("dni", dni);
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("apellido", apellido);
        request.getSession().setAttribute("fecha_nac", fecha_nac);
        request.getSession().setAttribute("direccion", direccion);
        request.getSession().setAttribute("cargo", cargo);
        request.getSession().setAttribute("usuario", usuario);
        request.getSession().setAttribute("clave", clave);

        Controladora control = new Controladora();
        
        control.altaEmple(dni, nombre, apellido, fecha_nac, usuario, clave, direccion, cargo);
        request.getSession().setAttribute("listaHuesped", control.traerHuespedes());
        response.sendRedirect("Login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

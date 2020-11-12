package web;

import datos.empleado.EmpleadoDAO;
import datos.empleado.EmpleadoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Empleado;
import model.Usuario;

/**
 * @date 10/11/2020
 * @time 23:33:33
 * @author asael
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    
    private final EmpleadoDAO empleadoDAO = EmpleadoDAOImpl.getEmpleadoDAO();
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Empleado> gerentes = empleadoDAO.getListGerentes();
        
        if (gerentes.isEmpty()) {
            response.sendRedirect("gerente/lecturaArchivo.jsp");
        } else {
            
            HttpSession sesion = request.getSession();
            Usuario user = (Usuario) sesion.getAttribute("user");
            if (sesion.getAttribute("user") != null) {
                
                switch (user.getTipoUsuario()) {
                    case 1 -> {
                        response.sendRedirect("gerente/inicioGerente.jsp");
                    }
                    case 2 -> {
                        response.sendRedirect("cajero/inicioCajero.jsp");
                    }
                    case 3 -> {
                        response.sendRedirect("cliente/inicioCliente.jsp");
                    }
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
    
}

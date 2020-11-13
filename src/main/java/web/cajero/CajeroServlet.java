package web.cajero;

import datos.empleado.EmpleadoDAO;
import datos.empleado.EmpleadoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Empleado;

/**
 * @date 12/11/2020
 * @time 22:41:29
 * @author asael
 */
@WebServlet("/cajero")
public class CajeroServlet extends HttpServlet {
    
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
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "listar" -> {
                listar(request);
                response.sendRedirect("gerente/cajeros/listCajeros.jsp");
            }
        }
    }
    
    private void listar(HttpServletRequest request) {
        List<Empleado> cajeros = empleadoDAO.getListCajeros();
        request.getSession().setAttribute("cajeros", cajeros);
    }
}

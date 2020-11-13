package web.turno;

import datos.CRUD;
import datos.turno.TurnoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Turno;

/**
 * @date 13/11/2020
 * @time 09:51:26
 * @author asael
 */
@WebServlet("/turno")
public class TurnoServlet extends HttpServlet {
    
    private final CRUD<Turno> turnoDAO = TurnoDAOImpl.getTurnoDAO();
    
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
                response.sendRedirect("gerente/turnos.jsp");
            }
        }
    }
    
    private void listar(HttpServletRequest request) {
        List<Turno> turnos = turnoDAO.getList();
        request.getSession().setAttribute("turnos", turnos);
    }
}

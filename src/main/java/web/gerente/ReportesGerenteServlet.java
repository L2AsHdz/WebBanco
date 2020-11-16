package web.gerente;

import datos.CRUD;
import datos.cambioRealizado.CambioRealizadoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CambioRealizado;

/**
 * @date 16/11/2020
 * @time 09:10:32
 * @author asael
 */
@WebServlet("/ReportesGerente")
public class ReportesGerenteServlet extends HttpServlet {
    
    private final CRUD<CambioRealizado> cambioDAO = CambioRealizadoDAOImpl.getCambioDAO();

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
            case "reporte1" -> {
                List<CambioRealizado> cambios = cambioDAO.getList();
                request.setAttribute("cambios", cambios);
                request.getRequestDispatcher("gerente/reportes/historialCambios.jsp").forward(request, response);
            }
        }
    }
}

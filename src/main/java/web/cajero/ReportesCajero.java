package web.cajero;

import datos.transaccion.TransaccionDAO;
import datos.transaccion.TransaccionDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Transaccion;
import model.Usuario;

/**
 * @date 16/11/2020
 * @time 16:40:59
 * @author asael
 */
@WebServlet("/ReportesCajero")
public class ReportesCajero extends HttpServlet {
    
    private final TransaccionDAO transaccionDAO = TransaccionDAOImpl.getTransaccionDAO();

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
                Usuario user = (Usuario) request.getSession().getAttribute("user");
                List<Transaccion> retiros = transaccionDAO.getRetirosTurno(user.getCodigo());
                List<Transaccion> depositos = transaccionDAO.getDepositosTurno(user.getCodigo());
                float totalR = transaccionDAO.getTotalRetiroTurno(user.getCodigo());
                float totalD = transaccionDAO.getTotalDepositoTurno(user.getCodigo());
                
                request.setAttribute("retiros", retiros);
                request.setAttribute("depositos", depositos);
                request.setAttribute("totalR", totalR);
                request.setAttribute("totalD", totalD);
                request.getRequestDispatcher("cajero/reporte1.jsp").forward(request, response);
            }
        }
    }
}

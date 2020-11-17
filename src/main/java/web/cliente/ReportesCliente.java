package web.cliente;

import datos.cuenta.CuentaDAO;
import datos.cuenta.CuentaDAOImpl;
import datos.solicitud.SolicitudDAO;
import datos.solicitud.SolicitudDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Solicitud;
import model.Usuario;
import model.cuenta.Cuenta;

/**
 * @date 16/11/2020
 * @time 19:38:17
 * @author asael
 */
@WebServlet("/ReportesCliente")
public class ReportesCliente extends HttpServlet {

    private final CuentaDAO cuentaDAO = CuentaDAOImpl.getCuentaDAO();
    private final SolicitudDAO solicitudDAO = SolicitudDAOImpl.getSolicitudDAO();

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
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String codCliente = String.valueOf(user.getCodigo());

        switch (accion) {
            case "reporte1" -> {
                List<Cuenta> cuentas = cuentaDAO.getCuentasWithTransacciones(user.getCodigo());

                request.setAttribute("cuentas", cuentas);
                request.getRequestDispatcher("cliente/reportes/reporte1.jsp").forward(request, response);
            }
            case "reporte3" -> {
                String fechaInicial = request.getParameter("fechaInicial");
                Cuenta cuenta = cuentaDAO.getCuentaConMasDinero(user.getCodigo(), fechaInicial);

                request.setAttribute("cuenta", cuenta);
                request.setAttribute("fechaInicial", fechaInicial);
                request.getRequestDispatcher("cliente/reportes/reporte3.jsp").forward(request, response);
            }
            case "reporte4" -> {
                List<Solicitud> solicitudes = solicitudDAO.getSolicitudesRecibidas(codCliente);

                request.setAttribute("solicitudes", solicitudes);
                request.setAttribute("codCliente", this);
                request.getRequestDispatcher("cliente/reportes/reporte4.jsp").forward(request, response);
            }
            case "reporte5" -> {
                List<Solicitud> solicitudes = solicitudDAO.getSolicitudesEnviadas(codCliente);
                
                request.setAttribute("solicitudes", solicitudes);
                request.getRequestDispatcher("cliente/reportes/reporte5.jsp").forward(request, response);
            }
        }
    }
}

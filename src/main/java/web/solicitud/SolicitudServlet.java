package web.solicitud;

import datos.CRUD;
import datos.cuenta.CuentaDAOImpl;
import datos.cuentaAsociada.CuentaAsociadaDAO;
import datos.cuentaAsociada.CuentaAsociadaDAOImpl;
import datos.solicitud.SolicitudDAO;
import datos.solicitud.SolicitudDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.Usuario;

/**
 * @date 14/11/2020
 * @time 08:15:15
 * @author asael
 */
@WebServlet("/solicitud")
public class SolicitudServlet extends HttpServlet {

    private final CRUD<Cuenta> cuentaDAO = CuentaDAOImpl.getCuentaDAO();
    private final CuentaAsociadaDAO cuentaAsoDAO = CuentaAsociadaDAOImpl.getCuentaAsoDAO();
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
        
        switch (accion) {
            case "verInfo" -> {
                Usuario user = (Usuario) request.getSession().getAttribute("user");
                String codigoCuenta = request.getParameter("codCuenta");
                
                if (cuentaDAO.exists(codigoCuenta)) {
                    Cuenta cuenta = cuentaDAO.getObject(codigoCuenta);
                    if (cuenta.getCliente().getNoIdentificacion().equals(user.getNoIdentificacion())) {
                        request.setAttribute("error", "No se puede asociar una cuenta propia");
                    } else if (cuentaAsoDAO.isAsociada(user.getCodigo(), codigoCuenta)) {
                        request.setAttribute("error", "La cuenta ya esta asociada");
                    } else if (solicitudDAO.isPendiente(user.getCodigo(), codigoCuenta)) {
                        request.setAttribute("error", "Ya existe una solicitud pendiente de aceptar");
                    } else if (!solicitudDAO.isAvailable(user.getCodigo(), codigoCuenta)) {
                        request.setAttribute("error", "Limite (3) de solicitud de asociacion a una cuenta alcanzado");
                    }  else {
                    request.setAttribute("cuenta", cuenta);
                    }
                } else {
                    request.setAttribute("error", "Cuenta ingresada no existe");
                    request.setAttribute("codigo", codigoCuenta);
                }
                request.getRequestDispatcher("cliente/asociar.jsp").forward(request, response);
            }
        }
    }
}

package web.transaccion;

import datos.CRUD;
import datos.cuenta.CuentaDAO;
import datos.cuenta.CuentaDAOImpl;
import datos.cuentaAsociada.CuentaAsociadaDAO;
import datos.cuentaAsociada.CuentaAsociadaDAOImpl;
import datos.transaccion.TransaccionDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.CuentaAsociada;
import model.Transaccion;
import model.Usuario;

/**
 * @date 15/11/2020
 * @time 08:22:30
 * @author asael
 */
@WebServlet("/transferencia")
public class TransferenciaServlet extends HttpServlet {

    private final CuentaDAO cuentaDAO = CuentaDAOImpl.getCuentaDAO();
    private final CuentaAsociadaDAO cuentaAsoDAO = CuentaAsociadaDAOImpl.getCuentaAsoDAO();
    private final CRUD<Transaccion> transaccionDAO = TransaccionDAOImpl.getTransaccionDAO();
    
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
                Usuario user = (Usuario) request.getSession().getAttribute("user");
                List<Cuenta> cuentasPropias = cuentaDAO.getCuentasPropias(user.getCodigo());
                List<CuentaAsociada> cuentasTerceros = cuentaAsoDAO.getCuentasTerceros(user.getCodigo());
                request.getSession().setAttribute("cuentasPropias", cuentasPropias);
                request.getSession().setAttribute("cuentasTerceros", cuentasTerceros);
                response.sendRedirect("cliente/transferir.jsp");
            }    
        }
    }
}

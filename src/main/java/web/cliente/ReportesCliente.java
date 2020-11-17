package web.cliente;

import datos.cuenta.CuentaDAO;
import datos.cuenta.CuentaDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        switch (accion) {
            case "reporte1" -> {
                List<Cuenta> cuentas = cuentaDAO.getCuentasWithTransacciones(user.getCodigo());

                cuentas.forEach(cuenta -> System.out.println(cuenta.getCodigo()));
                request.setAttribute("cuentas", cuentas);
                request.getRequestDispatcher("cliente/reportes/reporte1.jsp").forward(request, response);
            }
            case "reporte3" -> {
                String fechaInicial = request.getParameter("fechaInicial");
                Cuenta cuenta = cuentaDAO.getCuentaConMasDinero(user.getCodigo(), fechaInicial);
                
                request.setAttribute("cuenta", cuenta);
                request.getRequestDispatcher("cliente/reportes/reporte3.jsp").forward(request, response);

            }
        }
    }
}

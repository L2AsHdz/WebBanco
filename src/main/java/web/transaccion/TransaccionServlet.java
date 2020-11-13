package web.transaccion;

import datos.CRUD;
import datos.cuenta.CuentaDAOImpl;
import datos.transaccion.TransaccionDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.Transaccion;

/**
 * @date 13/11/2020
 * @time 16:13:21
 * @author asael
 */
@WebServlet("/transaccion")
public class TransaccionServlet extends HttpServlet {

    private final CRUD<Transaccion> transaccionDAO = TransaccionDAOImpl.getTransaccionDAO();
    private final CRUD<Cuenta> cuentaDAO = CuentaDAOImpl.getCuentaDAO();

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
            case "infoRetiro" -> {
                String codigoCuenta = request.getParameter("codCuenta");

                if (cuentaDAO.exists(codigoCuenta)) {
                    Cuenta cuenta = cuentaDAO.getObject(codigoCuenta);

                    request.setAttribute("cuenta", cuenta);
                } else {
                    request.setAttribute("error", "Cuenta ingresada no existe");
                    request.setAttribute("codigo", codigoCuenta);
                }
                request.getRequestDispatcher("cajero/retirar.jsp").forward(request, response);
            }
        }
    }
}

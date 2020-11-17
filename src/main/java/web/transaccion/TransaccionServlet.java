package web.transaccion;

import datos.CRUD;
import datos.cuenta.CuentaDAO;
import datos.cuenta.CuentaDAOImpl;
import datos.transaccion.TransaccionDAOImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.cuenta.Cuenta;
import model.Empleado;
import model.Transaccion;
import model.Usuario;

/**
 * @date 13/11/2020
 * @time 16:13:21
 * @author asael
 */
@WebServlet("/transaccion")
public class TransaccionServlet extends HttpServlet {

    private final CRUD<Transaccion> transaccionDAO = TransaccionDAOImpl.getTransaccionDAO();
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
        String codCajero = String.valueOf(user.getCodigo());
        String codCuenta = request.getParameter("codCuenta");

        switch (accion) {
            case "verInfo" -> {
                String codigoCuenta = request.getParameter("codCuenta");
                String tipo = request.getParameter("tipo");

                if (cuentaDAO.exists(codigoCuenta)) {
                    Cuenta cuenta = cuentaDAO.getObject(codigoCuenta);

                    request.setAttribute("cuenta", cuenta);
                } else {
                    request.setAttribute("error", "Cuenta ingresada no existe");
                    request.setAttribute("codigo", codigoCuenta);
                }
                if (tipo.equals("retiro")) {
                    request.getRequestDispatcher("cajero/retirar.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("cajero/depositar.jsp").forward(request, response);
                }
            }
            case "retirar" -> {
                float saldo = Float.parseFloat(request.getParameter("saldo"));
                float monto = Float.parseFloat(request.getParameter("monto"));
                transaccionDAO.create(new Transaccion(new Cuenta(codCuenta), "DEBITO", LocalDate.now(),
                        LocalTime.now(), monto, new Empleado(codCajero), saldo - monto));
                cuentaDAO.updateSaldo(codCuenta, -monto);

                request.setAttribute("success", "El retiro se realizo correctamente");
                request.getRequestDispatcher("cajero/retirar.jsp").forward(request, response);
            }
            case "depositar" -> {
                float saldo = Float.parseFloat(request.getParameter("saldo"));
                float monto = Float.parseFloat(request.getParameter("monto"));
                transaccionDAO.create(new Transaccion(new Cuenta(codCuenta), "CREDITO", LocalDate.now(),
                        LocalTime.now(), monto, new Empleado(codCajero), saldo + monto));
                cuentaDAO.updateSaldo(codCuenta, monto);

                request.setAttribute("success", "El deposito se realizo correctamente");
                request.getRequestDispatcher("cajero/depositar.jsp").forward(request, response);
            }
        }
    }
}

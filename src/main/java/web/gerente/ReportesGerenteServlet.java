package web.gerente;

import datos.CRUD;
import datos.cambioRealizado.CambioRealizadoDAOImpl;
import datos.cliente.ClienteDAO;
import datos.cliente.ClienteDAOImpl;
import datos.empleado.EmpleadoDAO;
import datos.empleado.EmpleadoDAOImpl;
import datos.limite.LimiteDAO;
import datos.limite.LimiteDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CambioRealizado;
import model.Empleado;
import model.cliente.Cliente;

/**
 * @date 16/11/2020
 * @time 09:10:32
 * @author asael
 */
@WebServlet("/ReportesGerente")
public class ReportesGerenteServlet extends HttpServlet {

    private final CRUD<CambioRealizado> cambioDAO = CambioRealizadoDAOImpl.getCambioDAO();
    private final ClienteDAO clienteDAO = ClienteDAOImpl.getClienteDAO();
    private final LimiteDAO limiteDAO = LimiteDAOImpl.getLimiteDAO();
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
            case "reporte1" -> {
                List<CambioRealizado> cambios = cambioDAO.getList();
                request.setAttribute("cambios", cambios);
                request.getRequestDispatcher("gerente/reportes/reporte1.jsp").forward(request, response);
            }
            case "reporte2" -> {
                getClientesR2R3(request, response, "reporte2");
            }
            case "reporte3" -> {
                getClientesR2R3(request, response, "reporte3");
            }
            case "reporte4" -> {
                List<Cliente> clientes = clienteDAO.get10ClientesWithMoreMoney();
                request.setAttribute("clientes", clientes);
                request.getRequestDispatcher("gerente/reportes/reporte4.jsp").forward(request, response);
            }
            case "reporte5" -> {
                List<Cliente> clientes = clienteDAO.getClientesSinTransacciones();
                request.setAttribute("clientes", clientes);
                request.getRequestDispatcher("gerente/reportes/reporte5.jsp").forward(request, response);
            }
            case "reporte7" -> {
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                Empleado cajero;
                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    cajero = empleadoDAO.getCajeroConMasTransacciones(fechaInicial, fechaFinal, 1);
                } else {
                    cajero = empleadoDAO.getCajeroConMasTransacciones(fechaInicial, fechaFinal, 2);
                }

                if (cajero != null) {
                    request.setAttribute("fechaInicial", fechaInicial);
                    request.setAttribute("fechaFinal", fechaFinal);
                    request.setAttribute("cajero", cajero);
                } else {
                    request.setAttribute("noData", true);
                }
                request.getRequestDispatcher("gerente/reportes/reporte7.jsp").forward(request, response);
            }
        }
    }

    private void getClientesR2R3(HttpServletRequest request, HttpServletResponse response, String caso) throws ServletException, IOException {
        boolean wasSetted = limiteDAO.wasSetted();

        if (wasSetted) {
            List<Cliente> clientes;
            Float limite;
            if (caso.equals("reporte2")) {
                clientes = clienteDAO.getClientesWithTrGreaterThanLimite();
                limite = limiteDAO.getObject("1").getValor();
            } else {
                clientes = clienteDAO.getClientesWithTrSumGreaterThanLimite();
                limite = limiteDAO.getObject("2").getValor();
            }
            request.setAttribute("clientes", clientes);
            request.setAttribute("limite", limite);
        } else {
            request.setAttribute("wasSetted", true);
        }
        String nameR = caso.equals("reporte2") ? "reporte2" : "reporte3";
        request.getRequestDispatcher("gerente/reportes/" + nameR + ".jsp").forward(request, response);
    }
}

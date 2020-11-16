package web.gerente;

import datos.CRUD;
import datos.cambioRealizado.CambioRealizadoDAOImpl;
import datos.cliente.ClienteDAO;
import datos.cliente.ClienteDAOImpl;
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
import model.Cliente;

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
            request.setAttribute("wasSetted", false);
        }
        String nameR = caso.equals("reporte2") ? "reporte2" : "reporte3";
        request.getRequestDispatcher("gerente/reportes/" + nameR+".jsp").forward(request, response);
    }
}

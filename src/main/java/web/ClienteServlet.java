package web;

import datos.CRUD;
import datos.cliente.ClienteDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 * @date 12/11/2020
 * @time 10:17:53
 * @author asael
 */
@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
    
    private final CRUD<Cliente> clienteDAO = ClienteDAOImpl.getClienteDAO();

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
                List<Cliente> clientes = clienteDAO.getList();
                request.getSession().setAttribute("clientes", clientes);
                response.sendRedirect("gerente/clientes/listClientes.jsp");
            }
        }
    }
    
    
}

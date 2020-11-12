package web;

import datos.CRUD;
import datos.cliente.ClienteDAOImpl;
import datos.usuario.UsuarioDAO;
import datos.usuario.UsuarioDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Cliente;
import model.Usuario;

/**
 * @date 12/11/2020
 * @time 10:17:53
 * @author asael
 */
@WebServlet("/cliente")
@MultipartConfig
public class ClienteServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
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
                listar(request, response);
                response.sendRedirect("gerente/clientes/listClientes.jsp");
            }
            case "agregar" -> {
                String nombre = request.getParameter("nombre");
                String direccion = request.getParameter("direccion");
                String birth = request.getParameter("birth");
                String noIdentificacion = request.getParameter("noIdentificacion");
                Part dpiPDF = request.getPart("dpiPDF");
                String sexo = request.getParameter("sexo");
                String password = request.getParameter("password");

                String codigo = usuarioDAO.crear(new Usuario(nombre, direccion, noIdentificacion, sexo, 3, password));
                clienteDAO.create(new Cliente(codigo, birth, dpiPDF.getInputStream()));

                request.setAttribute("success", "El cliente se ingreso correctamente");
                listar(request, response);
                request.getRequestDispatcher("gerente/clientes/listClientes.jsp").forward(request, response);
            }
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Cliente> clientes = clienteDAO.getList();
        request.getSession().setAttribute("clientes", clientes);
    }

}

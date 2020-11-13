package web.cliente;

import datos.CRUD;
import datos.cliente.ClienteDAOImpl;
import datos.usuario.UsuarioDAO;
import datos.usuario.UsuarioDAOImpl;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String birth = request.getParameter("birth");
        String noIdentificacion = request.getParameter("noIdentificacion");
        String sexo = request.getParameter("sexo");
        String password = request.getParameter("password");
        InputStream dpiPDF;
        try {
            dpiPDF = request.getPart("dpiPDF").getInputStream();
        } catch (IOException | ServletException e) {
            dpiPDF = null;
        }

        switch (accion) {
            case "listar" -> {
                listar(request);
                response.sendRedirect("gerente/clientes/listClientes.jsp");
            }
            case "agregar" -> {
                String codigo = usuarioDAO.crear(new Usuario(nombre, direccion, noIdentificacion, sexo, 3, password));
                clienteDAO.create(new Cliente(codigo, birth, dpiPDF));

                request.setAttribute("success", "El cliente se ingreso correctamente");
                listar(request);
                request.getRequestDispatcher("gerente/clientes/listClientes.jsp").forward(request, response);
            }
            case "editar" -> {
                String codigo = request.getParameter("codigo");
                Cliente cliente = clienteDAO.getObject(codigo);
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("gerente/clientes/listClientes.jsp").forward(request, response);
            }
            case "update" -> {
                String codigo = request.getParameter("codigo");
                usuarioDAO.update(new Usuario(codigo, nombre, direccion, noIdentificacion, sexo));
                clienteDAO.update(new Cliente(codigo, birth, dpiPDF));
                
                request.setAttribute("success", "Los datos del cliente se modificaron");
                listar(request);
                request.getRequestDispatcher("gerente/clientes/listClientes.jsp").forward(request, response);
            }
        }
    }

    private void listar(HttpServletRequest request) {
        List<Cliente> clientes = clienteDAO.getList();
        request.getSession().setAttribute("clientes", clientes);
    }

}

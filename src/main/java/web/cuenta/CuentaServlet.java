package web.cuenta;

import datos.CRUD;
import datos.cliente.ClienteDAOImpl;
import datos.cuenta.CuentaDAO;
import datos.cuenta.CuentaDAOImpl;
import datos.usuario.UsuarioDAO;
import datos.usuario.UsuarioDAOImpl;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Cuenta;
import model.Usuario;

/**
 * @date 12/11/2020
 * @time 18:07:10
 * @author asael
 */
@WebServlet("/cuenta")
@MultipartConfig
public class CuentaServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
    private final CRUD<Cliente> clienteDAO = ClienteDAOImpl.getClienteDAO();
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

        switch (accion) {
            case "verificarUser" -> {
                String noIdentificacion = request.getParameter("noIdentificacion");
                    Usuario user = usuarioDAO.getUsuario(noIdentificacion);
                if (user != null) {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(user.getCodigo());
                    cliente.setNombre(user.getNombre());
                    Cuenta cuenta = new Cuenta(cliente, LocalDate.now(), 0);
                    String codigoCuenta = cuentaDAO.crear(cuenta);
                    cuenta.setCodigo(Integer.parseInt(codigoCuenta));
                    
                    request.setAttribute("success", true);
                    request.setAttribute("cuenta", cuenta);
                    request.getRequestDispatcher("gerente/cuentas/crearCuenta.jsp").forward(request, response);
                } else {
                    request.setAttribute("crearCliente", true);
                    request.getRequestDispatcher("gerente/cuentas/crearCuenta.jsp").forward(request, response);
                }
            }
            case "agregar" -> {
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

                
                String codigo = usuarioDAO.crear(new Usuario(nombre, direccion, noIdentificacion, sexo, 3, password));
                Cliente cliente = new Cliente(codigo, birth, dpiPDF);
                cliente.setNombre(nombre);
                clienteDAO.create(cliente);
                Cuenta cuenta = new Cuenta(cliente, LocalDate.now(), 0);
                String codigoCuenta = cuentaDAO.crear(cuenta);
                cuenta.setCodigo(Integer.parseInt(codigoCuenta));

                request.setAttribute("success", true);
                request.setAttribute("cuenta", cuenta);
                request.getRequestDispatcher("gerente/cuentas/crearCuenta.jsp").forward(request, response);

            }
        }
    }

}

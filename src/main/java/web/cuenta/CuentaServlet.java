package web.cuenta;

import datos.CRUD;
import datos.cuenta.CuentaDAOImpl;
import datos.usuario.UsuarioDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.Usuario;

/**
 * @date 12/11/2020
 * @time 18:07:10
 * @author asael
 */
@WebServlet("/cuenta")
public class CuentaServlet extends HttpServlet {
    
    private final CRUD<Usuario> usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
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
            case "verificarUser" -> {
                String noIdentificacion = request.getParameter("noIdentificacion");
                if (usuarioDAO.exists(noIdentificacion)) {
                    
                } else {
                    request.setAttribute("crearCliente", true);
                    request.getRequestDispatcher("gerente/cuentas/crearCuenta.jsp").forward(request, response);
                }
            }
        }
    }

    
}

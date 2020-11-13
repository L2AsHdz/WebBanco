package web.gerente;

import datos.empleado.EmpleadoDAO;
import datos.empleado.EmpleadoDAOImpl;
import datos.usuario.UsuarioDAO;
import datos.usuario.UsuarioDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Empleado;

/**
 * @date 13/11/2020
 * @time 00:26:19
 * @author asael
 */
@WebServlet("/gerente")
public class GerenteServlet extends HttpServlet {
    
    private final UsuarioDAO usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
    private final EmpleadoDAO empleadoDAO = EmpleadoDAOImpl.getEmpleadoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String turno = request.getParameter("turno");
        String noIdentificacion = request.getParameter("noIdentificacion");
        String sexo = request.getParameter("sexo");
        String password = request.getParameter("password");
        
        switch (accion) {
            case "listar" -> {
                listar(request);
                response.sendRedirect("gerente/gerentes/listGerentes.jsp");
            }
        }
    }

    private void listar(HttpServletRequest request) {
        List<Empleado> gerentes = empleadoDAO.getListGerentes();
        request.getSession().setAttribute("gerentes", gerentes);
    }
}

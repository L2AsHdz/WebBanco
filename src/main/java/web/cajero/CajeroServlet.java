package web.cajero;

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
import model.Turno;
import model.Usuario;

/**
 * @date 12/11/2020
 * @time 22:41:29
 * @author asael
 */
@WebServlet("/cajero")
public class CajeroServlet extends HttpServlet {
    
    private final UsuarioDAO usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
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
        
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String turno = request.getParameter("turno");
        String noIdentificacion = request.getParameter("noIdentificacion");
        String sexo = request.getParameter("sexo");
        String password = request.getParameter("password");
        
        switch (accion) {
            case "listar" -> {
                listar(request);
                response.sendRedirect("gerente/cajeros/listCajeros.jsp");
            }
            case "agregar" -> {
                String codigo = usuarioDAO.crear(new Usuario(nombre, direccion, noIdentificacion, sexo, 2, password));
                empleadoDAO.create(new Empleado(new Turno(turno), codigo));
                
                request.setAttribute("success", "El cajero se ingreso correctamente");
                listar(request);
                request.getRequestDispatcher("gerente/cajeros/listCajeros.jsp").forward(request, response);
            }
            case "editar" -> {
                String codigo = request.getParameter("codigo");
                Empleado cajero = empleadoDAO.getObject(codigo);
                request.setAttribute("cajero", cajero);
                request.getRequestDispatcher("gerente/cajeros/listCajeros.jsp").forward(request, response);
            }
            case "update" -> {
                String codigo = request.getParameter("codigo");
                usuarioDAO.update(new Usuario(codigo, nombre, direccion, noIdentificacion, sexo));
                empleadoDAO.update(new Empleado(new Turno(turno), codigo));
                
                request.setAttribute("success", "Los datos del cajero se modificaron");
                listar(request);
                request.getRequestDispatcher("gerente/cajeros/listCajeros.jsp").forward(request, response);
            }
        }
    }
    
    private void listar(HttpServletRequest request) {
        List<Empleado> cajeros = empleadoDAO.getListCajeros();
        request.getSession().setAttribute("cajeros", cajeros);
    }
}

package web.gerente;

import datos.CRUD;
import datos.cambioRealizado.CambioRealizadoDAOImpl;
import datos.empleado.EmpleadoDAO;
import datos.empleado.EmpleadoDAOImpl;
import datos.usuario.UsuarioDAO;
import datos.usuario.UsuarioDAOImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CambioRealizado;
import model.Empleado;
import model.Turno;
import model.Usuario;

/**
 * @date 13/11/2020
 * @time 00:26:19
 * @author asael
 */
@WebServlet("/gerente")
public class GerenteServlet extends HttpServlet {
    
    private final UsuarioDAO usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
    private final EmpleadoDAO empleadoDAO = EmpleadoDAOImpl.getEmpleadoDAO();
    private final CRUD<CambioRealizado> cambioDAO = CambioRealizadoDAOImpl.getCambioDAO();

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
                response.sendRedirect("gerente/gerentes/listGerentes.jsp");
            }
            case "agregar" -> {
                String codigo = usuarioDAO.crear(new Usuario(nombre, direccion, noIdentificacion, sexo, 1, password));
                empleadoDAO.create(new Empleado(new Turno(turno), codigo));
                
                request.setAttribute("success", "El gerente se ingreso correctamente");
                listar(request);
                request.getRequestDispatcher("gerente/gerentes/listGerentes.jsp").forward(request, response);
            }
            case "editar" -> {
                String codigo = request.getParameter("codigo");
                Empleado gerente = empleadoDAO.getObject(codigo);
                request.setAttribute("gerente", gerente);
                request.getRequestDispatcher("gerente/gerentes/listGerentes.jsp").forward(request, response);
            }
            case "update" -> {
                String codigo = request.getParameter("codigo");
                Usuario user = new Usuario(codigo, nombre, direccion, noIdentificacion, sexo);
                usuarioDAO.update(user);
                request.getSession().setAttribute("user", user);
                empleadoDAO.update(new Empleado(new Turno(turno), codigo));
                crearRegistroCambio(request, codigo);
                
                request.setAttribute("success", "Los datos del gerente se modificaron");
                listar(request);
                request.getRequestDispatcher("gerente/gerentes/listGerentes.jsp").forward(request, response);
            }
        }
    }

    private void listar(HttpServletRequest request) {
        List<Empleado> gerentes = empleadoDAO.getListGerentes();
        request.getSession().setAttribute("gerentes", gerentes);
    }
    
    private void crearRegistroCambio(HttpServletRequest request, String codigo) {
        Usuario gerente = (Usuario) request.getSession().getAttribute("user");
        String codGerente = String.valueOf(gerente.getCodigo());
        cambioDAO.create(new CambioRealizado(new Empleado(codGerente), new Usuario(codigo), LocalDate.now(), LocalTime.now()));
    }
}

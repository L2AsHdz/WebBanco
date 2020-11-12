package web;

import datos.CRUD;
import datos.usuario.UsuarioDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @date 12/11/2020
 * @time 07:27:48
 * @author asael
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final CRUD<Usuario> usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String password = request.getParameter("password");

        verificarCredenciales(codigo, DigestUtils.sha1Hex(password), request, response);
    }

    private void verificarCredenciales(String codigo, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (usuarioDAO.exists(codigo)) {
            Usuario user = usuarioDAO.getObject(codigo);

            if (user.getPassword().equals(password)) {
                HttpSession sesion = request.getSession();
                sesion.setMaxInactiveInterval(3600);
                sesion.setAttribute("user", user);

                switch (user.getTipoUsuario()) {
                    case 1 -> response.sendRedirect("gerente/inicioGerente.jsp");
                    case 2 -> response.sendRedirect("cajero/inicioCajero.jsp");
                    case 3 -> response.sendRedirect("cliente/clienteInicio.jsp");
                }
            } else {
                request.setAttribute("errorLogin", "Contraseña incorrecta");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("errorLogin", "No existe un usuario con el codigo ingresado");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

}

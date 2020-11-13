package web.limite;

import datos.CRUD;
import datos.limite.LimiteDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Limite;

/**
 * @date 13/11/2020
 * @time 11:50:01
 * @author asael
 */
    @WebServlet("/limite")
public class LimiteServlet extends HttpServlet {
    
    private final CRUD<Limite> limiteDAO = LimiteDAOImpl.getLimiteDAO();
    
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
                listar(request);
                response.sendRedirect("gerente/limites.jsp");
            }
            case "update" -> {
                String valor1 = request.getParameter("valor1");
                String valor2 = request.getParameter("valor2");
                
                limiteDAO.update(new Limite(1, valor1));
                limiteDAO.update(new Limite(2, valor2));
                
                request.setAttribute("success", "El valor del los limites se actualizo");
                listar(request);
                request.getRequestDispatcher("gerente/limites.jsp").forward(request, response);
            }
        }
    }
    
    private void listar(HttpServletRequest request) {
        List<Limite> limites = limiteDAO.getList();
        request.getSession().setAttribute("limites", limites);
    }
}

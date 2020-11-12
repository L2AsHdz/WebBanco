package web.lecturaArchivo;

import datos.CRUD;
import datos.empleado.EmpleadoDAOImpl;
import datos.usuario.UsuarioDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;
import model.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import others.exceptions.FileInputException;
import static others.validators.EmpleadoValidator.validarEmpleado;
import static others.validators.UsuarioValidator.validarUsuario;

/**
 * @date 11/11/2020
 * @time 17:53:44
 * @author asael
 */
public class LecturaCajero {

    private static final CRUD<Usuario> usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
    private static final CRUD<Empleado> empleadoDAO = EmpleadoDAOImpl.getEmpleadoDAO();
    
    /**
     * Lee la etiqueta CAJERO del archivo de entrada, si no hay errores guarda
     * los datos en la base de datos
     * @param doc
     * @return List Errores
     */
    public static List<String> leerCajero(Document doc) {
        
        List<String> errores = new ArrayList<>();
        NodeList gerentes = doc.getElementsByTagName("CAJERO");
        
        for (int i = 0; i < gerentes.getLength(); i++) {
            Node gerenteNode = gerentes.item(i);
            
            if (gerenteNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gerente = (Element) gerenteNode;
                String codigo = getTextNode(gerente, "CODIGO");
                String nombre = getTextNode(gerente, "NOMBRE");
                String turno = getTextNode(gerente, "TURNO");
                String dpi = getTextNode(gerente, "DPI");
                String direccion = getTextNode(gerente, "DIRECCION");
                String sexo = getTextNode(gerente, "SEXO");
                String password = getTextNode(gerente, "PASSWORD");
                
                try {
                    Usuario user = validarUsuario(codigo, nombre, dpi, direccion, sexo, password, "CAJERO", i);
                    Empleado empleado = validarEmpleado(codigo, turno, "CAJERO", i);
                    usuarioDAO.create(user);
                    empleadoDAO.create(empleado);
                } catch (FileInputException e) {
                    errores.add(e.getMessage());
                }
            }
        }
        return errores;
    }
    
    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}

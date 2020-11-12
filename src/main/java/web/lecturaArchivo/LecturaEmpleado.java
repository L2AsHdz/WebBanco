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
import static others.validators.UsuarioValidator.validarUsuario;
import static others.validators.EmpleadoValidator.validarEmpleado;

/**
 * @date 11/11/2020
 * @time 09:35:40
 * @author asael
 */
public class LecturaEmpleado {

    private static final CRUD<Usuario> usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
    private static final CRUD<Empleado> empleadoDAO = EmpleadoDAOImpl.getEmpleadoDAO();
    
    /**
     * Lee la etiqueta GERENTE del archivo de entrada, si no hay errores guarda
     * los datos en la base de datos
     * @param doc
     * @param tipoEmpleado
     * @return List Errores
     */
    public static List<String> leerEmpleado(Document doc, String tipoEmpleado) {
        
        List<String> errores = new ArrayList<>();
        NodeList empleados = doc.getElementsByTagName(tipoEmpleado);
        
        for (int i = 0; i < empleados.getLength(); i++) {
            Node empleadoNode = empleados.item(i);
            
            if (empleadoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element empleadoE = (Element) empleadoNode;
                String codigo = getTextNode(empleadoE, "CODIGO");
                String nombre = getTextNode(empleadoE, "NOMBRE");
                String turno = getTextNode(empleadoE, "TURNO");
                String dpi = getTextNode(empleadoE, "DPI");
                String direccion = getTextNode(empleadoE, "DIRECCION");
                String sexo = getTextNode(empleadoE, "SEXO");
                String password = getTextNode(empleadoE, "PASSWORD");
                
                try {
                    Usuario user = validarUsuario(codigo, nombre, dpi, direccion, sexo, password, tipoEmpleado, i);
                    Empleado empleado = validarEmpleado(codigo, turno, tipoEmpleado, i);
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

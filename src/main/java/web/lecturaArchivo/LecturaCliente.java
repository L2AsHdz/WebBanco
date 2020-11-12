package web.lecturaArchivo;

import datos.CRUD;
import datos.cliente.ClienteDAOImpl;
import datos.usuario.UsuarioDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import others.exceptions.FileInputException;
import static others.validators.ClienteValidator.validarCliente;
import static others.validators.UsuarioValidator.validarUsuario;

/**
 * @date 11/11/2020
 * @time 18:09:55
 * @author asael
 */
public class LecturaCliente {
    
    private static final CRUD<Usuario> usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();
    private static final CRUD<Cliente> clienteDAO = ClienteDAOImpl.getClienteDAO();

    /**
     * Lee la etiqueta CAJERO del archivo de entrada, si no hay errores guarda
     * los datos en la base de datos
     * @param doc
     * @return List Errores
     */
    public static List<String> leerCliente(Document doc) {
        
        List<String> errores = new ArrayList<>();
        NodeList clientes = doc.getElementsByTagName("CLIENTE");
        
        for (int i = 0; i < clientes.getLength(); i++) {
            Node clienteNode = clientes.item(i);
            
            if (clienteNode.getNodeType() == Node.ELEMENT_NODE) {
                Element clienteE = (Element) clienteNode;
                String codigo = getTextNode(clienteE, "CODIGO");
                String nombre = getTextNode(clienteE, "NOMBRE");
                String birth = getTextNode(clienteE, "BIRTH");
                String dpi = getTextNode(clienteE, "DPI");
                String direccion = getTextNode(clienteE, "DIRECCION");
                String sexo = getTextNode(clienteE, "SEXO");
                String dpiFileName = getTextNode(clienteE, "DPI-PDF");
                String password = getTextNode(clienteE, "PASSWORD");
                
                try {
                    Usuario user = validarUsuario(codigo, nombre, dpi, direccion, sexo, password, "CLIENTE", i);
                    Cliente cliente = validarCliente(codigo, birth, dpiFileName, "CLIENTE", i);
                    usuarioDAO.create(user);
                    clienteDAO.create(cliente);

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

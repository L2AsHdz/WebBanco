package web.lecturaArchivo;

import datos.transaccion.TransaccionDAO;
import datos.transaccion.TransaccionDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.Transaccion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import others.exceptions.FileInputException;
import static others.validators.TransaccionValidator.validarTransaccion;

/**
 * @date 11/11/2020
 * @time 23:24:01
 * @author asael
 */
public class LecturaTransaccion {

    private static final TransaccionDAO transaccionDAO = TransaccionDAOImpl.getTransaccionDAO();

    public static List<String> leerTransaccion(Document doc) {

        List<String> errores = new ArrayList<>();
        NodeList transacciones = doc.getElementsByTagName("TRANSACCION");

        for (int i = 0; i < transacciones.getLength(); i++) {
            Node transaccionNode = transacciones.item(i);

            if (transaccionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element transaccionE = (Element) transaccionNode;
                String codigo = getTextNode(transaccionE, "CODIGO");
                String cuenta = getTextNode(transaccionE, "CUENTA-ID");
                String fecha = getTextNode(transaccionE, "FECHA");
                String hora = getTextNode(transaccionE, "HORA");
                String tipo = getTextNode(transaccionE, "TIPO");
                String monto = getTextNode(transaccionE, "MONTO");
                String cajero = getTextNode(transaccionE, "CAJERO-ID");
                
                try {
                    Transaccion transaccion = validarTransaccion(codigo, cuenta, fecha, hora, tipo, monto, cajero, i);
                    transaccionDAO.create(codigo, transaccion);
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

package datos.cliente;

import datos.CRUD;
import java.util.List;
import model.Cliente;

/**
 * @date 11/11/2020
 * @time 11:30:54
 * @author asael
 */
public interface ClienteDAO extends CRUD<Cliente> {
    
    byte[] getPDFdpi(String codigo);
    List<Cliente> getClientesWithTrGreaterThanLimite();
}

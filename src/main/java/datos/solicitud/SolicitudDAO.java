package datos.solicitud;

import datos.CRUD;
import java.util.List;
import model.Solicitud;

/**
 * @date 14/11/2020
 * @time 15:24:33
 * @author asael
 */
public interface SolicitudDAO extends CRUD<Solicitud> {

    boolean isPendiente(int codCliente, String codCuenta);
    boolean isAvailable(int codCliente, String codCuenta);
    List<Solicitud> getSolicitudesPendientes(String codCliente);
    void updateEstado(String id, int estado);
}

package datos.empleado;

import datos.CRUD;
import java.util.List;
import model.Empleado;

/**
 * @date 11/11/2020
 * @time 09:55:21
 * @author asael
 */
public interface EmpleadoDAO extends CRUD<Empleado> {

    List<Empleado> getListGerentes();
    List<Empleado> getListCajeros();
}

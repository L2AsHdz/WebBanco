package datos.cuentaAsociada;

import datos.CRUD;
import model.CuentaAsociada;

/**
 * @date 14/11/2020
 * @time 08:41:28
 * @author asael
 */
public interface CuentaAsociadaDAO extends CRUD<CuentaAsociada> {

    boolean isAsociada(int codCliente, String codCuenta);
}

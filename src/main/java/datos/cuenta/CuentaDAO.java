package datos.cuenta;

import datos.CRUD;
import model.Cuenta;

/**
 * @date 11/11/2020
 * @time 11:37:46
 * @author asael
 */
public interface CuentaDAO extends CRUD<Cuenta> {

    String crear(Cuenta c);
}

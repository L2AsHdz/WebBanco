package datos.cuenta;

import datos.CRUD;
import java.util.List;
import model.cuenta.Cuenta;

/**
 * @date 11/11/2020
 * @time 11:37:46
 * @author asael
 */
public interface CuentaDAO extends CRUD<Cuenta> {

    String crear(Cuenta c);
    void updateSaldo(String codigo, float monto);
    List<Cuenta> getCuentasPropias(int codCliente);
    List<Cuenta> getCuentasWithTransacciones(int codCliente);
    Cuenta getCuentaConMasDinero(int codCliente, String fechaInicial);
}

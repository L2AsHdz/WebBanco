package datos.transaccion;

import datos.CRUD;
import java.util.List;
import model.Transaccion;

/**
 * @date 11/11/2020
 * @time 11:47:28
 * @author asael
 */
public interface TransaccionDAO extends CRUD<Transaccion> {

    void create(String codigo, Transaccion t);
    List<Transaccion> getRetirosTurno(int codCajero);
    List<Transaccion> getDepositosTurno(int codCajero);
    float getTotalRetiroTurno(int codCajero);
    float getTotalDepositoTurno(int codCajero);
}

package model.cuenta;

import java.util.List;
import model.Transaccion;

/**
 * @date 16/11/2020
 * @time 19:00:14
 * @author asael
 */
public class CuentaDTO extends Cuenta {
    
    private List<Transaccion> transacciones;

    public CuentaDTO(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public CuentaDTO(List<Transaccion> transacciones, String codigo) {
        super(codigo);
        this.transacciones = transacciones;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
}

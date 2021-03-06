package model;

import model.cuenta.Cuenta;
import model.cliente.Cliente;
import java.io.Serializable;

/**
 * @date 10/11/2020
 * @time 21:39:26
 * @author asael
 */
public class CuentaAsociada implements Serializable {

    private Cliente cliente;
    private Cuenta cuenta;

    public CuentaAsociada() {
    }

    public CuentaAsociada(Cliente cliente, Cuenta cuenta) {
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}

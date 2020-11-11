package model;

/**
 * @date 10/11/2020
 * @time 21:40:24
 * @author asael
 */
public class Solicitud {

    private int id;
    private Cliente cliente;
    private Cuenta cuenta;
    private int estadoN;
    private String estado;

    public Solicitud() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getEstadoN() {
        return estadoN;
    }

    public void setEstadoN(int estadoN) {
        this.estadoN = estadoN;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

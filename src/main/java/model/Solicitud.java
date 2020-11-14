package model;

import java.io.Serializable;

/**
 * @date 10/11/2020
 * @time 21:40:24
 * @author asael
 */
public class Solicitud implements Serializable {

    private int id;
    private Cliente cliente;
    private Cuenta cuenta;
    private int estadoN;
    private String estado;

    public Solicitud() {
    }

    public Solicitud(Cliente cliente, Cuenta cuenta) {
        this.cliente = cliente;
        this.cuenta = cuenta;
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
        switch (estadoN) {
            case 0 -> setEstado("Rechazada");
            case 1 -> setEstado("Aceptada");
            case 2 -> setEstado("Pendiente");
        }
    }

    public String getEstado() {
        return estado;
    }

    private void setEstado(String estado) {
        this.estado = estado;
    }
}

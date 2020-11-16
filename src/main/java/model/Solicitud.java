package model;

import model.cliente.Cliente;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate fecha;

    public Solicitud() {
    }

    public Solicitud(Cliente cliente, Cuenta cuenta, LocalDate fecha) {
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.fecha = fecha;
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
            case 0 -> setEstado("Pendiente");
            case 1 -> setEstado("Aceptada");
            case 2 -> setEstado("Rechazada");
        }
    }

    public String getEstado() {
        return estado;
    }

    private void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

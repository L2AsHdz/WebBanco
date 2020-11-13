package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @date 10/11/2020
 * @time 22:42:32
 * @author asael
 */
public class CambioRealizado implements Serializable {

    private int id;
    private Empleado gerente;
    private Usuario usuarioModificado;
    private LocalDate fecha;
    private LocalTime hora;

    public CambioRealizado() {
    }

    public CambioRealizado(Empleado gerente, Usuario usuarioModificado, LocalDate fecha, LocalTime hora) {
        this.gerente = gerente;
        this.usuarioModificado = usuarioModificado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getGerente() {
        return gerente;
    }

    public void setGerente(Empleado gerente) {
        this.gerente = gerente;
    }

    public Usuario getUsuarioModificado() {
        return usuarioModificado;
    }

    public void setUsuarioModificado(Usuario usuarioModificado) {
        this.usuarioModificado = usuarioModificado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}

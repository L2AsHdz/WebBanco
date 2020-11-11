package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @date 10/11/2020
 * @time 22:42:32
 * @author asael
 */
public class CambioRealizado {

    private int id;
    private Empleado gerente;
    private Usuario usuarioModificado;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;

    public CambioRealizado() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

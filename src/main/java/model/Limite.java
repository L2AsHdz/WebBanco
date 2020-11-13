package model;

import java.io.Serializable;

/**
 * @date 10/11/2020
 * @time 22:44:15
 * @author asael
 */
public class Limite implements Serializable {

    private int id;
    private String nombre;
    private float valor;

    public Limite() {
    }

    public Limite(int id, String valor) {
        this.id = id;
        this.valor = Float.parseFloat(valor);
    }

    public Limite(int id, String nombre, float valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}

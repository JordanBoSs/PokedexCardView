package com.jordan.pokedexcardview.modelos;

public class Pokemon {

    private String nombre, descripcion;
    private int imgnFondo, imgnIcon, cantidad;

    final public int MAXIMO = 10;
    final public int RESET = 0;

    public Pokemon(){}

    public Pokemon(String nombre, String descripcion, int imgnFondo, int imgnIcon, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgnFondo = imgnFondo;
        this.imgnIcon = imgnIcon;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgnFondo() {
        return imgnFondo;
    }

    public void setImgnFondo(int imgnFondo) {
        this.imgnFondo = imgnFondo;
    }

    public int getImgnIcon() {
        return imgnIcon;
    }

    public void setImgnIcon(int imgnIcon) {
        this.imgnIcon = imgnIcon;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMAXIMO() {
        return MAXIMO;
    }

    public int getRESET() {
        return RESET;
    }

    public void reiniciarCantidad() {
        this.cantidad = RESET;
    }

    // Añadir cantidad
    public void addCantidad(int cantidad) {
        if (this.cantidad < MAXIMO)
            this.cantidad += cantidad;
    }
}

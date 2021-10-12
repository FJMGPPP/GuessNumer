package com.fjmg.guessnumer.data;

import java.io.Serializable;

public class Partida implements Serializable
{
    private String Usuario;
    private int NumeroIntentos;
    private int NumeroGenerado;

    public Partida(String usuario, int numeroIntentos, int numeroGenerado) {
        Usuario = usuario;
        NumeroIntentos = numeroIntentos;
        NumeroGenerado = numeroGenerado;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getNumeroIntentos() {
        return NumeroIntentos;
    }

    public void setNumeroIntentos(int numeroIntentos) {
        NumeroIntentos = numeroIntentos;
    }

    public int getNumeroGenerado() {
        return NumeroGenerado;
    }

    public void setNumeroGenerado(int numeroGenerado) {
        NumeroGenerado = numeroGenerado;
    }
}

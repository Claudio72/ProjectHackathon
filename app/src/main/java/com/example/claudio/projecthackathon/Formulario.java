package com.example.claudio.projecthackathon;

import java.util.ArrayList;

/**
 * Created by Claudio on 04/03/2017.
 */

public class Formulario {
    private String nombre;
    private ArrayList documentosNecesarios;
    private int img;

    Formulario(String nombre, ArrayList documentosNecesarios, int img){
        this.nombre=nombre;
        this.documentosNecesarios=documentosNecesarios;
        this.img=img;
    }

    public Formulario(ArrayList documentosNecesarios, String nombre) {
        this.documentosNecesarios = documentosNecesarios;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList getDocumentosNecesarios() {
        return documentosNecesarios;
    }

    public void setDocumentosNecesarios(ArrayList documentosNecesarios) {
        this.documentosNecesarios = documentosNecesarios;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

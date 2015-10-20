package com.example.dm2.tabs_appbar;

/**
 * Created by dm2 on 16/10/2015.
 */
public class ItemLlamada {
    private String nombre;
    private String telefono;
    private int foto;
    private String fecha;

    public ItemLlamada(String nom, String tel, int fot, String fec){
        nombre = nom;
        telefono = tel;
        foto = fot;
        fecha = fec;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }

    public int getFoto() {
        return foto;
    }

    public String getFecha(){
        return fecha;
    }
}

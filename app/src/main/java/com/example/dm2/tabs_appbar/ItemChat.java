package com.example.dm2.tabs_appbar;

/**
 * Created by dm2 on 16/10/2015.
 */
public class ItemChat {
    private String titulo;
    private String enlace;
    private int logo;

    public ItemChat(String tit, String enl, int log){
        titulo = tit;
        enlace = enl;
        logo = log;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getEnlace(){
        return enlace;
    }

    public int getLogo() {
        return logo;
    }
}

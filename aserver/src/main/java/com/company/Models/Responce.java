package com.company.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Responce implements Serializable {

    private static final long serialVersionUID = 0x0fffa;

    public ArrayList<String> responces;

    public void add(String resp){
        responces.add(resp);
    }
    public void clear(){
        responces.clear();
    }

    public void setResponces(ArrayList<String> responces) {
        this.responces = responces;
    }
    public Responce(){
        responces = new ArrayList<>();
    }

    public Responce(ArrayList<String> strings){
        this.responces = strings;
    }
}

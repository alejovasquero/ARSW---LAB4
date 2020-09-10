/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author cristian
 */
public class Cinema {
    private String name;
    private CopyOnWriteArrayList<CinemaFunction> functions; 
    
    
    public Cinema(){}
    
    public Cinema(String name,CopyOnWriteArrayList<CinemaFunction> functions){
        this.name=name;
        this.functions=functions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CopyOnWriteArrayList<CinemaFunction> getFunctions() {
        return this.functions;
    }

    public void setSchedule(CopyOnWriteArrayList<CinemaFunction> functions) {
        this.functions = functions;
    }
}

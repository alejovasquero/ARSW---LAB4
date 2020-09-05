/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cristian
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {

    @Autowired
    CinemaServices cinemaServices;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoCinema(){
        try {
            System.out.println(objectToJson(cinemaServices.getAllCinemas()));
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(objectToJson(cinemaServices.getAllCinemas()),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoCinemaNombre(@PathVariable String name){
        try {
            System.out.println(name);
            String json = objectToJson(cinemaServices.getCinemaByName(name));
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(json,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("HTTP 404 Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{name}/{date}")
    public ResponseEntity<?>  manejadorGetRecursoCinemaNombreFecha(
            @PathVariable String name, @PathVariable("date") String fecha){
        String ans = null;
        try {
            System.out.println(fecha);
            ans = objectToJson(cinemaServices.getFunctionsbyCinemaAndExactDay(name, fecha));

            return new ResponseEntity<>(ans,HttpStatus.NOT_FOUND);
        } catch (CinemaException e) {
            return new ResponseEntity<>("HTTP 404 Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{name}/{date}/{moviename}")
    public ResponseEntity<?>  manejadorGetRecursoCinemaNombreFecha(
            @PathVariable String name, @PathVariable("date") String fecha, @PathVariable String moviename){
        String ans = null;
        try {
            CinemaFunction a = cinemaServices.getFunctionbyCinemaDateAndMovie(name, fecha, moviename);
            ans = objectToJson(a);
            return new ResponseEntity<>(ans,HttpStatus.NOT_FOUND);
        } catch (CinemaException e) {
            return new ResponseEntity<>("HTTP 404 Not Found",HttpStatus.NOT_FOUND);
        }
    }

    private String objectToJson(Object a){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(a);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;

    }
}

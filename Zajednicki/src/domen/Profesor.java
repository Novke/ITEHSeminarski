/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Novica
 */
public class Profesor implements Serializable{
    
    private String ime;
    private String prezime;
    private String email;
    private String zvanje;
    private String radnikEmail;
    private List<Angazovanje> angazovanja = new ArrayList<>();

    public List<Angazovanje> getAngazovanja() {
        return angazovanja;
    }

    public void setAngazovanja(List<Angazovanje> angazovanja) {
        this.angazovanja = angazovanja;
    }
    

    public Profesor() {
    }

    
    

    public Profesor(String ime, String prezime, String email, String zvanje, String radnikEmail) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.zvanje = zvanje;
        this.radnikEmail = radnikEmail;
    }

    public String getRadnikEmail() {
        return radnikEmail;
    }

    public void setRadnikEmail(String radnikEmail) {
        this.radnikEmail = radnikEmail;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }
    
    
    
}

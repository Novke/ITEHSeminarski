/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

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

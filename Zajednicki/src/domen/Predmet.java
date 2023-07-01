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
public class Predmet implements Serializable{
    
    private int sifra;
    private String naziv;
    private String kod;
    private int espb;

    public Predmet() {
    }

    public Predmet(int sifra, String naziv, String kod, int espb) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.kod = kod;
        this.espb = espb;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
    
    
    
}

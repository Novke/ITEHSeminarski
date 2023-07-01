/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kontr;

import baza.DBBroker;
import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import domen.Radnik;
import java.util.ArrayList;
import java.sql.*;

public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;
    private ArrayList<Radnik> radnici = new ArrayList<>();

    private Kontroler() {
        dbb = new DBBroker();
        radnici.add(new Radnik("Ana", "Anic", "ana@gmail.com", "ana"));
        radnici.add(new Radnik("Petar", "Peric", "pera@gmail.com", "pera"));
        radnici.add(new Radnik("ime", "prezime", "admin", "admin"));
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Radnik login(String username, String password) {
        for (Radnik radnik : radnici) {
            if (radnik.getEmail().equals(username) && radnik.getLozinka().equals(password)) {
                return radnik;
            }
        }
        return null;
    }

    public boolean sacuvajProfesora(Profesor profesor) {
        try {
            return dbb.sacuvajProfesora(profesor);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Profesor> vratiProfesore() {
        return dbb.vratiProfesore();
    }

    public String sacuvajPredmet(Predmet predmet) {
        try {
            return dbb.sacuvajPredmet(predmet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Desila se greska prilikom cuvanja predmeta i angazovanja...";
    }

    public ArrayList<Angazovanje> vratiAngazovanjaProfesora(Profesor profesorAngazovanje) {
        return dbb.vratiAngazovanja(profesorAngazovanje);
    }

    public boolean obrisiAngazovanje(Angazovanje angazovanjeZaBrisanje) {
        try {
            return dbb.obrisiAngazovanje(angazovanjeZaBrisanje);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean dodajAngazovanje(Angazovanje angazovanjeZaDodavanje) {
        try {
            return dbb.dodajAngazovanje(angazovanjeZaDodavanje);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Predmet> vratiPredmete() {
        return dbb.vratiPredmete();
    }

    public boolean izmeniAngazovanje(Angazovanje angazovanjeZaIzmenu) {
        try {
            return dbb.izmeniAngazovanje(angazovanjeZaIzmenu);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<ProfesorPredmeti> vratiProfesorPredmete() {
        return dbb.vratiProfesorPredmete();
    }

    public ArrayList<Zvanje> vratiZvanja() {
        return dbb.vratiZvanja();
    }

}

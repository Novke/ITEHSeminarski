/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import Konstate.Operacije;
import Kontr.Kontroler;
import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import domen.Radnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    String[] str =  (String[]) kz.getParametar();
                    String username = str[0];
                    String password = str[1];
                    Radnik radnik = Kontroler.getInstance().login(username, password);
                    so.setOdgovor(radnik);
                    break;
                    
                case Operacije.SACUVAJ_PROFESORA:
                    Profesor profesor = (Profesor) kz.getParametar();
                    boolean uspesnoCuvanjeProfesora = Kontroler.getInstance().sacuvajProfesora(profesor);
                    so.setOdgovor(uspesnoCuvanjeProfesora);
                    break;
                    
                case Operacije.VRATI_PROFESORE:
                    ArrayList<Profesor> profesori = Kontroler.getInstance().vratiProfesore();
                    so.setOdgovor(profesori);
                    break;
                    
                    
                    
                    
                    
                case Operacije.SACUVAJ_PREDMET:
                    Predmet predmet = (Predmet) kz.getParametar();
                    String uspesnoCuvanjePredmeta = Kontroler.getInstance().sacuvajPredmet(predmet);
                    so.setOdgovor(uspesnoCuvanjePredmeta);
                    break;
                    
                    
                case Operacije.VRATI_ANGAZOVANJA_PROFESORA:
                    Profesor profesorAngazovanje = (Profesor) kz.getParametar();
                    ArrayList<Angazovanje> angazovanjaProfesora = Kontroler.getInstance()
                            .vratiAngazovanjaProfesora(profesorAngazovanje);
                    so.setOdgovor(angazovanjaProfesora);
                    break;
                    
                    
                    
                case Operacije.OBRISI_ANGAZOVANJE:
                    Angazovanje angazovanjeZaBrisanje = (Angazovanje) kz.getParametar();
                    boolean uspesnoBrisanjeAngazovanja = Kontroler.getInstance().obrisiAngazovanje(angazovanjeZaBrisanje);
                    so.setOdgovor(uspesnoBrisanjeAngazovanja);
                    break;
                case Operacije.DODAJ_NOVO_ANGAZOVANJE:
                    Angazovanje angazovanjeZaDodavanje = (Angazovanje) kz.getParametar();
                    boolean uspesnoDodavanjeAngazovanja = Kontroler.getInstance().dodajAngazovanje(angazovanjeZaDodavanje);
                    so.setOdgovor(uspesnoDodavanjeAngazovanja);
                    break;
                case Operacije.VRATI_PREDMETE:
                    ArrayList<Predmet> predmeti = Kontroler.getInstance().vratiPredmete();
                    so.setOdgovor(predmeti);
                    break;
                case Operacije.IZMENI_ANGAZOVANJE:
                    Angazovanje angazovanjeZaIzmenu = (Angazovanje) kz.getParametar();
                    boolean uspesnaIzmenaAngazovanja = Kontroler.getInstance().izmeniAngazovanje(angazovanjeZaIzmenu);
                    so.setOdgovor(uspesnaIzmenaAngazovanja);
                    break;
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("KLIJENT SE ODVEZAO (UGASIO/LA SI KLIJENTSKU APLIKACIJU),"
                    + " ZATO SE DESIO OVAJ EXCEPTION, NE BRINI SE NISTA! ARI");
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

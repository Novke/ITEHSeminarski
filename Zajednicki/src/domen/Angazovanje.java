/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.Date;

public class Angazovanje implements Serializable {
    
    private long angazovanjeID;
    private Date datumAngazovanja;
    private Profesor profesor;
    private Predmet predmet;

    public Angazovanje(long angazovanjeID, Date datumAngazovanja, Profesor profesor, Predmet predmet) {
        this.angazovanjeID = angazovanjeID;
        this.datumAngazovanja = datumAngazovanja;
        this.profesor = profesor;
        this.predmet = predmet;
    }

    public Angazovanje() {
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Date getDatumAngazovanja() {
        return datumAngazovanja;
    }

    public void setDatumAngazovanja(Date datumAngazovanja) {
        this.datumAngazovanja = datumAngazovanja;
    }


    public long getAngazovanjeID() {
        return angazovanjeID;
    }

    public void setAngazovanjeID(long angazovanjeID) {
        this.angazovanjeID = angazovanjeID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.angazovanjeID ^ (this.angazovanjeID >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Angazovanje other = (Angazovanje) obj;
        return true;
    }
    
    
    
}

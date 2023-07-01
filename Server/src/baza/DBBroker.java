/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import java.sql.*;
import java.util.ArrayList;


public class DBBroker {
    
    public ArrayList<Profesor> vratiProfesore() {
        ArrayList<Profesor> lista = new ArrayList<>();
        String upit = "SELECT * FROM PROFESOR";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                    Profesor profesor = new Profesor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    
                lista.add(profesor);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
    public boolean sacuvajProfesora(Profesor profesor) throws SQLException {
        String upit = "INSERT INTO PROFESOR (Ime, Prezime, email, Zvanje, radnikEmail) "
                + "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.setString(1, profesor.getIme());
            ps.setString(2, profesor.getPrezime());
            ps.setString(3, profesor.getEmail());
            ps.setString(4, profesor.getZvanje());
            ps.setString(5, profesor.getRadnikEmail());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            ex.printStackTrace();
        }
        return false;
    }
    
    public String sacuvajPredmet(Predmet predmet) throws SQLException {
        String upit = "INSERT INTO PREDMET (Naziv, Kod, ESPB) VALUES (?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection()
                    .prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, predmet.getNaziv());
            ps.setString(2, predmet.getKod());
            ps.setInt(3, predmet.getEspb());

            ps.executeUpdate();

            ResultSet tableKeys = ps.getGeneratedKeys();
            tableKeys.next();
            int predmetID = tableKeys.getInt(1);

            predmet.setSifra(predmetID);

            if (sacuvajAngazovanja(predmet).equals("Uspesno sacuvan predmet i njegova angazovanja!")) {
                Konekcija.getInstance().getConnection().commit();
                return "Uspesno sacuvan predmet i njegova angazovanja!";
            } else {
                Konekcija.getInstance().getConnection().rollback();
                return sacuvajAngazovanja(predmet);
            }

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            ex.printStackTrace();
        }

        return "2Desila se greska prilikom cuvanja predmeta i angazovanja...";
    }
    
    
//    private String sacuvajAngazovanja(Predmet predmet) throws SQLException {
//        String upit = "INSERT INTO ANGAZOVANJE (DatumAngazovanja, EmailKorisnika, "
//                + "ProfesorID, PredmetID) VALUES (?,?,?,?)";
//        try {
//            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
//
//            for (Angazovanje angazovanje : predmet.getAngazovanja()) {
//
//                if (proveriDaLiJeAngazovanNaTriIliVisePredmeta(angazovanje.getProfesor())) {
//                    Konekcija.getInstance().getConnection().rollback();
//                    return "Profesor " + angazovanje.getProfesor().toString() + " je "
//                            + "vec angazovan na vise od 3 predmeta!";
//                }
//
//                angazovanje.setPredmet(predmet);
//                ps.setDate(1, new Date(angazovanje.getDatumAngazovanja().getTime()));
//                ps.setString(2, angazovanje.getEmailKorisnika());
//                ps.setLong(3, angazovanje.getProfesor().getProfesorID());
//                ps.setLong(4, angazovanje.getPredmet().getPredmetID());
//
//                ps.addBatch();
//            }
//
//            ps.executeBatch();
//            Konekcija.getInstance().getConnection().commit();
//
//            return "Uspesno sacuvan predmet i njegova angazovanja!";
//
//        } catch (SQLException ex) {
//            Konekcija.getInstance().getConnection().rollback();
//            ex.printStackTrace();
//        }
//        return "Desila se greska prilikom cuvanja predmeta i angazovanja...";
//    }
    
    private String sacuvajAngazovanja(Profesor profesor, Predmet predmet) throws SQLException {
        String upit = "INSERT INTO ANGAZOVANJE (Datum, profesorid, predmetid)"
                + " VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            

            for (Angazovanje angazovanje : profesor.getAngazovanja()) {

//                if (proveriDaLiJeAngazovanNaTriIliVisePredmeta(angazovanje.getProfesor())) {
//                    Konekcija.getInstance().getConnection().rollback();
//                    return "Profesor " + angazovanje.getProfesor().toString() + " je "
//                            + "vec angazovan na vise od 3 predmeta!";
//                }

                

                angazovanje.setPredmet(predmet);
                ps.setDate(1, new Date(angazovanje.getDatumAngazovanja().getTime()));
                ps.setString(2, angazovanje.getProfesor().getEmail());
                ps.setLong(3, angazovanje.getPredmet().getSifra());

                ps.addBatch();
            }
            
            if (vratiAngazovanja(profesor).size()>=4){
                return "Profesor " + profesor.toString() + " je "
                            + "vec angazovan na vise od 3 predmeta!";
            }
            ps.executeBatch();
            Konekcija.getInstance().getConnection().commit();

            return "Uspesno sacuvan predmet i njegova angazovanja!";

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            ex.printStackTrace();
        }
        return "Desila se greska prilikom cuvanja predmeta i angazovanja...";
    }
    
    
    public ArrayList<Angazovanje> vratiAngazovanja(Profesor profesorAngazovanje) {
        ArrayList<Angazovanje> lista = new ArrayList<>();
        String upit = "SELECT * FROM ANGAZOVANJE A "
                + "JOIN PREDMET P ON (A.PREDMETID = P.PREDMETID) "
                + "WHERE PROFESORID = "
                + profesorAngazovanje.getEmail();
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Predmet predmet = new Predmet();
                predmet.setEspb(rs.getInt("espb"));
                predmet.setKod(rs.getString("kod"));
                predmet.setNaziv(rs.getString("naziv"));
                predmet.setSifra(rs.getInt("sifra"));
                
                Angazovanje a = new Angazovanje();
                a.setAngazovanjeID(rs.getInt("id"));
                a.setDatumAngazovanja(rs.getDate("datum"));
                a.setPredmet(predmet);
                a.setProfesor(profesorAngazovanje);

                lista.add(a);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
    
    public boolean obrisiAngazovanje(Angazovanje angazovanjeZaBrisanje) throws SQLException {
        String upit = "DELETE FROM ANGAZOVANJE WHERE ANGAZOVANJEID = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.setLong(1, angazovanjeZaBrisanje.getAngazovanjeID());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            ex.printStackTrace();
        }
        return false;
    }

    public boolean dodajAngazovanje(Angazovanje angazovanjeZaDodavanje) throws SQLException {
        String upit = "INSERT INTO ANGAZOVANJE (datum, "
                + "ProfesorID, PredmetID) VALUES (?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.setDate(1, new Date(angazovanjeZaDodavanje.getDatumAngazovanja().getTime()));
            ps.setString(3, angazovanjeZaDodavanje.getProfesor().getEmail());
            ps.setLong(4, angazovanjeZaDodavanje.getPredmet().getSifra());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Predmet> vratiPredmete() {
        ArrayList<Predmet> lista = new ArrayList<>();
        String upit = "SELECT * FROM PREDMET";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Predmet predmet = new Predmet();
                predmet.setEspb(rs.getInt("espb"));
                predmet.setKod(rs.getString("kod"));
                predmet.setNaziv(rs.getString("naziv"));
                predmet.setSifra(rs.getInt("sifra"));
                lista.add(predmet);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public boolean izmeniAngazovanje(Angazovanje angazovanjeZaIzmenu) throws SQLException {
        String upit = "UPDATE ANGAZOVANJE SET DatumAngazovanja = ? WHERE ANGAZOVANJEID = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.setDate(1, new Date(angazovanjeZaIzmenu.getDatumAngazovanja().getTime()));
            ps.setLong(2, angazovanjeZaIzmenu.getAngazovanjeID());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            ex.printStackTrace();
        }
        return false;
    }

    
}

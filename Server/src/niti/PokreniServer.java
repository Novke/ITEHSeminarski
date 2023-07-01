/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Novica
 */
public class PokreniServer extends Thread {

    public PokreniServer() {
        start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverskiSoket = new ServerSocket(9000);
            while (true) {
                Socket s = serverskiSoket.accept();
                System.out.println("Klijent se povezao!");
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
                nit.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        new PokreniServer();
    }

}

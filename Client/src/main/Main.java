/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import communication.Communication;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.forms.LoginForm;

/**
 *
 * @author David
 */
public class Main {
    public static void main(String[] args){
        
        try {
            connect();
            new LoginForm().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Neuspesno povezivanje sa serverom", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void connect() throws IOException{
        //TODO
        Socket socket = new Socket("localhost",3000);
        Communication.getInstance().setSocket(socket);
    }
}

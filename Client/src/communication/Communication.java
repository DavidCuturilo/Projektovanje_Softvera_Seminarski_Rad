/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author David
 */
public class Communication {
    private static Communication instance;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private Communication(){
        
    }
    
    public static Communication getInstance(){
        if (instance == null) instance = new Communication();
        return instance;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.setSocketStreams();
    }

    public Socket getSocket() {
        return socket;
    }

    private void setSocketStreams() throws IOException {
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
        this.in = new ObjectInputStream(this.socket.getInputStream());
    }
    
    public Response send(Request request)throws Exception{
        this.out.reset();
        this.out.writeObject(request);
        Response response = (Response) this.in.readObject();
        return response;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
    
    
}

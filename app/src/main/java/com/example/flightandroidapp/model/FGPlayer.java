package com.example.flightandroidapp.model;

import java.io.PrintWriter;
import java.net.Socket;

public class FGPlayer {
    private Socket socket;
    private PrintWriter out;
    private volatile String message = null;
    private Thread thread = null;
    private volatile boolean stop = false;

    private static class HelperHolder {
        public static final FGPlayer fg = new FGPlayer();
    }

    public static FGPlayer getFgPlayer() {
        return HelperHolder.fg;
    }

    public void connect(String ip, String port) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, Integer.parseInt(port));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    while (!stop) {
                        if (message != null) {
                            synchronized (this) {
                                if (message != null) {
                                    out.print(message);
                                    out.flush();
                                    message = null;
                                }
                            }
                        }
                    }
                    socket.close();
                    out.close();

                } catch (Exception e) {
                    System.out.println("error in creating connection");
                    if (socket != null)
                        try {
                            socket.close();
                        } catch (Exception e1) {
                        }
                    if (out != null)
                        out.close();
                    return;
                }
            }
        });
        thread.start();

    }

    public void disconnect() {
        stop = true;
        try {
            thread.join();
        } catch (Exception e) {
        }
    }

    public void sendToFg(String type,String type2 ,float val,float val2 ) {
        synchronized (this) {
            if(type2!=null) // belongs to joystick
                message="set /controls/flight/aileron "+Float.toString(val)+ " \r\n"+
                        "set /controls/flight/elevator "+Float.toString(val2)+ " \r\n";
            else if (type.equals("throttle"))
                message = "set /controls/engines/current-engine/throttle "+Float.toString(val)+ " \r\n";
            else // rudder
                message  = "set /controls/flight/rudder "+Float.toString(val)+" \r\n";
        }

    }


}
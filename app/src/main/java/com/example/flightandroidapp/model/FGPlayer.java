package com.example.flightandroidapp.model;

import android.text.Editable;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class FGPlayer{
    private double aileron;
    private double elevator;
    private double rudder;
    private double throttle;
    private Socket socket;
    private PrintWriter out;
    private String message=null;
    private Thread thread=null;
    private volatile boolean stop= false;
    private static class HelperHolder{
        public static final FGPlayer fg=new FGPlayer();
    }

    public static FGPlayer getFgPlayer(){
        return HelperHolder.fg;
    }
    public void connect(String ip, String port) throws Exception {
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, Integer.parseInt(port));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    while(!stop){
                        synchronized (this){
                            if(message!=null) {
                                out.print(message);
                                out.flush();
                                message=null;
                            }
                        }
                    }
                    socket.close();
                    out.close();

                } catch (Exception e) {
                    System.out.println("error in creating connection");
                    if(socket!=null)
                        try{socket.close();} catch (Exception e1){}
                    if(out!=null)
                        out.close();
                    return;
                }
            }
        });
        thread.start();



    }

    public void disconnect(){
        stop=true;
        try {
            thread.join();
        }
        catch (Exception e){}
    }
    public void sendToFg(String type, double val){
        message= "set /controls";
        if(type.equals("throttle"))
            message+="/engines/current-engine";
        else
            message+="/flight";
        message+="/"+type+" "+Double.toString(val)+"\r\n";
//        out.print(this.message+" "+Double.toString(val)+"\r\n");
//        out.flush();
    }


}

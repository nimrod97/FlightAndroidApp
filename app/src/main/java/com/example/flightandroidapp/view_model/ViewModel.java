package com.example.flightandroidapp.view_model;

import android.text.Editable;
import android.widget.EditText;

import com.example.flightandroidapp.model.FGPlayer;

import java.io.IOException;

public class ViewModel {
    private FGPlayer model;
    private double VM_throttle;
    private double VM_rudder;
    private double VM_ailerone;
    private double VM_elevator;

    public ViewModel(){
        this.model=FGPlayer.getFgPlayer();
    }
    public void VM_connect(String ip, String port) throws Exception {
        this.model.connect(ip, port);
    }
    public void setVM_throttle(double val){
        this.VM_throttle=val;
        this.model.sendToFg("throttle",this.VM_throttle);
    }
    public void setVM_rudder(double val){
        this.VM_rudder=val;
        this.model.sendToFg("rudder",this.VM_rudder);
    }
}

package com.example.flightandroidapp.view_model;


import com.example.flightandroidapp.model.FGPlayer;


public class ViewModel {
    private FGPlayer model;
    private float VM_throttle;
    private float VM_rudder;
    private float VM_aileron;
    private float VM_elevator;

    public ViewModel(){
        this.model=FGPlayer.getFgPlayer();
    }
    public void VM_connect(String ip, String port) throws Exception {
        this.model.connect(ip, port);
    }
    public void setVM_throttle(float val){
        this.VM_throttle=val;
        this.model.sendToFg("throttle",null,this.VM_throttle,0);
    }
    public void setVM_rudder(float val){
        this.VM_rudder=val;
        this.model.sendToFg("rudder",null,this.VM_rudder,0);
    }

    public void set_joystick_elements(float a, float e){
        this.VM_aileron=a;
        this.VM_elevator=e;
        this.model.sendToFg("aileron","elevator",this.VM_aileron,this.VM_elevator);
    }

    public void VM_destroy(){
        this.model.disconnect();
    }
}

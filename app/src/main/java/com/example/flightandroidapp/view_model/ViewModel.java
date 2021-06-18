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
        this.model.sendToFg("throttle",this.VM_throttle);
    }
    public void setVM_rudder(float val){
        this.VM_rudder=val;
        this.model.sendToFg("rudder",this.VM_rudder);
    }

    public void setVM_aileron(float val) {
        this.VM_aileron = val;
        this.model.sendToFg("aileron",this.VM_aileron);
    }

    public void setVM_elevator(float val) {
        this.VM_elevator = val;
        this.model.sendToFg("elevator",this.VM_elevator);

    }
    public void VM_destroy(){
        this.model.disconnect();
    }
}

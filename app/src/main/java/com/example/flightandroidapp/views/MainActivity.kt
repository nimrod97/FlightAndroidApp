package com.example.flightandroidapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import com.example.flightandroidapp.R
import com.example.flightandroidapp.model.FGPlayer
import com.example.flightandroidapp.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    open var viewModel: ViewModel? =null
    private var joystick: Joystick?=null
    private var throttle: SeekBar?= null
    private var rudder: SeekBar? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.activity_joystick)
        viewModel= ViewModel()
        joystick=Joystick()
        throttle=findViewById<SeekBar>(R.id.throttle)
        rudder=findViewById<SeekBar>(R.id.rudder)
        throttle?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar?, p1: Int, p2: Boolean) {
                val old_val=p1
                val old_min=0
                val old_max=100
                val new_min=0
                val new_max=1
                val new_val: Double
                new_val= (((old_val-old_min)/(old_max-old_min)) * (new_max-new_min) +new_min).toDouble()
                viewModel!!.setVM_throttle(new_val)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        })
        rudder?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar?, p1: Int, p2: Boolean) {
                val old_val=p1
                val old_min=0
                val old_max=100
                val new_min=-1
                val new_max=1
                val new_val: Double
                new_val= (((old_val-old_min)/(old_max-old_min)) * (new_max-new_min) +new_min).toDouble()
                viewModel!!.setVM_rudder(new_val)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        })
//        joystick= Joystick(this)
//        setContentView(joystick)

    }
    fun onClickFunc(view: View){
        val ip=findViewById<EditText>(R.id.ip)
        val port=findViewById<EditText>(R.id.port)
        viewModel?.VM_connect(ip.text.toString(),port.text.toString())
    }


}
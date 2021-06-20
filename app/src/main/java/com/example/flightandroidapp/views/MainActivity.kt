package com.example.flightandroidapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import com.example.flightandroidapp.R
import com.example.flightandroidapp.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: ViewModel? =null
    private var joystick:Joystick?=null
    private var throttle: SeekBar?=null
    private var rudder: SeekBar?=null
    private var aileron: Float = 0F
    private var elevator: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModel()
        joystick=findViewById<com.example.flightandroidapp.views.Joystick>(R.id.joystick)
        joystick?.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if(joystick!!.onTouchEvent(p1)==true){
                    aileron= joystick!!.dx/250
                    elevator= joystick!!.dy /-250
                    viewModel?.set_joystick_elements(aileron,elevator)
                }
                return true
            }
        })
        throttle = findViewById<SeekBar>(R.id.throttle)
        rudder = findViewById<SeekBar>(R.id.rudder)
        throttle?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, p1: Int, p2: Boolean) {
                val send:Float=p1/100.0F
                viewModel!!.setVM_throttle(send)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                val send: Float= p0!!.progress/100.0F
                viewModel!!.setVM_throttle(send)

            }
        })
        rudder?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, p1: Int, p2: Boolean) {
                val send:Float= p1/100.0F
                viewModel!!.setVM_rudder(send)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                val send:Float= p0!!.progress/100.0F
                viewModel!!.setVM_rudder(send)
            }
        })


    }

    fun onClickFunc(view: View) {
        val ip = findViewById<EditText>(R.id.ip)
        val port = findViewById<EditText>(R.id.port)
        viewModel?.VM_connect(ip.text.toString(), port.text.toString())
    }

    override fun onDestroy() {
        viewModel?.VM_destroy()
        super.onDestroy()
    }



}
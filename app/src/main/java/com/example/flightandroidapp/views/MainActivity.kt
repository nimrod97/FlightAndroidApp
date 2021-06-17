package com.example.flightandroidapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import com.example.flightandroidapp.R
import com.example.flightandroidapp.databinding.ActivityMainBinding
import com.example.flightandroidapp.view_model.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), Observer {

    open var viewModel: ViewModel? = null

    //    private var joystick: Joystick?=null
    private var throttle: SeekBar? = null
    private var rudder: SeekBar? = null
    private var ailerone: Float = 0F
    private var elevator: Float = 0F
    private var onJoystickChange: OnJoystickChange? = null

    //    private var touchListener :View.OnTouchListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModel()
//        joystick=Joystick(this)
//        binding=DataBindingUtil.setContentView<>(this,R.layout.activity_main)
        throttle = findViewById<SeekBar>(R.id.throttle)
        rudder = findViewById<SeekBar>(R.id.rudder)

        throttle?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, p1: Int, p2: Boolean) {
                val old_val = p1
                val old_min = 0
                val old_max = 100
                val new_min = 0
                val new_max = 1
                val new_val: Float
                new_val =
                    (((old_val - old_min) / (old_max - old_min)) * (new_max - new_min) + new_min).toFloat()
                viewModel!!.setVM_throttle(new_val)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        })
        rudder?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, p1: Int, p2: Boolean) {
                val old_val = p1
                val old_min = 0
                val old_max = 100
                val new_min = -1
                val new_max = 1
                val new_val: Float
                new_val =
                    (((old_val - old_min) / (old_max - old_min)) * (new_max - new_min) + new_min).toFloat()
                viewModel!!.setVM_rudder(new_val)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        })
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
//        joystick.onTouchEvent(event: MotionEvent?)
//        ailerone = binding.joystick.dx / 100
//        elevator = binding.joystick.dy / 100
//        viewModel?.setVM_ailerone(ailerone)
//        viewModel?.setVM_el(elevator)
        var joystick = Joystick(this)
        joystick.onChange = OnJoystickChange { a: Float, e: Float ->
            viewModel!!.setVM_ailerone(a)
            viewModel!!.setVM_elevator(e)

        }
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

//    fun onChange(joystick: Joystick){
//
//    }

    override fun update(p0: Observable?, p1: Any?) {
        TODO("Not yet implemented")
    }
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        joystick?.onTouchEvent(event)
//        val xPos= joystick?.getDx()
//        val yPos= joystick?.getDy()
//        if (xPos != null) {
//            viewModel?.setVM_ailerone(xPos/100)
//        }
//        if (yPos != null) {
//            viewModel?.setVM_elevator(yPos/100)
//        }
//        return true;
//    }


}
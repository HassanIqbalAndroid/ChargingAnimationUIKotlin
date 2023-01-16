package com.example.charginganimationt1

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.Settings
import com.example.charginganimationt1.databinding.ActivitySplashScreenBinding
import com.example.charginganimationt1.introscreens.InterScreen1

class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Call battery manager service
        val bm = applicationContext.getSystemService(BATTERY_SERVICE) as BatteryManager

        // Get the battery percentage and store it in a INT variable
        val batLevel:Int = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        //Show bettry
        var charging = "$batLevel" +"%" + "/n"+ "Charging"
        binding.chargingShow.text =charging
        var permissionCheck = ""
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(Settings.canDrawOverlays(this)){
                permissionCheck = "Granted"
            }else{
                permissionCheck = "FirstTimeUser"
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            redirect(permissionCheck)
        },5000)
    }

    private fun redirect(name:String){
        val intent= when(name){
            "Granted"->{
                Intent(this,MainActivity::class.java)
            }
            "FirstTimeUser"->{
                Intent(this, InterScreen1::class.java)
            }
            else -> throw java.lang.Exception("Not Path Exist")
        }
        startActivity(intent)
        this.finish()
    }
}
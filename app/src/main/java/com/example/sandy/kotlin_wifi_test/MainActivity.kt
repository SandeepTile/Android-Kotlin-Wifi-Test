package com.example.sandy.kotlin_wifi_test

import android.content.Context
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wManager=applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        var status=wManager.wifiState

        if (status==0||status==1){

            switch1.isChecked==false
        }else if (status==2||status==3){

            switch1.isChecked=true
        }

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->

            wManager.setWifiEnabled(isChecked)

            var vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vib.vibrate(10000)
        }

        gwd.setOnClickListener {

            var slist=wManager.scanResults

            var templist:MutableList<String> = mutableListOf()

            for(device in slist){

                templist.add(device.SSID+"\n"+device.frequency)

            }

            var myAdapter=ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,templist)


        }


    }
}

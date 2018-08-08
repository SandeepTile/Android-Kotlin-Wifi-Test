package com.example.sandy.kotlin_wifi_test

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
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

            /*
                set permission manually(location) otherwise this function is not working properly
            */


           // Toast.makeText(this,"entered",Toast.LENGTH_LONG).show()

            var slist:List<ScanResult> = wManager.scanResults

            var templist:MutableList<String> = mutableListOf()

            for(device in slist){

                templist.add(device.SSID+"\n"+device.frequency)

            }

            var myAdapter=ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,templist)

            lv1.adapter=myAdapter

            Log.i("scan","check")

        }

        gpd.setOnClickListener {
            var list:List<WifiConfiguration> =    wManager.configuredNetworks
            var temp_list : MutableList<String>   = mutableListOf()
            for(device in list){
                temp_list.add(device.SSID+"\n"+device.status)
            }
            var myadapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, temp_list)
            lv1.adapter = myadapter
        }



    }
}

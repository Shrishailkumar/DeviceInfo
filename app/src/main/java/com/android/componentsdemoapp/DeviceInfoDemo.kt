package com.android.componentsdemoapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.appcomponents.model.DeviceInfo
import com.android.appcomponents.util.Utility
import com.android.appcomponents.viewmodel.DeviceInfoViewModel


class DeviceInfoDemo : AppCompatActivity() {

    lateinit var mTvDeviceData : TextView
    //the Component viewModel accessed here
    private lateinit var deviceInfoViewModel: DeviceInfoViewModel
    //the Component Class Utiltiy accessed here
    val utilityLocOb = Utility(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.device_info_demo)
        mTvDeviceData = findViewById(R.id.id_deviceInfo)

        utilityLocOb.isNetworkConnected()
        if (!utilityLocOb.isNetworkConnected()) {
            Toast.makeText(this, getString(R.string.conccetivity_lost_text), Toast.LENGTH_LONG).show()
        } else {
            utilityLocOb.getCordinates()
            deviceInfoViewModel = ViewModelProvider(this).get(DeviceInfoViewModel::class.java)
            deviceInfoViewModel.getDeviceData()
                .observe( this, Observer<DeviceInfo?> {
                    updateUI(it)
                })
        }
    }

    private fun updateUI(build: DeviceInfo) {
       mTvDeviceData.setText( "SERIAL: " + build.serial + "\n" +
               "MODEL: " + build.model + "\n" +
               "ID: " + build.id + "\n" +
               "Manufacture: " + build.manufacture + "\n" +
               "Brand: " + build.brand + "\n" +
               "Type: " + build.type + "\n" +
               "User: " + build.user + "\n" +
               "SDK:  " + build.sdk + "\n" +
               "BOARD: " + build.board + "\n" +
               "BRAND: " + build.brand + "\n" +
               "HOST: " + build.host + "\n" +
               "FINGERPRINT: "+build.fingerPrint + "\n" +
               "Device: " + build.Device +"\n" +
               "BootLoader: "+build.bootLoader + "\n" +
               "Display: "+build.display + "\n" +
               "Hardware: "+build.hardware + "\n" +
               "product: "+build.product  )
    }
}
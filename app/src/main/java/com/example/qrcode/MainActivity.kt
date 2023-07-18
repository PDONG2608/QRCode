package com.example.qrcode

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.URLUtil
import android.widget.Button
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartQRCode = findViewById<Button>(R.id.btnStartQRCode)
        btnStartQRCode.setOnClickListener {
            startQRScan();
        }
    }

    private fun startQRScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Scan QR code - phuong.dong")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(false)
        integrator.initiateScan()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val scannedData = result.contents
                if (URLUtil.isValidUrl(scannedData)) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(scannedData)
                    startActivity(intent)
                }
            }
        }
    }
}
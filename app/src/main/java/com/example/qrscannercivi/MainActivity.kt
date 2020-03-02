package com.example.qrscannercivi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var qrScannerBtn:Button
   // lateinit var result:TextView

    companion object{
       private var scanerResult:String? = null
        var result:TextView? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidget()

        qrScannerBtn.setOnClickListener {
             var intent = Intent(this, ScanCodeActivity::class.java)
            startActivity(intent)
        }


    }


    fun initWidget(){
        qrScannerBtn = findViewById(R.id.qr_scannerBtnId)
        result = findViewById(R.id.resultTvId)
    }

// menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.root_menu,menu)
        return true
    }

// selected menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.qr_generatorId -> {
                var intent = Intent(this, QRCodeGeneratorActivity::class.java)
                startActivity(intent)
                return true
            }else -> false
        }
    }

}

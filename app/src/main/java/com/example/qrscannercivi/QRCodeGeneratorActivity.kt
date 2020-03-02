package com.example.qrscannercivi

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.WindowDecorActionBar
import com.google.zxing.WriterException
import java.lang.Exception

class QRCodeGeneratorActivity : AppCompatActivity() {

    lateinit var generatorText:TextView
    lateinit var showQrCodeView:ImageView
    lateinit var generatorBtn:Button
    lateinit var bitmap: Bitmap
    lateinit var qrgEncoder: QRGEncoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_code_generator)
        initWidget()

        generatorBtn.setOnClickListener {
            bitmap = makeGenerator(generatorText.text.toString())
            showQrCodeView.setImageBitmap(bitmap)
        }
    }


    fun initWidget(){
        showQrCodeView = findViewById(R.id.imageViewId)
        generatorText = findViewById(R.id.genertorTvId)
        generatorBtn = findViewById(R.id.generatorBtnId)
    }

    fun makeGenerator(inputValue:String):Bitmap{
        var bitmap:Bitmap? = null
        var windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var display = windowManager.defaultDisplay
        var point = Point()
        display.getSize(point)
        var width = point.x
        var height = point.y
        var sizeMension:Int?= null

        if (width < height) sizeMension = width else sizeMension= height

        sizeMension = sizeMension * 3/4

        qrgEncoder = QRGEncoder(inputValue,null, QRGContents.Type.TEXT,sizeMension)

        try {
            bitmap = qrgEncoder.encodeAsBitmap()

        }catch (ex:WriterException){
            Log.e("QrGenerator", ex.message)
        }

        return bitmap!!
    }
}

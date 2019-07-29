package com.ceosilvajr.signme

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.github.gcacace.signaturepad.views.SignaturePad
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.ByteArrayOutputStream


/**
 * @author ceosilvajr@gmail.com
 */
class MainActivity : AppCompatActivity(), SignaturePad.OnSignedListener, AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signature_pad.setOnSignedListener(this@MainActivity)
        setupSaveButton()
        setupClearButton()
    }

    private fun setupClearButton() {
        btn_clear.setOnClickListener {
            signature_pad.clear()
        }
    }

    private fun setupSaveButton() {
        btn_save.setOnClickListener {
            val signatureBitmap = signature_pad.signatureBitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            val encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)
            info(encoded)
        }
    }

    override fun onStartSigning() {
        info("onStartSigning")
    }

    override fun onSigned() {
        info("onSigned")
    }

    override fun onClear() {
        info("onClear")
    }
}

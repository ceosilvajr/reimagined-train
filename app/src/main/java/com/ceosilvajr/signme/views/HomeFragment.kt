package com.ceosilvajr.signme.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceosilvajr.roomdb.entities.PirmaEntity
import com.ceosilvajr.signme.R
import com.ceosilvajr.signme.base.BaseFragment
import com.ceosilvajr.signme.extensions.toBase64String
import com.github.gcacace.signaturepad.views.SignaturePad
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

/**
 * @author ceosilvajr@gmail.com
 */
class HomeFragment : BaseFragment(), SignaturePad.OnSignedListener, AnkoLogger {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signature_pad.setOnSignedListener(this@HomeFragment)
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
            pirmaDao.insertPirma(PirmaEntity(base64EncodedImage = signatureBitmap.toBase64String()))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ activity!!.toast("Signature saved!") }
                            , { activity!!.toast("Error during saving!") }).addTo(disposable)
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

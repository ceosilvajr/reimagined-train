package com.ceosilvajr.signme.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceosilvajr.roomdb.entities.PirmaEntity
import com.ceosilvajr.signme.R
import com.ceosilvajr.signme.base.BaseFragment
import com.ceosilvajr.signme.extensions.base64ToBitmap
import com.ceosilvajr.signme.models.Pirma
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pirma_details.*
import org.jetbrains.anko.toast

/**
 * @author ceosilvajr@gmail.com
 */
class PirmaDetailsFragment : BaseFragment() {

    private lateinit var pirma: Pirma

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pirma = it.getSerializable(PIRMA) as Pirma
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pirma_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_pirma.setImageBitmap(pirma.encodedBase64Image.base64ToBitmap())
        tv_time.text = "Date: ${pirma.date}"
        btn_delete.setOnClickListener {
            pirmaDao.delete(PirmaEntity(pirma.id, pirma.encodedBase64Image, pirma.date))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        activity!!.toast("Item deleted.")
                        navController.navigateUp()
                    }, {
                        activity!!.toast("Error during delete.")
                    }).addTo(disposable)

        }
    }

    companion object {
        private const val PIRMA = "Pirma"
        fun createBundleFrom(model: Pirma): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(PIRMA, model)
            return bundle
        }
    }
}

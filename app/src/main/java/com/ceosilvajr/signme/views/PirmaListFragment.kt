package com.ceosilvajr.signme.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceosilvajr.signme.R
import com.ceosilvajr.signme.adapters.PirmaItemAdapter
import com.ceosilvajr.signme.base.BaseFragment
import com.ceosilvajr.signme.models.Pirma
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pirma_list.*
import org.jetbrains.anko.toast
import java.util.*

/**
 * @author ceosilvajr@gmail.com
 */
class PirmaListFragment : BaseFragment(), PirmaItemAdapter.Listener {

    private lateinit var pirmaAdapter: PirmaItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pirmaAdapter = PirmaItemAdapter(arrayListOf())
        pirmaAdapter.init(context!!, this@PirmaListFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pirma_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list.adapter = pirmaAdapter
        sr_view.setOnRefreshListener {
            fetchPirma()
        }
        fetchPirma()
    }

    private fun fetchPirma() {
        sr_view.isRefreshing = true
        pirmaDao.getAllPirma().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe({
            pirmaAdapter.updateList(it.map { Pirma(it.id, it.base64EncodedImage, it.date) } as ArrayList<Pirma>)
            sr_view.isRefreshing = false
        }, {
            sr_view.isRefreshing = false
            activity!!.toast("Error during fetch.")
        }).addTo(disposable)
    }

    override fun onPirmaItemClicked(pirma: Pirma) {
        navController.navigate(R.id.action_pirmaListFragment_to_pirmaDetailsFragment,
                PirmaDetailsFragment.createBundleFrom(pirma))
    }

}

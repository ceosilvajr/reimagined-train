package com.ceosilvajr.signme.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ceosilvajr.roomdb.AppLocalDatabase
import com.ceosilvajr.roomdb.dao.PirmaDao
import io.reactivex.disposables.CompositeDisposable

/**
 * @author ceosilvajr@gmail.com
 */
open class BaseFragment : Fragment() {

    val disposable = CompositeDisposable()
    private val appLocalDatabase: AppLocalDatabase = AppLocalDatabase()
    lateinit var pirmaDao: PirmaDao
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pirmaDao = appLocalDatabase.providerPirmaDao(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::navController.isInitialized) throw RuntimeException()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }
}
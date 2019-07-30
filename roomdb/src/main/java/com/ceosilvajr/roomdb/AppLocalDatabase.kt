package com.ceosilvajr.roomdb

import android.content.Context

/**
 * @author ceosilvajr@gmail.com
 */
class AppLocalDatabase {
    fun providerPirmaDao(context: Context) = AppDatabase.getInstance(context).pirmaDao()
}
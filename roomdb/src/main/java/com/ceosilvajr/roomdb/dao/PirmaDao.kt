package com.ceosilvajr.roomdb.dao

import androidx.room.*
import com.ceosilvajr.roomdb.entities.PirmaEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @author ceosilvajr@gmail.com
 */
@Dao
interface PirmaDao {

    @Query("SELECT * FROM Pirma WHERE pirmaId = :id")
    fun getPirmabyId(id: String): Flowable<PirmaEntity>

    @Query("SELECT * FROM Pirma")
    fun getAllPirma(): Flowable<List<PirmaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPirma(pirmaEntity: PirmaEntity): Completable

    @Delete
    fun delete(pirmaEntity: PirmaEntity): Completable

}
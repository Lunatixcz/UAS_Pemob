package com.example.uas.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UnivDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (univ: Univ)

    @Update
    fun update(univ: Univ)

    @Delete
    fun delete(univ: Univ)

    @Query("SELECT * FROM Univ")
    fun getAllUniv(): List<Univ>

    @Query("SELECT * FROM Univ WHERE name = :name AND country = :country")
    fun getUnivData(name: String?, country : String?): Univ?
}
package com.mobile.mp3_final.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.uas.database.Univ
import com.example.uas.database.UnivDao
import com.example.uas.database.UnivRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UnivRespos(application: Application) {

    private var mUnivDao:UnivDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UnivRoomDatabase.getDatabase(application)
        mUnivDao = db.UnivDao()
    }

    private val insertionStatus = MutableLiveData<Boolean>()

    fun insertIfNotExists(name: String?, country: String?) {
        executorService.execute {
            val existingUniv = mUnivDao.getUnivData(name, country)
            if (existingUniv == null) {
                val newUniv = Univ(name = name, country = country)
                mUnivDao.insert(newUniv)
                insertionStatus.postValue(true)
            }
            else {
                insertionStatus.postValue(false)
            }
        }
    }

    fun deleteExistingUniv(name: String, country : String) {
        executorService.execute {
            val existingNote = mUnivDao.getUnivData(name, country)
            existingNote?.let {
                mUnivDao.delete(it)
            }
        }
    }

    fun update(univ: Univ) {
        executorService.execute {
            mUnivDao.update(univ)
        }
    }
}
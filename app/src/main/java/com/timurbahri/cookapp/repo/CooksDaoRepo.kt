package com.timurbahri.cookapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.timurbahri.cookapp.entity.Cooks
import com.timurbahri.cookapp.room.Db
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CooksDaoRepo(var application: Application) {
    var cooksList = MutableLiveData<List<Cooks>>()
    var db:Db

    init {
        db = Db.dbEntity(application)!!
        cooksList = MutableLiveData()
    }

    fun cookGet() : MutableLiveData<List<Cooks>> {
        return cooksList
    }

    fun allCookGet() {
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            cooksList.value = db.cooksDao().allCooks()
        }
    }
}
package com.timurbahri.cookapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timurbahri.cookapp.entity.Cooks
import com.timurbahri.cookapp.repo.CooksDaoRepo

class MainPageViewModel(application: Application) : AndroidViewModel(application) {
    var cooksList = MutableLiveData<List<Cooks>>()
    var crepo = CooksDaoRepo(application)

    init {
        cookLoad()
        cooksList = crepo.cookGet()
    }

    fun cookLoad() {
        crepo.allCookGet()
    }
}
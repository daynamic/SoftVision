package com.akshat.softvision.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshat.softvision.data.Coroutines
import com.akshat.softvision.data.Repo
import com.akshat.softvision.model.Row

/**
 * Created by Akshat on 22/06/20.
 */
class MainViewModel : ViewModel() {
    private val _providedData = MutableLiveData<List<Row>>()
    val providedData: LiveData<List<Row>>
        get() = _providedData
    private val _heading = MutableLiveData<String>()
    val heading: LiveData<String>
        get() = _heading


    fun getServices() {
        Coroutines.main {
            try {
                val authResponse = Repo().getService()
                if (authResponse.title != null) {
                    //Getting Values from Response and assigning to _heading and _providedData
                    _heading.value = authResponse.title
                    _providedData.value = authResponse.rows
                    return@main
                }
            } catch (e: Exception) {
                // authListener?.onFailure(e.message!!)
            }
        }
    }

}
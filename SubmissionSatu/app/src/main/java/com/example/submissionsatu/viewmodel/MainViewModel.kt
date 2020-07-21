package com.example.submissionsatu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissionsatu.model.User

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()
    fun setUsers(){

    }
    fun getUsers(): LiveData<ArrayList<User>> = listUsers
}
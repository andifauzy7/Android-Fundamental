package com.example.submissionsatu.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.TypedArray
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.submissionsatu.R
import com.example.submissionsatu.model.User

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val listUsers = MutableLiveData<ArrayList<User>>()

    fun getUsers() = listUsers

    @SuppressLint("Recycle")
    fun setUsers(){
        val listUsersLocal = ArrayList<User>()
        val dataUsername: Array<String>     = getApplication<Application>().resources.getStringArray(R.array.username)
        val dataName: Array<String>         = getApplication<Application>().resources.getStringArray(R.array.name)
        val dataLocation: Array<String>     = getApplication<Application>().resources.getStringArray(R.array.location)
        val dataCompany: Array<String>      = getApplication<Application>().resources.getStringArray(R.array.company)
        val dataRepository: Array<String>   = getApplication<Application>().resources.getStringArray(R.array.repository)
        val dataFollowing: Array<String>    = getApplication<Application>().resources.getStringArray(R.array.following)
        val dataFollowers: Array<String>    = getApplication<Application>().resources.getStringArray(R.array.followers)
        val dataPhoto: TypedArray           = getApplication<Application>().resources.obtainTypedArray(R.array.avatar)
        for (position in dataName.indices) {
            val user = User(
                dataUsername[position], dataName[position],
                dataPhoto.getResourceId(position, -1), dataCompany[position],
                dataLocation[position], dataRepository[position].toInt(),
                dataFollowers[position].toInt(), dataFollowing[position].toInt()
            )
            listUsersLocal.add(user)
        }
        listUsers.postValue(listUsersLocal)
    }
}
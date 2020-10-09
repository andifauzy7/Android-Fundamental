package com.example.submissiontiga.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submissiontiga.db.UserRepository
import com.example.submissiontiga.db.UserRoomDatabase
import com.example.submissiontiga.model.User

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    var repositoryUser: UserRepository
    var allUsers: LiveData<List<User>>
    init {
        val usersDao = UserRoomDatabase.getDatabase(application).userDao()
        this.repositoryUser = UserRepository(usersDao)
        this.allUsers = repositoryUser.allUsers
    }
}
package com.example.submissiontiga.db

import androidx.lifecycle.LiveData
import com.example.submissiontiga.model.User

class UserRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<User>> = userDao.getAlphabetizedUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User){
        userDao.delete(user)
    }
}
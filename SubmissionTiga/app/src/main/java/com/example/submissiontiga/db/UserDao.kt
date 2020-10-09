package com.example.submissiontiga.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.submissiontiga.model.User

@Dao
interface UserDao {
    @Query("SELECT * from user_table ORDER BY username ASC")
    fun getAlphabetizedUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)
}
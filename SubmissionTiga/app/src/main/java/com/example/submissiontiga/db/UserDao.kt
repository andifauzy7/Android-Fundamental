package com.example.submissiontiga.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.submissiontiga.model.User

@Dao
interface UserDao {
    @Query("SELECT * from user_table ORDER BY username ASC")
    fun getAlphabetizedUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table WHERE username = :username")
    suspend fun deleteAll(username : String)
}
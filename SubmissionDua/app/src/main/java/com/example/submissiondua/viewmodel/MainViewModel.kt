package com.example.submissiondua.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissiondua.model.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val listUsers = MutableLiveData<ArrayList<User>>()

    fun getUsers(): LiveData<ArrayList<User>> = listUsers

    fun setUsers(username: String): Boolean {
        var status = true
        val listUser = ArrayList<User>()
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "f7b6febb3427dcf755657ce73168abf06e44032c")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/search/users?q=${username}"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                status = try {
                    val responseObject = JSONObject(result)
                    val arrayResult = responseObject.getJSONArray("items")
                    for (i in 0 until arrayResult.length()){
                        val resultUser =arrayResult.getJSONObject(i)
                        val user = User(resultUser.getString("login"),resultUser.getString("avatar_url"))
                        listUser.add(user)
                    }
                    listUsers.postValue(listUser)
                    true
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
                status = false
            }
        })
        return status
    }
}
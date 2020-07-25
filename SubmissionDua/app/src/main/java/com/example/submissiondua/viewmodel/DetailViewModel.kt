package com.example.submissiondua.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissiondua.model.DetailUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private var user = MutableLiveData<DetailUser>()

    fun getUser(): LiveData<DetailUser> = user

    fun setUser(username: String){
        var detailUser: DetailUser
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "f7b6febb3427dcf755657ce73168abf06e44032c")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/${username}"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONObject(result)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {

            }
        })
    }
}
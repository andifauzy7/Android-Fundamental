package com.example.submissiontiga.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissiontiga.model.DetailUser
import com.example.submissiontiga.model.Repository
import com.example.submissiontiga.model.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private var user = MutableLiveData<DetailUser>()
    private val followers = MutableLiveData<ArrayList<User>>()
    private val following = MutableLiveData<ArrayList<User>>()
    private val repository = MutableLiveData<ArrayList<Repository>>()

    fun getUser(): LiveData<DetailUser> = user

    fun getFollowers(): LiveData<ArrayList<User>> = followers

    fun getFollowing(): LiveData<ArrayList<User>> = following

    fun getRepository(): LiveData<ArrayList<Repository>> = repository

    fun setUser(username: String){
        setRepository(username)
        setFollowers(username)
        setFollowing(username)
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
                    val avatar = responseObject.getString("avatar_url")
                    val name = responseObject.getString("name")
                    val company = responseObject.getString("company")
                    val location = responseObject.getString("location")
                    val bio = responseObject.getString("bio")
                    val repo = responseObject.getInt("public_repos")
                    val followers = responseObject.getInt("followers")
                    val following = responseObject.getInt("following")
                    detailUser = DetailUser(username, avatar, name, company, location, bio, repo, followers, following)
                    user.postValue(detailUser)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    private fun setFollowers(username: String){
        val listUser = ArrayList<User>()
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "f7b6febb3427dcf755657ce73168abf06e44032c")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/${username}/followers"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONArray(result)
                    for (i in 0 until responseObject.length()){
                        val resultUser = responseObject.getJSONObject(i)
                        val user = User(resultUser.getString("login"),resultUser.getString("avatar_url"))
                        listUser.add(user)
                    }
                    followers.postValue(listUser)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    private fun setFollowing(username: String){
        val listUser = ArrayList<User>()
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "f7b6febb3427dcf755657ce73168abf06e44032c")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/${username}/following"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONArray(result)
                    for (i in 0 until responseObject.length()){
                        val resultUser = responseObject.getJSONObject(i)
                        val user = User(resultUser.getString("login"),resultUser.getString("avatar_url"))
                        listUser.add(user)
                    }
                    following.postValue(listUser)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    private fun setRepository(username: String){
        val listRepo = ArrayList<Repository>()
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "f7b6febb3427dcf755657ce73168abf06e44032c")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/${username}/repos"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONArray(result)
                    for (i in 0 until responseObject.length()){
                        val resultRepo = responseObject.getJSONObject(i)
                        val repo = Repository(resultRepo.getString("full_name"),resultRepo.getString("description"), resultRepo.getString("created_at"), resultRepo.getString("language"))
                        listRepo.add(repo)
                    }
                    repository.postValue(listRepo)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }
}
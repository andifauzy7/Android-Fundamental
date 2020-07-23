package com.example.submissiondua

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiondua.adapter.UserAdapter
import com.example.submissiondua.model.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private val listUsers = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUsers()

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        list_user.layoutManager = LinearLayoutManager(this)
        list_user.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bahasa -> {
                true
            }
            R.id.english -> {
                true
            }
            R.id.espanol -> {
                true
            }
            else -> true
        }
    }

    private fun getUsers(){
        loading_bar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "f7b6febb3427dcf755657ce73168abf06e44032c")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/search/users?q=andi"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONObject(result)
                    val value = responseObject.getInt("total_count")
                    val arrayResult = responseObject.getJSONArray("items")
                    for (i in 0 until arrayResult.length()){
                        val resultUser =arrayResult.getJSONObject(i)
                        val user = User(resultUser.getString("login"),resultUser.getString("avatar_url"))
                        listUsers.add(user)
                    }
                    loading_bar.visibility = View.INVISIBLE
                    adapter.setData(listUsers)
                    Toast.makeText(this@MainActivity, "${resources.getString(R.string.found)} : $value ${resources.getString(R.string.people)}.", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Toast.makeText(this@MainActivity, resources.getString(R.string.connection_problem), Toast.LENGTH_SHORT).show()
            }
        })
    }
}